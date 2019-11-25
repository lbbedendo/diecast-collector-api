package diecast.collector.api.controller;

import diecast.collector.api.api.CollectionApi;
import diecast.collector.api.domain.Collection;
import diecast.collector.api.dto.CollectionSaveRequest;
import diecast.collector.api.service.CollectionService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Objects;

@Controller("/collection")
public class CollectionController implements CollectionApi {
    private CollectionService collectionService;

    public CollectionController(CollectionService collectionService) {
        this.collectionService = collectionService;
    }

    @Override
    public HttpResponse<Collection> getById(Integer id) {
        var collection = collectionService.getById(id).orElse(null);

        return Objects.nonNull(collection)
                ? HttpResponse
                .ok(collection)
                : HttpResponse
                .notFound();
    }

    @Override
    public List<Collection> getAll() {
        return collectionService.getAll();
    }

    @Override
    public HttpResponse<Collection> create(@Body @Valid CollectionSaveRequest request) {
        var collection = collectionService.create(request);

        return HttpResponse
                .created(collection, location(collection.getId()));
    }

    @Override
    @Transactional
    public HttpResponse<Collection> update(Integer id, @Body @Valid CollectionSaveRequest request) {
        var collection = collectionService.getById(id).orElse(null);

        return Objects.nonNull(collection)
                ? HttpResponse
                .ok(collectionService.update(collection, request))
                .headers(headers -> headers.location(location(collection.getId())))
                : HttpResponse
                .notFound();
    }

    @Override
    @Transactional
    public HttpResponse<Collection> delete(Integer id) {
        var collection = collectionService.getById(id).orElse(null);
        if (collection == null) {
            return HttpResponse.notFound();
        }
        collectionService.delete(collection);
        return HttpResponse.ok(collection);
    }

    private URI location(Integer id) {
        return URI.create("/collection/" + id);
    }
}
