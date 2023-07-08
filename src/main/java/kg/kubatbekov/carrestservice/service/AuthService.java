package kg.kubatbekov.carrestservice.service;


import kg.kubatbekov.carrestservice.DTO.JwtRequestDTO;
import kg.kubatbekov.carrestservice.DTO.JwtResponseDTO;
import kg.kubatbekov.carrestservice.DTO.RegistrationUserEntityDTO;
import kg.kubatbekov.carrestservice.DTO.UserEntityDTO;
import kg.kubatbekov.carrestservice.exception.AppError;
import kg.kubatbekov.carrestservice.model.UserEntity;
import kg.kubatbekov.carrestservice.utils.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserEntityService userEntityService;
    private final JwtTokenUtils jwtTokenUtils;
    private final AuthenticationManager authenticationManager;

    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequestDTO authRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.username(), authRequest.password()));
        } catch (BadCredentialsException bce) {
            new ResponseEntity<>(
                    new AppError(HttpStatus.UNAUTHORIZED.value(), "Wrong login or password!"), HttpStatus.UNAUTHORIZED);
        }

        UserDetails userDetails = userEntityService.loadUserByUsername(authRequest.username());
        String token = jwtTokenUtils.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponseDTO(token));
    }

    public ResponseEntity<?> createUserEntity(@RequestBody RegistrationUserEntityDTO userEntityDTO) {
        if (!userEntityDTO.password().equals(userEntityDTO.confirmPassword())) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "Wrong password!"), HttpStatus.BAD_REQUEST);
        }
        if (userEntityService.findByUsername(userEntityDTO.username()).isPresent()) {
            return new ResponseEntity<>(new AppError(HttpStatus.UNAUTHORIZED.value(), "The user already exists!"), HttpStatus.BAD_REQUEST);
        }

        UserEntity userEntity = userEntityService.createNewUserEntity(userEntityDTO);
        return ResponseEntity.ok(new UserEntityDTO(userEntity.getId(), userEntity.getUsername(), userEntity.getEmail()));
    }
}

