package org.hack.service;

import lombok.RequiredArgsConstructor;
import org.hack.entity.Role;
import org.hack.repository.RoleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;
    public Role getUserRole() {
        return findRoleByName("ROLE_USER");
    }
    public Role findRoleByName(String roleName) {
        return roleRepository.findByName(roleName)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Не найдена роль с именем " + roleName));
    }
}
