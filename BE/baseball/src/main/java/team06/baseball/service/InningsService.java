package team06.baseball.service;

import org.springframework.stereotype.Service;
import team06.baseball.repository.InningsRepository;

@Service
public class InningsService {

    private final InningsRepository inningsRepository;

    public InningsService(InningsRepository inningsRepository) {
        this.inningsRepository = inningsRepository;
    }
}
