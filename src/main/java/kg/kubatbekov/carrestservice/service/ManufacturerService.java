package kg.kubatbekov.carrestservice.service;

import kg.kubatbekov.carrestservice.model.Manufacturer;
import kg.kubatbekov.carrestservice.repository.ManufacturerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ManufacturerService {
    private final ManufacturerRepository manufacturerRepository;

    public List<Manufacturer> findAll() {
        return manufacturerRepository.findAll();
    }

    public void save(Manufacturer manufacturer) {
        manufacturerRepository.save(manufacturer);
    }

    public Manufacturer findById(int id) {
        return manufacturerRepository.findById(id).get();
    }

    public Manufacturer update(Manufacturer group) {
        return manufacturerRepository.save(group);
    }

    public void deleteById(int id) {
        manufacturerRepository.deleteById(id);
    }

    public void saveAll(List<Manufacturer> manufacturers) {
        manufacturerRepository.saveAll(manufacturers);
    }

    public List<Manufacturer> findManufacturersByPagination(int pageNo, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNo, pageSize, Sort.by("manufacturerName").ascending());
        Page<Manufacturer> pagingManufacturers = manufacturerRepository.findAll(pageRequest);
        return pagingManufacturers.getContent();
    }
}
