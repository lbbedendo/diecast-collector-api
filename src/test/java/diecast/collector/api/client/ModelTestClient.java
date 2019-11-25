package diecast.collector.api.client;

import diecast.collector.api.api.ModelApi;
import io.micronaut.http.client.annotation.Client;

@Client("/model")
public interface ModelTestClient extends ModelApi {
}
