package ci.techpioneers.santefurture.repositories;

import ci.techpioneers.santefurture.models.Medecin;
import ci.techpioneers.santefurture.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MedecinRepository extends JpaRepository<Medecin, Long> {
    Optional<Medecin> findByUser_Id(Long userId);

}
