package diecast.collector.api.repository;

import diecast.collector.api.domain.Automaker;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

@Repository
public interface AutomakerRepository extends JpaRepository<Automaker, Integer> {
}
