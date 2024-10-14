package ci.techpioneers.santefurture.repositories;

import ci.techpioneers.santefurture.models.Affectation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AffectationRepository extends JpaRepository<Affectation, Long> {
}