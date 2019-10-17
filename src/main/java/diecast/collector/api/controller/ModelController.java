package diecast.collector.api.controller;

import diecast.collector.api.domain.Model;
import diecast.collector.api.dto.ModelSaveRequest;
import diecast.collector.api.service.ModelService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;

import javax.transaction.Transactional;
import java.net.URI;
import java.util.List;
import java.util.Objects;

@Controller("/model")
public class ModelController {
    private ModelService modelService;

    public ModelController(ModelService modelService) {
        this.modelService = modelService;
    }

    @Get("/{id}")
    public HttpResponse<Model> getById(Integer id) {
        var model = modelService.getById(id).orElse(null);

        return Objects.nonNull(model)
                ? HttpResponse
                .ok(model)
                : HttpResponse
                .notFound();
    }

    @Get("/")
    public List<Model> getAll() {
        return modelService.getAll();
    }

    @Post("/")
    public HttpResponse<Model> create(ModelSaveRequest request) {
        var model = modelService.create(request);

        return HttpResponse
                .created(model, location(model.getId()));
    }

    @Put("/{id}")
    @Transactional
    public HttpResponse<Model> update(Integer id, ModelSaveRequest request) {
        var model = modelService.getById(id).orElse(null);

        return Objects.nonNull(model)
                ? HttpResponse
                .ok(modelService.update(model, request))
                .headers(headers -> headers.location(location(model.getId())))
                : HttpResponse
                .notFound();
    }

    @Delete("/{id}")
    @Transactional
    public HttpResponse<Model> delete(Integer id) {
        var model = modelService.getById(id).orElse(null);
        if (model == null) {
            return HttpResponse.notFound();
        }
        modelService.delete(model);
        return HttpResponse.ok(model);
    }

    private URI location(Integer id) {
        return URI.create("/model/" + id);
    }
}
