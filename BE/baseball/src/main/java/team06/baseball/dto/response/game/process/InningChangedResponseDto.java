package team06.baseball.dto.response.game.process;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class InningChangedResponseDto {
    private int counter;
    private boolean isTop;

    public static InningChangedResponseDto of(int counter, String topBottom) {
        return InningChangedResponseDto.builder()
                .counter(counter)
                .isTop("TOP".equalsIgnoreCase(topBottom))
                .build();
    }
}
