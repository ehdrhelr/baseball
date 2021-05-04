package team06.baseball.domain;

import org.springframework.data.annotation.Id;

public class Player {

    @Id
    private Long id;
    private Long teamId;
    private String name;
    private String position;
    private Integer atBat;
    private Integer hit;
    private Integer out;
    private Integer pitch;
}
