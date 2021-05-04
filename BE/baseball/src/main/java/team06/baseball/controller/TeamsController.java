package team06.baseball.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team06.baseball.domain.Team;
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
    public String getTeam(@PathVariable Long id) {
        Team team = teamsRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(id + "팀이 없습니다."));
        logger.info(team.toString());
        return team.toString();
    }

    @GetMapping("/")
    public String getTeams() {
        List<Team> teams = (List<Team>) teamsRepository.findAll();
        logger.info(teams.toString());
        return teams.toString();
    }
}
