package team06.baseball.dto.response.game.process;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BallChangedResponseDto {
    private int strike;
    private int ball;
    private int out;

    public static BallChangedResponseDto strike() {
        return BallChangedResponseDto.builder()
                .strike(1)
                .ball(0)
                .out(0)
                .build();
    }

    public static BallChangedResponseDto ball() {
        return BallChangedResponseDto.builder()
                .strike(0)
                .ball(1)
                .out(0)
                .build();
    }

    public static BallChangedResponseDto out() {
        return BallChangedResponseDto.builder()
                .strike(0)
                .ball(0)
                .out(1)
                .build();
    }

    public static BallChangedResponseDto runOneBase() {
        return BallChangedResponseDto.builder()
                .strike(0)
                .ball(0)
                .out(0)
                .build();
    }

}
