package kg.kubatbekov.carrestservice.controller;

import kg.kubatbekov.carrestservice.model.Category;
import kg.kubatbekov.carrestservice.parser.CsvSaver;
import kg.kubatbekov.carrestservice.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/categories")
public class CategoryController {
    private final CategoryService categoryService;
    private final CsvSaver csvSaver;

    @Autowired
    public CategoryController(CategoryService categoryService, CsvSaver csvSaver) {
        this.categoryService = categoryService;
        this.csvSaver = csvSaver;
    }

    @GetMapping(path = "/findAll", produces = "application/json")
    public List<Category> findAll() {
        csvSaver.run();

        return categoryService.findAll();
    }

    @PostMapping(path = "/save", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> save(@RequestBody Category category) {
        csvSaver.run();

        categoryService.save(category);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(path = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Category> update(@RequestBody Category category) {
        csvSaver.run();

        Category updatedCategory = categoryService.update(category);
        if (updatedCategory == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(updatedCategory);
        }
    }

    @GetMapping(path = "/{id}/findById", produces = "application/json")
    public ResponseEntity<Category> get(@PathVariable int id) {
        csvSaver.run();

        Category foundCategory = categoryService.findById(id);
        if (foundCategory == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(foundCategory);
        }
    }

    @DeleteMapping(path = "/{id}/delete", produces = "application/json")
    public ResponseEntity<Object> delete(@PathVariable int id) {
        csvSaver.run();

        categoryService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    //    {
//        "categoryName":"new category",
//            "car":null
//    }
}
