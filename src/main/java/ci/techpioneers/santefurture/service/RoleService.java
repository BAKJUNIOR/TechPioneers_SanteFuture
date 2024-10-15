package ci.techpioneers.santefurture.service;

import ci.techpioneers.santefurture.models.enums.RoleName;
import ci.techpioneers.santefurture.service.dto.RoleDTO;

import java.util.List;
import java.util.Set;

public interface RoleService {
    List<RoleDTO> getAllRoles();
}