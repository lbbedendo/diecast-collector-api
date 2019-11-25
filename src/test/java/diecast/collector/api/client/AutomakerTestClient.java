package diecast.collector.api.client;

import diecast.collector.api.api.AutomakerApi;
import io.micronaut.http.client.annotation.Client;

@Client("/automaker")
public interface AutomakerTestClient extends AutomakerApi {
}
