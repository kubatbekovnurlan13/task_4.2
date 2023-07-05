package kg.kubatbekov.carrestservice.controller;

import kg.kubatbekov.carrestservice.model.Model;
import kg.kubatbekov.carrestservice.parser.CsvSaver;
import kg.kubatbekov.carrestservice.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/models")
public class ModelController {
    private final ModelService modelService;
    private final CsvSaver csvSaver;

    @Autowired
    public ModelController(ModelService modelService, CsvSaver csvSaver) {
        this.modelService = modelService;
        this.csvSaver = csvSaver;
    }

    @GetMapping(path = "/findAll", produces = "application/json")
    public List<Model> findAll() {
        csvSaver.run();

        return modelService.findAll();
    }

    //    http://localhost:8081/api/v1/cars/findAllWithPaging?pageNo=0&pageSize=20
    @GetMapping(path = "/findAllWithPaging", produces = "application/json")
    public List<Model> getModelsWithPaging(@RequestParam(defaultValue = "0") Integer pageNo,
                                           @RequestParam(defaultValue = "10") Integer pageSize) {
        csvSaver.run();
        return modelService.findModelsByPagination(pageNo, pageSize);
    }

    @PostMapping(path = "/save", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> saveModel(@RequestBody Model model) {
        csvSaver.run();

        modelService.save(model);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(path = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Model> updateModel(@RequestBody Model model) {
        csvSaver.run();

        Model updatedModel = modelService.update(model);
        if (updatedModel == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(updatedModel);
        }
    }

    @GetMapping(path = "/{id}/findById", produces = "application/json")
    public ResponseEntity<Model> getModel(@PathVariable int id) {
        csvSaver.run();

        Model foundModel = modelService.findById(id);
        if (foundModel == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(foundModel);
        }
    }

    @DeleteMapping(path = "/{id}/delete", produces = "application/json")
    public ResponseEntity<Object> deleteModel(@PathVariable int id) {
        csvSaver.run();

        modelService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

//    {
//        "modelName":"new model",
//            "car":null
//    }
}
