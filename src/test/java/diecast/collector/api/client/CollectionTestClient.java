package diecast.collector.api.client;

import diecast.collector.api.domain.Collection;
import diecast.collector.api.dto.CollectionSaveRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.http.client.annotation.Client;

@Client("/collection")
public interface CollectionTestClient {
    @Get("/{id}")
    HttpResponse<Collection> getById(Integer id);

    @Post("/")
    HttpResponse<Collection> create(@Body CollectionSaveRequest request);

    @Put("/{id}")
    HttpResponse<Collection> update(Integer id, @Body CollectionSaveRequest request);

    @Delete("/{id}")
    HttpResponse<Collection> delete(Integer id);
}
