package team06.baseball.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team06.baseball.domain.Game;
import team06.baseball.domain.Inning;
import team06.baseball.domain.Player;
import team06.baseball.domain.Team;
import team06.baseball.dto.response.GameStartResponseDto;
import team06.baseball.dto.response.GamesResponseDto;
import team06.baseball.repository.GamesRepository;
import team06.baseball.repository.InningsRepository;
import team06.baseball.repository.PlayersRepository;
import team06.baseball.repository.TeamsRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GamesService {

    private final GamesRepository gamesRepository;
    private final TeamsRepository teamsRepository;
    private final InningsRepository inningsRepository;
    private final PlayersRepository playersRepository;

    @Autowired
    public GamesService(GamesRepository gamesRepository
            , TeamsRepository teamsRepository
            , InningsRepository inningsRepository
            , PlayersRepository playersRepository) {
        this.gamesRepository = gamesRepository;
        this.teamsRepository = teamsRepository;
        this.inningsRepository = inningsRepository;
        this.playersRepository = playersRepository;
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
        Inning bottomStartInning = inningsRepository.save(new Inning(id));
        Team away = teamsRepository.findById(1L)
                .orElseThrow(() -> new IllegalStateException("1번팀을 찾을 수 없습니다."));
        Team home = teamsRepository.findById(2L)
                .orElseThrow(() -> new IllegalStateException("2번팀을 찾을 수 없습니다."));
        Player batter = playersRepository.findBatterAtBatByTeamId(2L);
        Player pitcher = playersRepository.findPlayingPitcherByTeamId(1L);

        return GameStartResponseDto.of(home, away, bottomStartInning, batter, pitcher);
    }
}
