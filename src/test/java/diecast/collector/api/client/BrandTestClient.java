package diecast.collector.api.client;

import diecast.collector.api.api.BrandApi;
import io.micronaut.http.client.annotation.Client;

@Client("/brand")
public interface BrandTestClient extends BrandApi {
}
