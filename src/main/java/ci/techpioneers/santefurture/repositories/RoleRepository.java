package ci.techpioneers.santefurture.repositories;

import ci.techpioneers.santefurture.models.Role;
import ci.techpioneers.santefurture.models.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByLibelle(RoleName roleName);
}