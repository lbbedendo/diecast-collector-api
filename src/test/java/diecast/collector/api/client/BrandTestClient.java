package diecast.collector.api.client;

import diecast.collector.api.domain.Brand;
import diecast.collector.api.dto.BrandSaveRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.http.client.annotation.Client;

@Client("/brand")
public interface BrandTestClient {
    @Get("/{id}")
    HttpResponse<Brand> getById(Integer id);

    @Post("/")
    HttpResponse<Brand> create(@Body BrandSaveRequest request);

    @Put("/{id}")
    HttpResponse<Brand> update(Integer id, @Body BrandSaveRequest request);

    @Delete("/{id}")
    HttpResponse<Brand> delete(Integer id);
}
