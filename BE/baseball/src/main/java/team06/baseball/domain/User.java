package team06.baseball.domain;

import org.springframework.data.annotation.Id;

public class User {

    @Id
    private Long id;
    private String email;
    private Long game_id;
}
