package diecast.collector.api.service;

import com.google.common.collect.Lists;
import diecast.collector.api.domain.Model;
import diecast.collector.api.dto.ModelSaveRequest;
import diecast.collector.api.repository.ModelRepository;

import javax.inject.Singleton;
import java.util.List;
import java.util.Optional;

@Singleton
public class ModelService {
    private ModelRepository modelRepository;

    public ModelService(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }

    public Optional<Model> getById(Integer id) {
        return modelRepository.findById(id);
    }

    public List<Model> getAll() {
        return Lists.newArrayList(modelRepository.findAll());
    }

    public Model create(ModelSaveRequest request) {
        var model = new Model(
                request.getName(),
                request.getModelYear(),
                request.getScale(),
                request.getColorRgba(),
                request.getAutomaker(),
                request.getCollection(),
                request.getBrand());
        return modelRepository.save(model);
    }

    public Model update(Model model, ModelSaveRequest request) {
        model.setName(request.getName());
        model.setModelYear(request.getModelYear());
        model.setScale(request.getScale());
        model.setColorRgba(request.getColorRgba());
        model.setAutomaker(request.getAutomaker());
        model.setCollection(request.getCollection());
        model.setBrand(request.getBrand());
        return modelRepository.save(model);
    }

    public void delete(Model model) {
        modelRepository.delete(model);
    }
}
