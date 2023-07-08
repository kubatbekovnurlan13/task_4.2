package kg.kubatbekov.carrestservice.service;

import kg.kubatbekov.carrestservice.DTO.RegistrationUserEntityDTO;
import kg.kubatbekov.carrestservice.model.UserEntity;
import kg.kubatbekov.carrestservice.repository.UserEntityRepository;
import kg.kubatbekov.carrestservice.security.PasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserEntityService implements UserDetailsService {
    private final UserEntityRepository userEntityRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("The user '%s' is not found!", username)));

        return new User(
                user.getUsername(),
                user.getPassword(),
                user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName())).toList());
    }

    public Optional<UserEntity> findByUsername(String username) {
        return userEntityRepository.findUserByUsername(username);
    }

    public UserEntity createNewUserEntity(RegistrationUserEntityDTO userEntityDTO) {

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(userEntityDTO.username());
        userEntity.setPassword(passwordEncoder.encode(userEntityDTO.password()));
        userEntity.setEmail(userEntityDTO.email());
        userEntity.setRoles(List.of(roleService.getUserRole()));

        return userEntityRepository.save(userEntity);
    }
}
