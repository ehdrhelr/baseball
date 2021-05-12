package team06.baseball.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import team06.baseball.domain.Defense;

import java.util.Optional;

public interface DefenseRepository extends CrudRepository<Defense, Long> {

    @Query("SELECT * FROM baseball.defense WHERE team_id = :id ORDER BY id DESC LIMIT 1")
    Defense findNewestDefenseByTeamId(Long id);

    @Query("SELECT pitch FROM baseball.defense WHERE player_id = :playerId ORDER BY id DESC LIMIT 1")
    Optional<Integer> findBeforePitchByPlayerId(Long playerId);
}
