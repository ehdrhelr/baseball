package team06.baseball.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import team06.baseball.domain.Inning;

public interface InningsRepository extends CrudRepository<Inning, Long> {

    @Query("SELECT * FROM baseball.inning WHERE game_id = :id ORDER BY id DESC LIMIT 1;")
    Inning findNewestInningByGameId(Long id);
}
