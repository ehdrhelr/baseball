package team06.baseball.dto.response;

import lombok.ToString;

import java.util.List;

@ToString
public class GameListResponseDto {

    private List<GamesResponseDto> games;
}
