package ci.techpioneers.santefurture.repositories;

import ci.techpioneers.santefurture.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}