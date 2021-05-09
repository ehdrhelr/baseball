package team06.baseball.repository;

import org.springframework.data.repository.CrudRepository;
import team06.baseball.domain.Inning;

public interface InningsRepository extends CrudRepository<Inning, Long> {

}
