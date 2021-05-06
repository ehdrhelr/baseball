package team06.baseball.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import team06.baseball.domain.Team;

@Getter
@AllArgsConstructor
public class GamesResponseDto {

    private Long id; // game id
    private Team home;
    private Team away;
}
