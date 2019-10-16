package diecast.collector.api.controller;

import diecast.collector.api.client.AutomakerTestClient;
import diecast.collector.api.dto.AutomakerSaveRequest;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@MicronautTest
public class AutomakerControllerTest {

    @Inject
    AutomakerTestClient client;

    @Test
    public void createAutomaker_created_whenBodyIsValid() {
        var request = new AutomakerSaveRequest("Honda", "Japan");
        var response = client.create(request);
        assertThat(response.code()).isEqualTo(HttpStatus.CREATED.getCode());
        var body = response.body();
        assertThat(body).isNotNull();
        assertThat(body.getId()).isNotNull();
        assertThat(body.getName()).isEqualTo("Honda");
        assertThat(body.getCountry()).isEqualTo("Japan");
    }

    @Test
    public void createAutomaker_badRequest_whenNameIsNull() {
        var request = new AutomakerSaveRequest(null, "Brazil");
        assertThatExceptionOfType(HttpClientResponseException.class)
                .isThrownBy(() -> client.create(request))
                .withMessage("request.name: não pode estar em branco");
    }

    @Test
    public void createAutomaker_badRequest_whenNameIsBlank() {
        var request = new AutomakerSaveRequest("", "Brazil");
        assertThatExceptionOfType(HttpClientResponseException.class)
                .isThrownBy(() -> client.create(request))
                .withMessage("request.name: não pode estar em branco");
    }

    @Test
    public void updateAutomaker_ok_whenBodyIsValid() {
        var responseCreated = client.create(new AutomakerSaveRequest("Chvrolet", "Canada"));
        assertThat(responseCreated.code()).isEqualTo(HttpStatus.CREATED.getCode());
        var itemCreated = responseCreated.body();
        assertThat(itemCreated).isNotNull();
        var responseUpdated = client.update(itemCreated.getId(), new AutomakerSaveRequest("Chevrolet", "United States"));
        assertThat(responseUpdated.code()).isEqualTo(HttpStatus.OK.getCode());
        var itemUpdated = responseUpdated.body();
        assertThat(itemUpdated).isNotNull();
        assertThat(itemUpdated.getName()).isEqualTo("Chevrolet");
        assertThat(itemUpdated.getCountry()).isEqualTo("United States");
    }

    @Test
    public void updateAutomaker_notFound_whenAutomakerDoesNotExists() {
        var request = new AutomakerSaveRequest("Gurgel", "Brazil");
        var response = client.update(999, request);
        assertThat(response.code()).isEqualTo(HttpStatus.NOT_FOUND.getCode());
    }

    @Test
    public void delete_ok_whenAutomakerExists() {
        var responseCreated = client.create(new AutomakerSaveRequest("BMW", "Germany"));
        assertThat(responseCreated.code()).isEqualTo(HttpStatus.CREATED.getCode());
        var itemCreated = responseCreated.body();
        assertThat(itemCreated).isNotNull();
        var responseDeleted = client.delete(itemCreated.getId());
        assertThat(responseDeleted.code()).isEqualTo(HttpStatus.OK.getCode());
    }

    @Test
    public void delete_notFound_whenAutomakerDoesNotExists() {
        var response = client.delete(999);
        assertThat(response.code()).isEqualTo(HttpStatus.NOT_FOUND.getCode());
    }

    @Test
    public void getById_ok_whenAutomakerExists() {
        var responseCreated = client.create(new AutomakerSaveRequest("Porsche", "Germany"));
        assertThat(responseCreated.code()).isEqualTo(HttpStatus.CREATED.getCode());
        var itemCreated = responseCreated.body();
        assertThat(itemCreated).isNotNull();
        var responseFound = client.getById(itemCreated.getId());
        assertThat(responseFound.code()).isEqualTo(HttpStatus.OK.getCode());
    }

    @Test
    public void getById_notFound_whenAutomakerDoesNotExists() {
        var response = client.getById(999);
        assertThat(response.code()).isEqualTo(HttpStatus.NOT_FOUND.getCode());
    }
}
