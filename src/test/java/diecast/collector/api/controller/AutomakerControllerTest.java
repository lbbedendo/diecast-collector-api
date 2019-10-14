package diecast.collector.api.controller;

import diecast.collector.api.client.AutomakerTestClient;
import diecast.collector.api.domain.Automaker;
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
        var automaker = new Automaker();
        automaker.setName("Honda");
        automaker.setCountry("Japan");
        var response = client.create(automaker);
        assertThat(response.code()).isEqualTo(HttpStatus.CREATED.getCode());
        var body = response.body();
        assertThat(body).isNotNull();
        assertThat(body.getId()).isNotNull();
        assertThat(body.getName()).isEqualTo("Honda");
        assertThat(body.getCountry()).isEqualTo("Japan");
    }
}
