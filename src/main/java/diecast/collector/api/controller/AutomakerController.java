package diecast.collector.api.controller;

import diecast.collector.api.domain.Automaker;
import diecast.collector.api.dto.AutomakerSaveRequest;
import diecast.collector.api.service.AutomakerService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.validation.Validated;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@Validated
@Controller("/automaker")
public class AutomakerController {
    private AutomakerService automakerService;

    public AutomakerController(AutomakerService automakerService) {
        this.automakerService = automakerService;
    }

    @Get("/{id}")
    public Automaker getById(Integer id) {
        return automakerService.getById(id);
    }

    @Get("/")
    public List<Automaker> getAll() {
        return automakerService.getAll();
    }

    @Post("/")
    public HttpResponse<Automaker> save(@Body @Valid AutomakerSaveRequest request) {
        var automaker = automakerService.save(request);

        return HttpResponse
                .created(automaker)
                .headers(headers -> headers.location(location(automaker.getId())));

    }

    private URI location(Integer id) {
        return URI.create("/automaker/" + id);
    }
}
