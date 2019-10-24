package diecast.collector.api.repository;

import diecast.collector.api.domain.Automaker;
import diecast.collector.api.domain.Model;
import diecast.collector.api.dto.response.ModelQuantityByAutomakerResponse;
import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import javax.inject.Inject;
import java.util.List;

import static diecast.collector.api.enums.ModelScale.SCALE_1_64;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;

@MicronautTest
@TestInstance(value = TestInstance.Lifecycle.PER_CLASS)
class ModelRepositoryTest {
    @Inject
    ModelRepository modelRepository;
    @Inject
    AutomakerRepository automakerRepository;

    @BeforeAll
    public void setUp() {
        populateData();
    }

    private void populateData() {
        var honda = automakerRepository.save(new Automaker("Honda", "Japan"));
        var datsun = automakerRepository.save(new Automaker("Datsun", "Japan"));
        var porsche = automakerRepository.save(new Automaker("Porsche", "Germany"));
        modelRepository.saveAll(List.of(
                new Model("Civic Type R", 2016, SCALE_1_64, null, honda, null, null),
                new Model("CRX", 1988, SCALE_1_64, null, honda, null, null)));
        modelRepository.saveAll(List.of(
                new Model("240z", 1970, SCALE_1_64, null, datsun, null, null),
                new Model("510", 1974, SCALE_1_64, null, datsun, null, null)));
        modelRepository.save(new Model("911", 2018, SCALE_1_64, null, porsche, null, null));
    }

    @Test
    void loadModelQuantityByAutomakers() {
        var response = modelRepository.loadModelQuantityByAutomakers();
        assertThat(response)
                .isNotNull()
                .extracting(
                        ModelQuantityByAutomakerResponse::getAutomakerName,
                        ModelQuantityByAutomakerResponse::getQuantity)
                .containsExactlyInAnyOrder(
                        tuple("Honda", 2),
                        tuple("Datsun", 2),
                        tuple("Porsche", 1));
    }
}