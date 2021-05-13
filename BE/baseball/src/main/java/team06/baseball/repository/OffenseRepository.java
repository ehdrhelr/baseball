package team06.baseball.repository;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import team06.baseball.domain.Offense;

import java.util.Optional;

public interface OffenseRepository extends CrudRepository<Offense, Long> {

    @Query("SELECT * FROM baseball.offense WHERE team_id = :teamId AND inning_id = :inningId AND on_turn = 1")
    Offense findByTeamIdAndInningIdAndOnTurn(Long teamId, Long inningId);

    @Query("SELECT * FROM baseball.offense WHERE team_id = :teamId AND inning_id = :inningId AND `order` = :order")
    Offense findByTeamIdAndInningIdAndOrder(Long teamId, Long inningId, int order);

    @Query("SELECT at_bat FROM baseball.offense WHERE player_id = :playerId ORDER BY id DESC LIMIT 1")
    Optional<Integer> findBeforeAtBatByPlayerId(Long playerId);

    @Query("SELECT hit FROM baseball.offense WHERE player_id = :playerId ORDER BY id DESC LIMIT 1")
    Optional<Integer> findBeforeHitByPlayerId(Long playerId);

    @Query("SELECT on_turn FROM baseball.offense WHERE player_id = :playerId ORDER BY id DESC LIMIT 1")
    Optional<Boolean> findBeforeOnTurnByPlayerId(Long playerId);

    @Modifying
    @Query("DELETE FROM offense")
    void deletaAllData();
}
