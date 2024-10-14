package ci.techpioneers.santefurture.repositories;

import ci.techpioneers.santefurture.models.DossierMedical;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DossierMedicalRepository extends JpaRepository<DossierMedical, Long> {
}