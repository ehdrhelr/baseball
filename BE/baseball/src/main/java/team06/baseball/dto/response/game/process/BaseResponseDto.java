package team06.baseball.dto.response.game.process;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BaseResponseDto {

    private boolean in;
    private boolean out;

    public static BaseResponseDto of(boolean in, boolean out) {
        return BaseResponseDto.builder()
                .in(in)
                .out(out)
                .build();
    }
}
