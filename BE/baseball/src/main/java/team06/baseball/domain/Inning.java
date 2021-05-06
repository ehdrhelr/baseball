package team06.baseball.domain;

import org.springframework.data.annotation.Id;

public class Inning {

    @Id
    private Long id;
    private Long gameId;
    private Integer round;
    private String topBottom;
    private Integer out;
    private Integer strike;
    private Integer ball;
    private boolean base1;
    private boolean base2;
    private boolean base3;
    private Integer score;
}
