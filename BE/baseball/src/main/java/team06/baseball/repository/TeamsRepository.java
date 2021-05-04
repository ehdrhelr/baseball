package team06.baseball.repository;

import org.springframework.data.repository.CrudRepository;
import team06.baseball.domain.Team;

public interface TeamsRepository extends CrudRepository<Team, Long> {
}
