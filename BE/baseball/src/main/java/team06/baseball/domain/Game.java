package team06.baseball.domain;

import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@ToString
@Getter
public class Game {

    @Id
    private Long id;
    private Long homeTeamId;
    private Long awayTeamId;
}
