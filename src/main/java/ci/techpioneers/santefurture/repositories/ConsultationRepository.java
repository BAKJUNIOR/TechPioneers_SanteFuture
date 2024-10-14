package ci.techpioneers.santefurture.repositories;

import ci.techpioneers.santefurture.models.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultationRepository extends JpaRepository<Consultation, Long> {
}