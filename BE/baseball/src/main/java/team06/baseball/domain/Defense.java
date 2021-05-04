package team06.baseball.domain;

import org.springframework.data.annotation.Id;

public class Defense {

    @Id
    private Long id;
    private Long teamId;
    private Long inningId;
    private Long playerId;
}
