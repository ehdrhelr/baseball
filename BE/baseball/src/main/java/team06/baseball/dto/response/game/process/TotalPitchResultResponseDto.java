package team06.baseball.dto.response.game.process;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import team06.baseball.dto.response.game.start.PlayerStartResponseDto;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TotalPitchResultResponseDto {

    private InningChangedResponseDto inning;
    private ScoreResponseDto score;
    private PlayerStartResponseDto batter;
    private PlayerStartResponseDto pitcher;
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

    public static TotalPitchResultResponseDto of(
            InningChangedResponseDto inning
            , ScoreResponseDto score
            , PlayerStartResponseDto batter
            , PlayerStartResponseDto pitcher
            , NewPitchResponseDto newPitch
            , BallChangedResponseDto ballChanged
            , BaseChangedResponseDto baseChanged) {
        return TotalPitchResultResponseDto.builder()
                .inning(inning)
                .score(score)
                .batter(batter)
                .pitcher(pitcher)
                .newPitch(newPitch)
                .ballChanged(ballChanged)
                .baseChanged(baseChanged)
                .build();
    }
}
