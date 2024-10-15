package ci.techpioneers.santefurture.repositories;

import ci.techpioneers.santefurture.models.User;
import ci.techpioneers.santefurture.service.dto.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

}