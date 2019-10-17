package diecast.collector.api.client;

import diecast.collector.api.domain.Model;
import diecast.collector.api.dto.ModelSaveRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.http.client.annotation.Client;

@Client("/model")
public interface ModelTestClient {
    @Get("/{id}")
    HttpResponse<Model> getById(Integer id);

    @Post("/")
    HttpResponse<Model> create(@Body ModelSaveRequest request);

    @Put("/{id}")
    HttpResponse<Model> update(Integer id, @Body ModelSaveRequest request);

    @Delete("/{id}")
    HttpResponse<Model> delete(Integer id);
}
