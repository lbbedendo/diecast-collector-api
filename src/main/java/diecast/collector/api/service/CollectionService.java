package diecast.collector.api.service;

import com.google.common.collect.Lists;
import diecast.collector.api.domain.Collection;
import diecast.collector.api.dto.CollectionSaveRequest;
import diecast.collector.api.repository.CollectionRepository;

import javax.inject.Singleton;
import java.util.List;
import java.util.Optional;

@Singleton
public class CollectionService {
    private CollectionRepository collectionRepository;

    public CollectionService(CollectionRepository collectionRepository) {
        this.collectionRepository = collectionRepository;
    }

    public Optional<Collection> getById(Integer id) {
        return collectionRepository.findById(id);
    }

    public List<Collection> getAll() {
        return Lists.newArrayList(collectionRepository.findAll());
    }

    public Collection create(CollectionSaveRequest request) {
        var collection = new Collection(request.getName(), request.getYear());
        return collectionRepository.save(collection);
    }

    public Collection update(Collection collection, CollectionSaveRequest request) {
        collection.setName(request.getName());
        collection.setYear(request.getYear());
        return collectionRepository.save(collection);
    }

    public void delete(Collection collection) {
        collectionRepository.delete(collection);
    }
}
