package diecast.collector.api.repository;

import diecast.collector.api.domain.Automaker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

class ModelRepositoryTest {
    @Inject
    static ModelRepository modelRepository;
    @Inject
    static AutomakerRepository automakerRepository;

    @BeforeAll
    static void setUp() {
        var honda = automakerRepository.save(new Automaker("Honda", "Japan"));
        var datsun = automakerRepository.save(new Automaker("Datsun", "Japan"));
        var porsche = automakerRepository.save(new Automaker("Porsche", "Germany"));

    }

    @Test
    void loadModelQuantityByAutomakers() {
    }
}