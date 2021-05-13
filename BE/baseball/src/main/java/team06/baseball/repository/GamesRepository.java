package team06.baseball.repository;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import team06.baseball.domain.Game;

public interface GamesRepository extends CrudRepository<Game, Long> {

    @Modifying
    @Query("set sql_safe_updates=0")
    void safeUpdateOff();
}
