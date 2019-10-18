package diecast.collector.api.repository;

import diecast.collector.api.domain.Model;
import edu.umd.cs.findbugs.annotations.NonNull;
import io.micronaut.data.annotation.Join;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ModelRepository extends JpaRepository<Model, Integer> {
    @NonNull
    @Override
    @Join(value = "automaker", type = Join.Type.LEFT_FETCH)
    @Join(value = "collection", type = Join.Type.LEFT_FETCH)
    @Join(value = "brand", type = Join.Type.LEFT_FETCH)
    List<Model> findAll();

    @NonNull
    @Override
    @Join(value = "automaker", type = Join.Type.LEFT_FETCH)
    @Join(value = "collection", type = Join.Type.LEFT_FETCH)
    @Join(value = "brand", type = Join.Type.LEFT_FETCH)
    Optional<Model> findById(@NonNull Integer id);
}
