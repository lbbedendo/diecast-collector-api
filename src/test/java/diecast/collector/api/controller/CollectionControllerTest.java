package diecast.collector.api.controller;

import diecast.collector.api.client.CollectionTestClient;
import diecast.collector.api.domain.Collection;
import diecast.collector.api.dto.CollectionSaveRequest;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@MicronautTest
public class CollectionControllerTest {
    @Inject
    CollectionTestClient client;

    @Test
    public void createCollection_created_whenBodyIsValid() {
        var request = new CollectionSaveRequest("Factory Fresh", 2018);
        var response = client.create(request);
        assertThat(response.code()).isEqualTo(HttpStatus.CREATED.getCode());
        var body = response.body();
        assertThat(body).isNotNull();
        assertThat(body.getId()).isNotNull();
        assertThat(body.getName()).isEqualTo("Factory Fresh");
        assertThat(body.getYear()).isEqualTo(2018);
    }

    @Test
    public void createCollection_badRequest_whenNameIsNull() {
        var request = new CollectionSaveRequest(null, 2018);
        assertThatExceptionOfType(HttpClientResponseException.class)
                .isThrownBy(() -> client.create(request))
                .withMessage("request.name: não pode estar em branco");
    }

    @Test
    public void createCollection_badRequest_whenNameIsBlank() {
        var request = new CollectionSaveRequest("", 2018);
        assertThatExceptionOfType(HttpClientResponseException.class)
                .isThrownBy(() -> client.create(request))
                .withMessage("request.name: não pode estar em branco");
    }

    @Test
    public void updateCollection_ok_whenBodyIsValid() {
        var collection = createCollection("Fatory Fresh", 2018);
        var request = new CollectionSaveRequest("Factory Fresh", 2019);
        var response = client.update(collection.getId(), request);
        assertThat(response.code()).isEqualTo(HttpStatus.OK.getCode());
        var body = response.body();
        assertThat(body).isNotNull();
        assertThat(body.getId()).isEqualTo(collection.getId());
        assertThat(body.getName()).isEqualTo("Factory Fresh");
        assertThat(body.getYear()).isEqualTo(2019);
    }

    @Test
    public void updateCollection_notFound_whenCollectionDoesNotExists() {
        var request = new CollectionSaveRequest("Factory Fresh", 2019);
        var response = client.update(999, request);
        assertThat(response.code()).isEqualTo(HttpStatus.NOT_FOUND.getCode());
    }

    @Test
    public void delete_ok_whenCollectionExists() {
        var collection = createCollection("Street Tuners", 2000);
        var response = client.delete(collection.getId());
        assertThat(response.code()).isEqualTo(HttpStatus.OK.getCode());
    }

    @Test
    public void delete_notFound_whenCollectionDoesNotExists() {
        var response = client.delete(999);
        assertThat(response.code()).isEqualTo(HttpStatus.NOT_FOUND.getCode());
    }

    @Test
    public void getById_ok_whenCollectionExists() {
        var collection = createCollection("Supercars", 2016);
        var response = client.getById(collection.getId());
        assertThat(response.code()).isEqualTo(HttpStatus.OK.getCode());
    }

    @Test
    public void getById_notFound_whenCollectionDoesNotExists() {
        var response = client.getById(999);
        assertThat(response.code()).isEqualTo(HttpStatus.NOT_FOUND.getCode());
    }

    private Collection createCollection(String name, Integer year) {
        var request = new CollectionSaveRequest(name, year);
        var response = client.create(request);
        return response.body();
    }
}
