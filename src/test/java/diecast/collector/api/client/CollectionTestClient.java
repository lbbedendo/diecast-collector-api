package diecast.collector.api.client;

import diecast.collector.api.api.CollectionApi;
import io.micronaut.http.client.annotation.Client;

@Client("/collection")
public interface CollectionTestClient extends CollectionApi {
}
