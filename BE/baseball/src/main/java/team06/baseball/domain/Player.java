package team06.baseball.domain;

import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@ToString
@Getter
public class Player {

    @Id
    private Long id;
    private Long teamId;
    private String name;
    private String position;
}
