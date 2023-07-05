package kg.kubatbekov.carrestservice.controller;

import kg.kubatbekov.carrestservice.DTO.CarDTO;
import kg.kubatbekov.carrestservice.model.Car;
import kg.kubatbekov.carrestservice.parser.CsvSaver;
import kg.kubatbekov.carrestservice.service.CarService;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/cars")
public class CarController {
    private final CarService carService;
    @Value("${csv.file}")
    private String pathOfCsv;
    private final CsvSaver csvSaver;

    @Autowired
    public CarController(CarService carService, CsvSaver csvSaver) {
        this.carService = carService;
        this.csvSaver = csvSaver;
    }

    @GetMapping(path = "/findAll", produces = "application/json")
    public List<Car> findAll() {
        csvSaver.run();

        List<Car> carsNew = carService.findAll();
        System.out.println("carsNew len: " + carsNew.size());
        return carsNew;
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
