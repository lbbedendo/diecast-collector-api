package diecast.collector.api.repository;

import diecast.collector.api.domain.Automaker;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

@Repository
public interface AutomakerRepository extends CrudRepository<Automaker, Integer> {
}
