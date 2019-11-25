package diecast.collector.api.api;

import diecast.collector.api.domain.Collection;
import diecast.collector.api.dto.CollectionSaveRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;

import javax.validation.Valid;
import java.util.List;

public interface CollectionApi {
    @Get("/{id}")
    HttpResponse<Collection> getById(Integer id);

    @Get("/")
    List<Collection> getAll();

    @Post("/")
    HttpResponse<Collection> create(@Body @Valid CollectionSaveRequest request);

    @Put("/{id}")
    HttpResponse<Collection> update(Integer id, @Body @Valid CollectionSaveRequest request);

    @Delete("/{id}")
    HttpResponse<Collection> delete(Integer id);
}
