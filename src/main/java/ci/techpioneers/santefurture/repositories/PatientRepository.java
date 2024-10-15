package ci.techpioneers.santefurture.repositories;

import ci.techpioneers.santefurture.models.Patient;
import ci.techpioneers.santefurture.service.dto.PatientDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Optional<Patient> findByUser_Id(Long userId);

}