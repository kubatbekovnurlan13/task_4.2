package kg.kubatbekov.carrestservice.service;

import kg.kubatbekov.carrestservice.model.Role;
import kg.kubatbekov.carrestservice.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public Role getUserRole() {
        return roleRepository.findByName("ROLE_USER").get();
    }
}
