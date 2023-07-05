package kg.kubatbekov.carrestservice.service;

import kg.kubatbekov.carrestservice.DTO.CarDTO;
import kg.kubatbekov.carrestservice.DTO.CarDTOMapper;
import kg.kubatbekov.carrestservice.model.Car;
import kg.kubatbekov.carrestservice.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {
    private final CarRepository carRepository;
    private final CarDTOMapper carDTOMapper;

    @Autowired
    public CarService(CarRepository carRepository, CarDTOMapper carDTOMapper) {
        this.carRepository = carRepository;
        this.carDTOMapper = carDTOMapper;
    }

    public List<Car> findAll() {
        return carRepository.findAll();
    }

    public void save(CarDTO carDTO) {
        carRepository.save(carDTOMapper.apply(carDTO));
    }

    public void save(Car car) {
        carRepository.save(car);
    }

    public Car findById(int id) {
        return carRepository.findById(id).get();
    }

    public Car update(CarDTO carDTO) {
        return carRepository.save(carDTOMapper.apply(carDTO));
    }

    public void deleteById(int id) {
        carRepository.deleteById(id);
    }


    public void saveAll(List<Car> cars) {
        carRepository.saveAll(cars);
    }
}
