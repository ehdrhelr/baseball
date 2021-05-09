package team06.baseball.domain;

import lombok.*;
import org.springframework.data.annotation.Id;

@Setter
@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Defense {

    @Id
    private Long id;
    private Long teamId;
    private Long inningId;
    private Long playerId;
    private int pitch;

    public static Defense of(Long teamId, Long inningId, Long playerId, int pitch) {
        return Defense.builder()
                .teamId(teamId)
                .inningId(inningId)
                .playerId(playerId)
                .pitch(pitch)
                .build();
    }
}
