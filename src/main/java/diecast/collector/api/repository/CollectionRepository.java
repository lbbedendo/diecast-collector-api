package diecast.collector.api.repository;

import diecast.collector.api.domain.Collection;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

@Repository
public interface CollectionRepository extends CrudRepository<Collection, Integer> {
}
