package diecast.collector.api.repository;

import diecast.collector.api.domain.Model;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

@Repository
public interface ModelRepository extends CrudRepository<Model, Integer> {
}
