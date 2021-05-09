package team06.baseball.dto.response;

import team06.baseball.domain.Inning;
import team06.baseball.domain.Player;
import team06.baseball.dto.BallChangedDto;
import team06.baseball.dto.BaseChangedDto;
import team06.baseball.dto.NewPitchDto;

import java.util.List;

public class GameStartResponseDto {
    private List<String> teams;
    private Inning inning;
    private List<Integer> score;
    private Player batter;
    private Player pitcher;
    private NewPitchDto newPitch;
    private BallChangedDto ballChanged;
    private BaseChangedDto baseChanged;
}
