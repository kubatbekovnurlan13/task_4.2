package kg.kubatbekov.carrestservice.DTO;

public record RegistrationUserEntityDTO(
        String username,
        String password,
        String confirmPassword,
        String email) {
}
