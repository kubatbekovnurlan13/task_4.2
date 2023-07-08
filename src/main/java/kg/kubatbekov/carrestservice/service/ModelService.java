package kg.kubatbekov.carrestservice.service;

import kg.kubatbekov.carrestservice.model.Model;
import kg.kubatbekov.carrestservice.repository.ModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ModelService {
    private final ModelRepository modelRepository;

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

    public void saveAll(List<Model> models) {
        modelRepository.saveAll(models);
    }

    public List<Model> findModelsByPagination(int pageNo, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNo, pageSize, Sort.by("modelName").ascending());

        Page<Model> pagingModels = modelRepository.findAll(pageRequest);
        return pagingModels.getContent();
    }
}
