package team06.baseball.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import team06.baseball.domain.Team;
import team06.baseball.dto.ApiResult;
import team06.baseball.dto.request.TeamSelectRequestDto;
import team06.baseball.repository.TeamsRepository;

import java.util.List;

@RequestMapping("/baseball/teams")
@RestController
public class TeamsController {

    private Logger logger = LoggerFactory.getLogger(TeamsController.class);

    private final TeamsRepository teamsRepository;

    public TeamsController(TeamsRepository teamsRepository) {
        this.teamsRepository = teamsRepository;
    }

    @GetMapping("/{id}")
    public ApiResult<Team> getTeam(@PathVariable Long id) {
        Team team = teamsRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(id + "팀이 없습니다."));
        logger.info(team.toString());
        return ApiResult.succeed(team);
    }

    @GetMapping
    public ApiResult<List<Team>> getTeams() {
        List<Team> teams = (List<Team>) teamsRepository.findAll();
        logger.info(teams.toString());
        return ApiResult.succeed(teams);
    }

    @PostMapping
    public void selectTeam(TeamSelectRequestDto teamSelectRequestDto) {


    }
}
