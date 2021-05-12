package team06.baseball.dto.response.game.process;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import team06.baseball.domain.Inning;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BaseChangedResponseDto {

    private BaseResponseDto first;
    private BaseResponseDto second;
    private BaseResponseDto third;

    public static BaseChangedResponseDto of(
            BaseResponseDto first, BaseResponseDto second, BaseResponseDto third) {
        return BaseChangedResponseDto.builder()
                .first(first)
                .second(second)
                .third(third)
                .build();
    }

    public static BaseChangedResponseDto of(Inning inning) {
        BaseResponseDto first = null;
        BaseResponseDto second = null;
        BaseResponseDto third = null;

        if (!inning.isBase1()) {
            first = BaseResponseDto.of(true, false);
        } else if (!inning.isBase2()) {
            first = BaseResponseDto.of(true, true);
            second = BaseResponseDto.of(true, false);
        } else if (!inning.isBase3()) {
            first = BaseResponseDto.of(true, true);
            second = BaseResponseDto.of(true, true);
            third = BaseResponseDto.of(true, false);
        }

        return BaseChangedResponseDto.builder()
                .first(first)
                .second(second)
                .third(third)
                .build();
    }

    public static BaseChangedResponseDto scoring() {
        return BaseChangedResponseDto.builder()
                .first(BaseResponseDto.of(true, true))
                .second(BaseResponseDto.of(true, true))
                .third(BaseResponseDto.of(true, true))
                .build();

    }
}
