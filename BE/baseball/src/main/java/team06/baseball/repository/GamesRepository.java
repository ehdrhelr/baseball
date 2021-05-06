package team06.baseball.repository;

import org.springframework.data.repository.CrudRepository;
import team06.baseball.domain.Game;

public interface GamesRepository extends CrudRepository<Game, Long> {
}
