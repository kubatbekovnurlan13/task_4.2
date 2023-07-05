package kg.kubatbekov.carrestservice.controller;

import kg.kubatbekov.carrestservice.DTO.CarDTO;
import kg.kubatbekov.carrestservice.model.Car;
import kg.kubatbekov.carrestservice.parser.CsvSaver;
import kg.kubatbekov.carrestservice.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/cars")
public class CarController {
    private final CarService carService;
    private final CsvSaver csvSaver;

    @Autowired
    public CarController(CarService carService, CsvSaver csvSaver) {
        this.carService = carService;
        this.csvSaver = csvSaver;
    }

    //    http://localhost:8081/api/v1/cars?manufacturer=Audi&model=Q3&minYear=2000&maxYear=2023&category=SUV
    @GetMapping(produces = "application/json")
    public List<Car> findCarsWithParameters(
            @RequestParam(value = "manufacturer", defaultValue = "default") String manufacturer,
            @RequestParam(value = "model", defaultValue = "default") String model,
            @RequestParam(value = "category", defaultValue = "default") String category,
            @RequestParam(value = "minYear", defaultValue = "0") int minYear,
            @RequestParam(value = "maxYear", defaultValue = "0") int maxYear
    ) {
        csvSaver.run();

        return carService.findCarsWithParameters(manufacturer, model, category, minYear, maxYear);
    }

    @GetMapping(path = "/findAll", produces = "application/json")
    public List<Car> findAll() {
        csvSaver.run();

        List<Car> carsNew = carService.findAll();
        System.out.println("carsNew len: " + carsNew.size());
        return carsNew;
    }

    //    http://localhost:8081/api/v1/cars/findAllWithPaging?pageNo=0&pageSize=20
    @GetMapping(path = "/findAllWithPaging", produces = "application/json")
    public List<Car> getCarsWithPaging(@RequestParam(defaultValue = "0") Integer pageNo,
                                       @RequestParam(defaultValue = "10") Integer pageSize) {
        csvSaver.run();
        return carService.findCarsByPagination(pageNo, pageSize);
    }

    @PostMapping(path = "/save", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> save(@RequestBody CarDTO carDTO) {
        csvSaver.run();

        carService.save(carDTO);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(path = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Car> update(@RequestBody CarDTO carDTO) {
        csvSaver.run();

        Car updated = carService.update(carDTO);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(updated);
        }
    }

    @GetMapping(path = "/{id}/findById", produces = "application/json")
    public ResponseEntity<Car> get(@PathVariable int id) {
        csvSaver.run();

        Car found = carService.findById(id);
        if (found == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(found);
        }
    }

    @DeleteMapping(path = "/{id}/delete", produces = "application/json")
    public ResponseEntity<Object> delete(@PathVariable int id) {
        csvSaver.run();

        carService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


//    {
//           "year": 2100,
//           "manufacturerId": 6,
//           "modelId": 6,
//           "categoryId": 6
//    }
}
