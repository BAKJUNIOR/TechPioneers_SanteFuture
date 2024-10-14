package ci.techpioneers.santefurture.repositories;


import ci.techpioneers.santefurture.models.ValidationCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ValidationCodeRepository extends JpaRepository<ValidationCode, Integer> {
    Optional<ValidationCode> findByCode(String code);
}
