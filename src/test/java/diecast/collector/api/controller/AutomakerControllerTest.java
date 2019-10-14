package diecast.collector.api.controller;

import diecast.collector.api.client.AutomakerTestClient;
import diecast.collector.api.domain.Automaker;
import io.micronaut.context.ApplicationContext;
import io.micronaut.runtime.server.EmbeddedServer;
import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
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

    @Test
    void testIsRunning() {
        assertTrue(server.isRunning());
    }

    @Test
    public void testCreateAutomaker() {
        var automaker = new Automaker();
        automaker.setName("Honda");
        automaker.setCountry("Japan");
        var response = client.save(automaker);
        assertNotNull(response.body());
        assertNotNull(response.body().getId());
    }

}
