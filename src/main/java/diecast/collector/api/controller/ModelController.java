package diecast.collector.api.controller;

import diecast.collector.api.api.ModelApi;
import diecast.collector.api.domain.Model;
import diecast.collector.api.dto.ModelSaveRequest;
import diecast.collector.api.service.ModelService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Objects;

@Controller("/model")
public class ModelController implements ModelApi {
    private ModelService modelService;

    public ModelController(ModelService modelService) {
        this.modelService = modelService;
    }

    @Override
    public HttpResponse<Model> getById(Integer id) {
        var model = modelService.getById(id);

        return model.isPresent()
                ? HttpResponse
                .ok(model.get())
                : HttpResponse
                .notFound();
    }

    @Override
    public List<Model> getAll() {
        return modelService.getAll();
    }

    @Override
    public HttpResponse<Model> create(@Valid @Body ModelSaveRequest request) {
        var model = modelService.create(request);

        return HttpResponse
                .created(model, location(model.getId()));
    }

    @Override
    @Transactional
    public HttpResponse<Model> update(Integer id, @Valid @Body ModelSaveRequest request) {
        var model = modelService.getById(id);

        return model.isPresent()
                ? HttpResponse
                .ok(modelService.update(model.get(), request))
                .headers(headers -> headers.location(location(id)))
                : HttpResponse
                .notFound();
    }

    @Override
    @Transactional
    public HttpResponse<Model> delete(Integer id) {
        var modelOptional = modelService.getById(id);
        if (modelOptional.isEmpty()) {
            return HttpResponse.notFound();
        }
        var model = modelOptional.get();
        modelService.delete(model);
        return HttpResponse.ok(model);
    }

    private URI location(Integer id) {
        return URI.create("/model/" + id);
    }
}
