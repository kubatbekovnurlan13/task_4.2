package kg.kubatbekov.carrestservice.parser;

import kg.kubatbekov.carrestservice.model.Car;
import kg.kubatbekov.carrestservice.model.Category;
import kg.kubatbekov.carrestservice.model.Manufacturer;
import kg.kubatbekov.carrestservice.model.Model;
import kg.kubatbekov.carrestservice.service.CarService;
import kg.kubatbekov.carrestservice.service.CategoryService;
import kg.kubatbekov.carrestservice.service.ManufacturerService;
import kg.kubatbekov.carrestservice.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class CsvSaver {
    private final CsvParser csvParser;
    private List<List<String>> record;
    private final ModelService modelService;
    private final CategoryService categoryService;
    private final ManufacturerService manufacturerService;
    private final CarService carService;

    @Autowired
    public CsvSaver(CsvParser csvParser, ModelService modelService, CategoryService categoryService, ManufacturerService manufacturerService, CarService carService) {
        this.csvParser = csvParser;
        this.modelService = modelService;
        this.categoryService = categoryService;
        this.manufacturerService = manufacturerService;
        this.carService = carService;
    }

    public void run() {
        List<Car> cars = carService.findAll();
        if (cars.isEmpty()) {
            System.out.println("CSV saver running...");
            setRecord();
            System.out.println("setRecord...");

            saveModels(getModels());
            System.out.println("saveModels...");

            saveCategories(getCategories());
            System.out.println("saveCategories...");

            saveManufacturers(getManufacturers());
            System.out.println("saveManufacturers...");

            saveCars();
            System.out.println("saveCars...");
            System.out.println("CSV saver done...");
        }
    }

    public void setRecord() {
        this.record = csvParser.parse();
    }

    public void saveModels(List<Model> models) {
        modelService.saveAll(models);
    }

    public void saveCategories(List<Category> categories) {
        categoryService.saveAll(categories);
    }

    public void saveManufacturers(List<Manufacturer> manufacturers) {
        manufacturerService.saveAll(manufacturers);
    }

    public void saveCars() {
        List<Model> models = modelService.findAll();
        List<Category> categories = categoryService.findAll();
        List<Manufacturer> manufacturers = manufacturerService.findAll();

        Map<String, Model> modelMap = convertModel(models);
        Map<String, Category> categoryMap = convertCategory(categories);
        Map<String, Manufacturer> manufacturerMap = convertManufacturer(manufacturers);

        for (List<String> line : record) {
            Car car = new Car();

            car.setCarId(line.get(0));

            Manufacturer manufacturer1 = manufacturerMap.get(line.get(1));
            car.setManufacturer(manufacturer1);

            car.setYear(Integer.parseInt(line.get(2)));

            Model model1 = modelMap.get(line.get(3));
            car.setModel(model1);

            Category category1 = categoryMap.get(line.get(4));
            car.setCategory(category1);

            carService.save(car);
        }
    }

    public Map<String, Model> convertModel(List<Model> list) {
        return list.stream()
                .collect(Collectors.toMap(Model::getModelName, Function.identity()));
    }

    public Map<String, Category> convertCategory(List<Category> list) {
        return list.stream()
                .collect(Collectors.toMap(Category::getCategoryName, Function.identity()));
    }

    public Map<String, Manufacturer> convertManufacturer(List<Manufacturer> list) {
        return list.stream()
                .collect(Collectors.toMap(Manufacturer::getManufacturerName, Function.identity()));
    }

    public List<Model> getModels() {
        HashSet<String> rawModels = new HashSet<>();

        for (List<String> line : record) {
            String model = line.get(3);
            rawModels.add(model);
        }

        return rawModels.stream()
                .map(Model::new).toList();
    }

    public List<Category> getCategories() {
        HashSet<String> rawCategories = new HashSet<>();

        for (List<String> line : record) {
            String category = line.get(4);
            rawCategories.add(category);
        }
        return rawCategories.stream()
                .map(Category::new).toList();
    }

    public List<Manufacturer> getManufacturers() {
        HashSet<String> rawManufacturers = new HashSet<>();

        for (List<String> line : record) {
            String manufacturer = line.get(1);
            rawManufacturers.add(manufacturer);
        }
        return rawManufacturers.stream()
                .map(Manufacturer::new).toList();
    }

}
