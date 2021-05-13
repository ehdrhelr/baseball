package team06.baseball.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team06.baseball.dto.ApiResult;
import team06.baseball.dto.response.game.process.TotalPitchResultResponseDto;
import team06.baseball.dto.response.game.start.GameStartResponseDto;
import team06.baseball.dto.response.GamesResponseDto;
import team06.baseball.service.GamesService;
import team06.baseball.service.InningsService;

import java.util.List;

@RequestMapping("/baseball/games")
@RestController
public class GamesController {

    private final GamesService gamesService;
    private final InningsService inningsService;

    public GamesController(GamesService gamesService, InningsService inningsService) {
        this.gamesService = gamesService;
        this.inningsService = inningsService;
    }

    @GetMapping
    public ApiResult<List<GamesResponseDto>> getGames() {
        return ApiResult.succeed(gamesService.getGames());
    }

    @GetMapping("/{id}")
    public ApiResult<GamesResponseDto> getGame(@PathVariable Long id) {
        return ApiResult.succeed(gamesService.getGame(id));
    }

    @GetMapping("/{id}/start")
    public ApiResult<GameStartResponseDto> startGame(@PathVariable Long id) {
        gamesService.resetDatabase();
        return ApiResult.succeed(gamesService.startGame(id));
    }

    @GetMapping("/{id}/pitch")
    public ApiResult<TotalPitchResultResponseDto> pitch(@PathVariable Long id) {
        return ApiResult.succeed(inningsService.pitch(id));
    }
}
