package team06.baseball.domain;

import lombok.ToString;
import org.springframework.data.annotation.Id;

@ToString
public class Team {

    @Id
    private Long id;
    private String name;
    private String status;
}
