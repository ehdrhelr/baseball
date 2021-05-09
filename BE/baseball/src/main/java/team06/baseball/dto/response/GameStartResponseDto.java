package team06.baseball.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import team06.baseball.domain.Inning;
import team06.baseball.domain.Player;
import team06.baseball.domain.Team;

import java.util.HashMap;
import java.util.Map;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GameStartResponseDto {

    private Map<String, String> teams;
    private InningStartResponseDto inning;
    private Map<String, Integer> score;
    private PlayerStartResponseDto batter;
    private PlayerStartResponseDto pitcher;


    public static GameStartResponseDto of(
            Team home, Team away, Inning inning, Player batter, Player pitcher) {

        Map<String, String> tempTeams = new HashMap<>();
        tempTeams.put("home", home.getTeam());
        tempTeams.put("away", away.getTeam());

        Map<String, Integer> tempScore = new HashMap<>();
        tempScore.put("home", 0);
        tempScore.put("away", 0);

        return builder()
                .teams(tempTeams)
                .inning(InningStartResponseDto.of(inning.getRound(), inning.getTopBottom()))
                .score(tempScore)
                .batter(PlayerStartResponseDto.of(batter.getName(), "백엔드 천재"))
                .pitcher(PlayerStartResponseDto.of(pitcher.getName(), "iOS 천재"))
                .build();
    }
}
