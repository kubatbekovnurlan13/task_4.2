package kg.kubatbekov.carrestservice.DTO;

import kg.kubatbekov.carrestservice.model.Car;
import kg.kubatbekov.carrestservice.model.Category;
import kg.kubatbekov.carrestservice.model.Manufacturer;
import kg.kubatbekov.carrestservice.model.Model;
import kg.kubatbekov.carrestservice.service.CategoryService;
import kg.kubatbekov.carrestservice.service.ManufacturerService;
import kg.kubatbekov.carrestservice.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class CarDTOMapper implements Function<CarDTO, Car> {
    private final ManufacturerService manufacturerService;
    private final CategoryService categoryService;
    private final ModelService modelService;

    @Autowired
    public CarDTOMapper(
            ManufacturerService manufacturerService,
            CategoryService categoryService,
            ModelService modelService) {
        this.manufacturerService = manufacturerService;
        this.categoryService = categoryService;
        this.modelService = modelService;
    }

    @Override
    public Car apply(CarDTO carDTO) {
        Manufacturer manufacturer = manufacturerService.findById(carDTO.manufacturerId());
        Model model = modelService.findById(carDTO.modelId());
        Category category = categoryService.findById(carDTO.categoryId());
        if (carDTO.carId().isEmpty()) {
            return new Car(manufacturer, carDTO.year(), model, category);
        } else {
            return new Car(carDTO.carId(), manufacturer, carDTO.year(), model, category);
        }
    }
}
