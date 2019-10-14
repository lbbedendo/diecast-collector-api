package diecast.collector.api.controller;

import diecast.collector.api.domain.Automaker;
import diecast.collector.api.dto.AutomakerSaveRequest;
import diecast.collector.api.service.AutomakerService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.validation.Validated;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Objects;

@Validated
@Controller("/automaker")
public class AutomakerController {
    private AutomakerService automakerService;

    public AutomakerController(AutomakerService automakerService) {
        this.automakerService = automakerService;
    }

    @Get("/{id}")
    public Automaker getById(Integer id) {
        return automakerService.getById(id).orElse(null);
    }

    @Get("/")
    public List<Automaker> getAll() {
        return automakerService.getAll();
    }

    @Post("/")
    public HttpResponse<Automaker> save(@Body @Valid AutomakerSaveRequest request) {
        var automaker = automakerService.create(request);

        return HttpResponse
                .created(automaker)
                .headers(headers -> headers.location(location(automaker.getId())));

    }

    @Put("/{id}")
    @Transactional
    public HttpResponse<Automaker> update(Integer id, @Body @Valid AutomakerSaveRequest request) {
        var automaker = automakerService.getById(id).orElse(null);
        return Objects.nonNull(automaker)
                ? HttpResponse
                .ok(automakerService.update(automaker, request))
                .headers(headers -> headers.location(location(id)))
                : HttpResponse
                .notFound();
    }

    private URI location(Integer id) {
        return URI.create("/automaker/" + id);
    }
}
