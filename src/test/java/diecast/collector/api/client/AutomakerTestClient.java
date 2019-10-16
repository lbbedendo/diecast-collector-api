package diecast.collector.api.client;

import diecast.collector.api.domain.Automaker;
import diecast.collector.api.dto.AutomakerSaveRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import io.micronaut.http.client.annotation.Client;

@Client("/automaker")
public interface AutomakerTestClient {
    @Get("/{id}")
    Automaker getById(Integer id);

    @Post("/")
    HttpResponse<Automaker> create(@Body AutomakerSaveRequest request);

    @Put("/{id}")
    HttpResponse<Automaker> update(Integer id, @Body AutomakerSaveRequest request);
}
