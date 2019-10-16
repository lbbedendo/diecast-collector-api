package diecast.collector.api.controller;

import diecast.collector.api.client.AutomakerTestClient;
import diecast.collector.api.dto.AutomakerSaveRequest;
import io.micronaut.context.ApplicationContext;
import io.micronaut.http.HttpStatus;
import io.micronaut.runtime.server.EmbeddedServer;
import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@MicronautTest
public class AutomakerControllerTest {

    private static EmbeddedServer server;
    private static AutomakerTestClient client;

    @BeforeAll
    public static void setUp() {
        server = ApplicationContext.run(EmbeddedServer.class);
        client = server.getApplicationContext().createBean(AutomakerTestClient.class, server.getURL());
    }

    @AfterAll
    public static void cleanUp() {
        if (server != null) {
            server.stop();
        }
    }

    @Test
    void testIsRunning() {
        assertTrue(server.isRunning());
    }

    @Test
    public void testCreateAutomakerWhenBodyIsValid() {
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
    public void testUpdateAutomakerWhenBodyIsValid() {
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
}
