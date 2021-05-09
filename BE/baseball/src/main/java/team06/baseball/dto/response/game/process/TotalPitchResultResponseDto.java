package team06.baseball.dto.response.game.process;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TotalPitchResultResponseDto {

    private NewPitchResponseDto newPitch;
    private BallChangedResponseDto ballChanged;
    private BaseChangedResponseDto baseChanged;

    public static TotalPitchResultResponseDto of(
            NewPitchResponseDto newPitch
            , BallChangedResponseDto ballChanged
            , BaseChangedResponseDto baseChanged) {
        return TotalPitchResultResponseDto.builder()
                .newPitch(newPitch)
                .ballChanged(ballChanged)
                .baseChanged(baseChanged)
                .build();
    }
}