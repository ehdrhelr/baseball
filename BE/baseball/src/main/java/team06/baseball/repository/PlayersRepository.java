package team06.baseball.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import team06.baseball.domain.Player;

import java.util.List;

public interface PlayersRepository extends CrudRepository<Player, Long> {

    // todo : player에 현재 투수를 구별할 수 있는 컬럼 추가하고 변경 요망
    @Query("SELECT * FROM baseball.player WHERE team_id = :id AND position = '투수'")
    Player findPlayingPitcherByTeamId(Long id);

    @Query("SELECT * FROM baseball.player WHERE " +
            "(SELECT @ROWNUM:=@ROWNUM+1 AS ROWNUM " +
            "WHERE team_id = :id " +
            "AND position = '타자' " +
            "AND (@ROWNUM:=0)=0" +
            ") = :order")
    Player findNextBatterByTeamIdAndOrder(Long id, Long order);

    @Query("SELECT * FROM baseball.player WHERE " +
            "(SELECT @ROWNUM:=@ROWNUM+1 AS ROWNUM " +
            "WHERE team_id = :id" +
            "AND position = '타자' " +
            "AND (@ROWNUM:=0)=0" +
            ") = 1")
    Player findFirstBatterByTeamId(Long id);

    @Query("SELECT * FROM baseball.player WHERE team_id = :id AND position = '타자'")
    List<Player> findBattersByTeamId(Long id);
}
