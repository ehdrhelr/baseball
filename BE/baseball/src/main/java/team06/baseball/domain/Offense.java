package team06.baseball.domain;

import lombok.*;
import org.springframework.data.annotation.Id;

@Setter
@Getter
@Builder(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Offense {

    @Id
    private Long id;
    private Long teamId;
    private Long inningId;
    private Long playerId;
    private int atBat;
    private int hit;
    private int order;
    private boolean onTurn;

    public static Offense of(
            Long teamId, Long inningId, Long playerId, int atBat, int hit, int order, boolean onTurn) {
        return Offense.builder()
                .teamId(teamId)
                .inningId(inningId)
                .playerId(playerId)
                .atBat(atBat)
                .hit(hit)
                .order(order)
                .onTurn(onTurn)
                .build();
    }

    public void turnOff() {
        this.onTurn = false;
    }

    public void turnOn() {
        this.onTurn = true;
    }
}
