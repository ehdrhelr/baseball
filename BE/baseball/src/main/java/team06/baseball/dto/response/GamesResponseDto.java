package team06.baseball.dto.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import team06.baseball.domain.Team;

@JsonPropertyOrder({"id", "home", "away"})
@Getter
@AllArgsConstructor
public class GamesResponseDto {

    private Long id; // game id
    private Team home;
    private Team away;
}
