package ci.techpioneers.santefurture.service.Impl;

import ci.techpioneers.santefurture.models.Role;
import ci.techpioneers.santefurture.models.enums.RoleName;
import ci.techpioneers.santefurture.repositories.RoleRepository;
import ci.techpioneers.santefurture.service.RoleService;
import ci.techpioneers.santefurture.service.dto.RoleDTO;
import ci.techpioneers.santefurture.service.mappers.RoleMapper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    @Override
    public List<RoleDTO> getAllRoles() {
        return roleRepository.findAll().stream()
                .map(roleMapper::fromEntity)
                .collect(Collectors.toList());
    }

    @PostConstruct
    public void initRoles() {
        List<RoleName> rolesToCreate = Arrays.asList(RoleName.PATIENT, RoleName.MEDECIN, RoleName.AIDE_SOIGNANT, RoleName.ADMINISTRATEUR);

        for (RoleName roleName : rolesToCreate) {
            if (!roleRepository.existsByLibelle(roleName)) {
                Role role = new Role();
                role.setLibelle(roleName);
                roleRepository.save(role);
            }
        }
    }
}
