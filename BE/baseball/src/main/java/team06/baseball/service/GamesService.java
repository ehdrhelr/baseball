package team06.baseball.service;

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

    private final GamesRepository gamesRepository;
    private final TeamsRepository teamsRepository;
    private final InningsRepository inningsRepository;
    private final PlayersRepository playersRepository;
    private final DefenseRepository defenseRepository;

    @Autowired
    public GamesService(GamesRepository gamesRepository
            , TeamsRepository teamsRepository
            , InningsRepository inningsRepository
            , PlayersRepository playersRepository
            , DefenseRepository defenseRepository) {
        this.gamesRepository = gamesRepository;
        this.teamsRepository = teamsRepository;
        this.inningsRepository = inningsRepository;
        this.playersRepository = playersRepository;
        this.defenseRepository = defenseRepository;
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

    /**
     * 1회초 away팀 공격 home팀 수비를 가정
     * @param id
     * @return
     */
    public GameStartResponseDto startGame(Long id) {
        Inning bottomStartInning = inningsRepository.save(new Inning(id));
        Team away = teamsRepository.findById(1L)
                .orElseThrow(() -> new IllegalStateException("1번팀을 찾을 수 없습니다."));
        Team home = teamsRepository.findById(2L)
                .orElseThrow(() -> new IllegalStateException("2번팀을 찾을 수 없습니다."));
        Player batter = playersRepository.findBatterAtBatByTeamId(2L);
        Player pitcher = playersRepository.findPlayingPitcherByTeamId(1L);

        defenseRepository.save(Defense.of(home.getId(), bottomStartInning.getId(), pitcher.getId(), 0));

        return GameStartResponseDto.of(home, away, bottomStartInning, batter, pitcher);
    }
}
