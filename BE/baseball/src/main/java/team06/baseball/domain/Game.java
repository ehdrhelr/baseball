package team06.baseball.domain;

import org.springframework.data.annotation.Id;

public class Game {

    @Id
    private Long id;
    private Long homeTeamId;
    private Long awayTeamId;
}
