package team06.baseball.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import team06.baseball.domain.Player;

public interface PlayersRepository extends CrudRepository<Player, Long> {

    // todo : player에 현재 타석에 있는 타자를 구별할 수 있는 컬럼 추가하고 변경 요망
    @Query("SELECT * FROM baseball.player WHERE id = 11 AND team_id = :id")
    Player findBatterAtBatByTeamId(Long id);

    // todo : player에 현재 투수를 구별할 수 있는 컬럼 추가하고 변경 요망
    @Query("SELECT * FROM baseball.player WHERE id = 10 AND team_id = :id")
    Player findPlayingPitcherByTeamId(Long id);
}
