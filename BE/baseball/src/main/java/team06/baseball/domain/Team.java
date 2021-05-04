package team06.baseball.domain;

import org.springframework.data.annotation.Id;

public class Team {

    @Id
    private Long id;
    private String name;
    private String status;
}
