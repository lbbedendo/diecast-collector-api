package diecast.collector.api.repository;

import diecast.collector.api.domain.Brand;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

@Repository
public interface BrandRepository extends CrudRepository<Brand, Integer> {
}
