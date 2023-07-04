package kg.kubatbekov.carrestservice.service;

import kg.kubatbekov.carrestservice.model.Car;
import kg.kubatbekov.carrestservice.repository.CarRepository;
import kg.kubatbekov.carrestservice.repository.CategoryRepository;
import kg.kubatbekov.carrestservice.repository.ManufacturerRepository;
import kg.kubatbekov.carrestservice.repository.ModelRepository;
import org.hibernate.query.sql.internal.ParameterRecognizerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {
    private final CarRepository carRepository;
    private final CategoryRepository categoryRepository;
    private final ModelRepository modelRepository;
    private final ManufacturerRepository manufacturerRepository;

    @Autowired
    public CarService(CarRepository carRepository,
                      CategoryRepository categoryRepository,
                      ModelRepository modelRepository,
                      ManufacturerRepository manufacturerRepository) {
        this.carRepository = carRepository;
        this.categoryRepository = categoryRepository;
        this.modelRepository = modelRepository;
        this.manufacturerRepository = manufacturerRepository;
    }

    public List<Car> findAll() {
        return carRepository.findAll();
    }

    public void save(Car car) {
        carRepository.save(car);
    }



    public Car findById(int id) {
        return carRepository.findById(id).get();
    }

    public Car update(Car car) {
        return carRepository.save(car);
    }

    public void deleteById(int id) {
        carRepository.deleteById(id);
    }
}
