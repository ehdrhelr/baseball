package team06.baseball.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import team06.baseball.domain.Offense;

public interface OffenseRepository extends CrudRepository<Offense, Long> {

    @Query("SELECT * FROM baseball.offense WHERE team_id = :teamId AND inning_id = :inningId AND on_turn = 1")
    Offense findByTeamIdAndInningIdAndOnTurn(Long teamId, Long inningId);

    @Query("SELECT * FROM baseball.offense WHERE team_id = :teamId AND inning_id = :inningId AND `order` = :order")
    Offense findByTeamIdAndInningIdAndOrder(Long teamId, Long inningId, int order);
}
