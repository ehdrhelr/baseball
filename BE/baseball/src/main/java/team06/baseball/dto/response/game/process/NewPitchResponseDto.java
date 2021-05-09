package team06.baseball.dto.response.game.process;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class NewPitchResponseDto {

    private int count;
    private String result;
    private String log;

    public static NewPitchResponseDto of(int count, String result, String log) {
        return NewPitchResponseDto.builder()
                .count(count)
                .result(result)
                .log(log)
                .build();
    }
}
