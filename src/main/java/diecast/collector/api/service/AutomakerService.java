package diecast.collector.api.service;

import diecast.collector.api.domain.Automaker;
import diecast.collector.api.dto.AutomakerSaveRequest;
import diecast.collector.api.dto.filters.AutomakerFilters;
import diecast.collector.api.repository.AbstractAutomakerRepository;

import javax.inject.Singleton;
import java.util.List;
import java.util.Optional;

@Singleton
public class AutomakerService {
    private AbstractAutomakerRepository automakerRepository;

    public AutomakerService(AbstractAutomakerRepository automakerRepository) {
        this.automakerRepository = automakerRepository;
    }

    public Optional<Automaker> getById(Integer id) {
        return automakerRepository.findById(id);
    }

    public List<Automaker> findAll(AutomakerFilters filters) {
        return automakerRepository.findAll(filters);
    }

    public Automaker create(AutomakerSaveRequest request) {
        var automaker = new Automaker();
        automaker.setName(request.getName());
        automaker.setCountry(request.getCountry());
        return automakerRepository.save(automaker);
    }

    public Automaker update(Automaker automaker, AutomakerSaveRequest request) {
        automaker.setName(request.getName());
        automaker.setCountry(request.getCountry());
        return automakerRepository.save(automaker);
    }

    public void delete(Automaker automaker) {
        automakerRepository.delete(automaker);
    }
}