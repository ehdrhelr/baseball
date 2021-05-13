package team06.baseball.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team06.baseball.domain.*;
import team06.baseball.dto.response.game.start.GameStartResponseDto;
import team06.baseball.dto.response.GamesResponseDto;
import team06.baseball.repository.*;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GamesService {

    private Logger logger = LoggerFactory.getLogger(GamesService.class);

    private final GamesRepository gamesRepository;
    private final TeamsRepository teamsRepository;
    private final InningsRepository inningsRepository;
    private final PlayersRepository playersRepository;
    private final DefenseRepository defenseRepository;
    private final OffenseRepository offenseRepository;

    @Autowired
    public GamesService(GamesRepository gamesRepository
            , TeamsRepository teamsRepository
            , InningsRepository inningsRepository
            , PlayersRepository playersRepository
            , DefenseRepository defenseRepository
            , OffenseRepository offenseRepository) {
        this.gamesRepository = gamesRepository;
        this.teamsRepository = teamsRepository;
        this.inningsRepository = inningsRepository;
        this.playersRepository = playersRepository;
        this.defenseRepository = defenseRepository;
        this.offenseRepository = offenseRepository;
    }

    public GamesResponseDto getGame(Long id) {
        Game game = gamesRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(id + "번 게임을 찾을 수 없습니다."));
        Long homeTeamId = game.getHomeTeamId();
        Long awayTeamId = game.getAwayTeamId();

        Team home = teamsRepository.findById(homeTeamId)
                .orElseThrow(() -> new IllegalStateException(homeTeamId + "번 팀을 찾을 수 없습니다."));
        Team away = teamsRepository.findById(awayTeamId)
                .orElseThrow(() -> new IllegalStateException(awayTeamId + "번 팀을 찾을 수 없습니다."));

        return new GamesResponseDto(game.getId(), home, away);
    }

    public List<GamesResponseDto> getGames() {
        List<Game> games = (List<Game>) gamesRepository.findAll();
        return games.stream()
                .map(game -> getGame(game.getId()))
                .collect(Collectors.toList());
    }

    public GameStartResponseDto startGame(Long id) {
        Inning inning = inningsRepository.save(new Inning(id));
        Game game = gamesRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(id + "게임을 찾을 수 없습니다."));
        Long homeTeamId = game.getHomeTeamId();
        Long awayTeamId = game.getAwayTeamId();

        Team away = teamsRepository.findById(awayTeamId)
                .orElseThrow(() -> new IllegalStateException(awayTeamId + "팀을 찾을 수 없습니다."));
        Team home = teamsRepository.findById(homeTeamId)
                .orElseThrow(() -> new IllegalStateException(homeTeamId + "팀을 찾을 수 없습니다."));

        Player pitcher = playersRepository.findPlayingPitcherByTeamId(homeTeamId);

        // 1회 초 홈 - 수비 등록
        defenseRepository.save(Defense.of(home.getId(), inning.getId(), pitcher.getId(), 0));
        // 1회 초 어웨이 - 공격 등록
        saveOffense(awayTeamId, inning.getId());

        Offense offense = offenseRepository.findByTeamIdAndInningIdAndOnTurn(awayTeamId, inning.getId());
        Player batter = playersRepository.findById(offense.getPlayerId())
                .orElseThrow(() -> new IllegalStateException());

        return GameStartResponseDto.of(home, away, inning, batter, pitcher);
    }

    protected void saveOffense(Long teamId, Long inningId) {
        List<Player> players = playersRepository.findBattersByTeamId(teamId);
        Inning inning = inningsRepository.findById(inningId)
                .orElseThrow(() -> new IllegalStateException());

        for (int i = 0; i < 9; i++) {
            int order = i + 1;
            int beforeAtBat = offenseRepository.findBeforeAtBatByPlayerId(players.get(i).getId())
                    .orElse(0);
            int beforeHit = offenseRepository.findBeforeHitByPlayerId(players.get(i).getId())
                    .orElse(0);
            boolean beforeOnTurn = false;

            if (inning.isGameStartInning() && i == 0) {
                beforeOnTurn = true;
            } else {
                beforeOnTurn = offenseRepository.findBeforeOnTurnByPlayerId(players.get(i).getId())
                        .orElse(false);;
            }

            Offense offense = Offense.of(
                    teamId
                    , inningId
                    , players.get(i).getId()
                    , beforeAtBat
                    , beforeHit
                    , order
                    , beforeOnTurn);
            offenseRepository.save(offense);
        }
    }

    public void resetDatabase() {
        gamesRepository.safeUpdateOff();
        defenseRepository.deleteAllData();
        gamesRepository.safeUpdateOff();
        offenseRepository.deletaAllData();
        gamesRepository.safeUpdateOff();
        inningsRepository.deleteAllData();
    }
}
