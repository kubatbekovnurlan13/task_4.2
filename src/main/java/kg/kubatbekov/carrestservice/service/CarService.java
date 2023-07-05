package kg.kubatbekov.carrestservice.service;

import kg.kubatbekov.carrestservice.DTO.CarDTO;
import kg.kubatbekov.carrestservice.DTO.CarDTOMapper;
import kg.kubatbekov.carrestservice.model.Car;
import kg.kubatbekov.carrestservice.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    public List<Car> findCarsByPagination(int pageNo, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNo, pageSize);
        Page<Car> pagingCar = carRepository.findAll(pageRequest);
        return pagingCar.getContent();
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

    public List<Car> findCarsWithParameters(String manufacturer, String model, String category,
                                            int minYear, int maxYear) {
        List<Car> cars = findAll();

        if (!manufacturer.equals("default")) {
            cars = cars.stream()
                    .filter(car -> car.getManufacturer().getManufacturerName().equals(manufacturer))
                    .toList();
        }
        if (!model.equals("default")) {
            cars = cars.stream().filter(car -> car.getModel().getModelName().equals(model)).toList();
        }
        if (!category.equals("default")) {
            cars = cars.stream()
                    .filter(car -> car.getCategory().getCategoryName().equals(category)).toList();
        }
        if (minYear != 0) {
            cars = cars.stream().filter(car -> car.getYear() >= minYear).toList();
        }
        if (maxYear != 0) {
            cars = cars.stream().filter(car -> car.getYear() <= maxYear).toList();
        }
        return cars;
    }

}
