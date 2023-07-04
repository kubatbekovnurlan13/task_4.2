package kg.kubatbekov.carrestservice.service;

import kg.kubatbekov.carrestservice.model.Model;
import kg.kubatbekov.carrestservice.repository.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModelService {
    private final ModelRepository modelRepository;

    @Autowired
    public ModelService(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }

    public List<Model> findAll() {
        return modelRepository.findAll();
    }

    public void save(Model model) {
        modelRepository.save(model);
    }

    public Model findById(int id) {
        return modelRepository.findById(id).get();
    }

    public Model update(Model model) {
        return modelRepository.save(model);
    }

    public void deleteById(int id) {
        modelRepository.deleteById(id);
    }
}
