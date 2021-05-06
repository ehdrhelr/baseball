package team06.baseball.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team06.baseball.domain.Game;
import team06.baseball.dto.ApiResult;
import team06.baseball.dto.GamesResponseDto;
import team06.baseball.service.GamesService;

import java.util.List;

@RequestMapping("/baseball/games")
@RestController
public class GamesController {

    private final GamesService gamesService;

    public GamesController(GamesService gamesService) {
        this.gamesService = gamesService;
    }

    @GetMapping
    public ApiResult<List<GamesResponseDto>> getGames() {
        return ApiResult.succeed(gamesService.getGames());
    }

    @GetMapping("/{id}")
    public ApiResult<GamesResponseDto> getGame(@PathVariable Long id) {
        GamesResponseDto gamesResponseDto = gamesService.getGame(id);
        return ApiResult.succeed(gamesResponseDto);
    }
}
