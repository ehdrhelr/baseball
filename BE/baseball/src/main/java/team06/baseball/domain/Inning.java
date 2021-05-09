package team06.baseball.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@ToString
@Getter
@NoArgsConstructor
public class Inning {

    @Id
    private Long id;
    private Long gameId;
    private int round; // counter
    private String topBottom;
    private int out;
    private int strike;
    private int ball;
    private boolean base1;
    private boolean base2;
    private boolean base3;
    private int score;

    public Inning(Long gameId) {
        this.gameId = gameId;
        this.round = 1;
        this.topBottom = "BOTTOM";
    }

    public void oneStrike() {
        strike++;
    }
    public void oneBall() {
        ball++;
    }
}
