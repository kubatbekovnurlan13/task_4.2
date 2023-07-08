package kg.kubatbekov.carrestservice.controller;

import kg.kubatbekov.carrestservice.DTO.JwtRequestDTO;
import kg.kubatbekov.carrestservice.DTO.RegistrationUserEntityDTO;

import kg.kubatbekov.carrestservice.service.AuthService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping(path = "/sign-in", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequestDTO authRequest) {
        return authService.createAuthToken(authRequest);
    }

    @PostMapping(path = "/registration", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> registrationUserEntity(@RequestBody RegistrationUserEntityDTO userEntityDTO) {
        return authService.createUserEntity(userEntityDTO);
    }
}
