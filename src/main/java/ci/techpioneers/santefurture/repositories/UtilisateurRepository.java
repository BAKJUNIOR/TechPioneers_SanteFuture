package ci.techpioneers.santefurture.repositories;


import ci.techpioneers.santefurture.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UtilisateurRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
