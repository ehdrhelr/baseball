package team06.baseball.dto.response.game.process;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ScoreResponseDto {
    private int home;
    private int away;

    public static ScoreResponseDto of(int home, int away) {
        return ScoreResponseDto.builder()
                .home(home)
                .away(away)
                .build();
    }
}
