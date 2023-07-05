package kg.kubatbekov.carrestservice.service;

import kg.kubatbekov.carrestservice.model.Manufacturer;
import kg.kubatbekov.carrestservice.repository.ManufacturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManufacturerService {
    private final ManufacturerRepository manufacturerRepository;

    @Autowired
    public ManufacturerService(ManufacturerRepository manufacturerRepository) {
        this.manufacturerRepository = manufacturerRepository;
    }

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

    public Manufacturer findByManufacturerName(String name){
        return manufacturerRepository.findByManufacturerName(name).get();
    }
}
