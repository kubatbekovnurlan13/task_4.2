package kg.kubatbekov.carrestservice.controller;


import kg.kubatbekov.carrestservice.model.Manufacturer;
import kg.kubatbekov.carrestservice.parser.CsvSaver;
import kg.kubatbekov.carrestservice.service.ManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/manufacturers")
public class ManufacturerController {
    private final ManufacturerService manufacturerService;
    private final CsvSaver csvSaver;

    @Autowired
    public ManufacturerController(ManufacturerService manufacturerService, CsvSaver csvSaver) {
        this.manufacturerService = manufacturerService;
        this.csvSaver = csvSaver;
    }

    @GetMapping(path = "/findAll", produces = "application/json")
    public List<Manufacturer> findAll() {
        csvSaver.run();

        return manufacturerService.findAll();
    }

    @PostMapping(path = "/save", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> save(@RequestBody Manufacturer manufacturer) {
        csvSaver.run();

        manufacturerService.save(manufacturer);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(path = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Manufacturer> update(@RequestBody Manufacturer manufacturer) {
        csvSaver.run();

        Manufacturer updated = manufacturerService.update(manufacturer);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(updated);
        }
    }

    @GetMapping(path = "/{id}/findById", produces = "application/json")
    public ResponseEntity<Manufacturer> get(@PathVariable int id) {
        csvSaver.run();

        Manufacturer found = manufacturerService.findById(id);
        if (found == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(found);
        }
    }

    @DeleteMapping(path = "/{id}/delete", produces = "application/json")
    public ResponseEntity<Object> delete(@PathVariable int id) {
        csvSaver.run();

        manufacturerService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

//    {
//        "manufacturerName":"new manufacturer",
//            "car":null
//    }
}
