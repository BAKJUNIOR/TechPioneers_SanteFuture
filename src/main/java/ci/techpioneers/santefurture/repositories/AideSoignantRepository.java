package ci.techpioneers.santefurture.repositories;

import ci.techpioneers.santefurture.models.AideSoignant;
import ci.techpioneers.santefurture.models.Medecin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AideSoignantRepository extends JpaRepository<AideSoignant, Long> {
    Optional<AideSoignant> findByUser_Id(Long userId);
}