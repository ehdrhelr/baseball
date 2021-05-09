package team06.baseball.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import team06.baseball.domain.Defense;

public interface DefenseRepository extends CrudRepository<Defense, Long> {

    @Query("SELECT * FROM baseball.defense WHERE team_id = :id")
    Defense findByTeamId(Long id);
}
