package kg.kubatbekov.carrestservice.DTO;

public record CarDTO(
        String carId,
        int manufacturerId,
        int year,
        int modelId,
        int categoryId
) {
}
