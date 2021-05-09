package team06.baseball.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class InningStartResponseDto {
    private int counter;
    private boolean isTop;


    public static InningStartResponseDto of(int counter, String topBottom) {

        return InningStartResponseDto.builder()
                .counter(counter)
                .isTop(topBottom.equals("TOP"))
                .build();
    }
}
