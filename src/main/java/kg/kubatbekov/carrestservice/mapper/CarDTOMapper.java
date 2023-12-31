package kg.kubatbekov.carrestservice.mapper;

import kg.kubatbekov.carrestservice.DTO.CarDTO;
import kg.kubatbekov.carrestservice.model.Car;
import kg.kubatbekov.carrestservice.model.Category;
import kg.kubatbekov.carrestservice.model.Manufacturer;
import kg.kubatbekov.carrestservice.model.Model;
import kg.kubatbekov.carrestservice.service.CategoryService;
import kg.kubatbekov.carrestservice.service.ManufacturerService;
import kg.kubatbekov.carrestservice.service.ModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class CarDTOMapper implements Function<CarDTO, Car> {
    private final ManufacturerService manufacturerService;
    private final CategoryService categoryService;
    private final ModelService modelService;

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
