package kg.kubatbekov.carrestservice.DTO;

public record CarDTO(
        int carId,
        int manufacturerId,
        int year,
        int modelId,
        int categoryId
) {
}
