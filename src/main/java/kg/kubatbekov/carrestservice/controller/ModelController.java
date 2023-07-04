package kg.kubatbekov.carrestservice.controller;

import kg.kubatbekov.carrestservice.model.Model;
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

    @Autowired
    public ModelController(ModelService modelService) {
        this.modelService = modelService;
    }

    @GetMapping(path = "/findAll", produces = "application/json")
    public List<Model> findAll() {
        return modelService.findAll();
    }

    @PostMapping(path = "/save", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> saveModel(@RequestBody Model model) {
        modelService.save(model);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(path = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Model> updateModel(@RequestBody Model model) {
        Model updatedModel = modelService.update(model);
        if (updatedModel == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(updatedModel);
        }
    }

    @GetMapping(path = "/{id}/findById", produces = "application/json")
    public ResponseEntity<Model> getModel(@PathVariable int id) {
        Model foundModel = modelService.findById(id);
        if (foundModel == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(foundModel);
        }
    }

    @DeleteMapping(path = "/{id}/delete", produces = "application/json")
    public ResponseEntity<Object> deleteModel(@PathVariable int id) {
        modelService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

//    {
//        "modelName":"new model",
//            "car":null
//    }
}
