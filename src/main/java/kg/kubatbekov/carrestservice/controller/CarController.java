package kg.kubatbekov.carrestservice.controller;

import kg.kubatbekov.carrestservice.model.Car;
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

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping(path = "/findAll", produces = "application/json")
    public List<Car> findAll() {
        return carService.findAll();
    }

    @PostMapping(path = "/save", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> save(@RequestBody Car car) {
        carService.save(car);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(path = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Car> update(@RequestBody Car car) {
        Car updated = carService.update(car);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(updated);
        }
    }

    @GetMapping(path = "/{id}/findById", produces = "application/json")
    public ResponseEntity<Car> get(@PathVariable int id) {
        Car found = carService.findById(id);
        if (found == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(found);
        }
    }

    @DeleteMapping(path = "/{id}/delete", produces = "application/json")
    public ResponseEntity<Object> delete(@PathVariable int id) {
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
