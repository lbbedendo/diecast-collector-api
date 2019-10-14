package diecast.collector.api.service;

import com.google.common.collect.Lists;
import diecast.collector.api.domain.Automaker;
import diecast.collector.api.dto.AutomakerSaveRequest;
import diecast.collector.api.repository.AutomakerRepository;

import javax.inject.Singleton;
import java.util.List;

@Singleton
public class AutomakerService {
    private AutomakerRepository automakerRepository;

    public AutomakerService(AutomakerRepository automakerRepository) {
        this.automakerRepository = automakerRepository;
    }

    public Automaker getById(Integer id) {
        return automakerRepository.findById(id).orElse(null);
    }

    public List<Automaker> getAll() {
        return Lists.newArrayList(automakerRepository.findAll());
    }

    public Automaker save(AutomakerSaveRequest request) {
        var automaker = new Automaker();
        automaker.setName(request.getName());
        automaker.setCountry(request.getCountry());
        return automakerRepository.save(automaker);
    }
}