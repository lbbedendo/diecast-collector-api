package diecast.collector.api.controller;

import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

@Controller("/brand")
public class BrandController {

    @Get("/")
    public HttpStatus index() {
        return HttpStatus.OK;
    }
}