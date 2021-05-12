package team06.baseball.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import team06.baseball.domain.Player;

import java.util.List;

public interface PlayersRepository extends CrudRepository<Player, Long> {

    @Query("SELECT * FROM baseball.player WHERE team_id = :id AND position = '투수'")
    Player findPlayingPitcherByTeamId(Long id);

    @Query("SELECT * FROM baseball.player WHERE team_id = :id AND position = '타자'")
    List<Player> findBattersByTeamId(Long id);
}
