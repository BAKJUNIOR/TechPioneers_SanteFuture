package ci.techpioneers.santefurture.service;

import ci.techpioneers.santefurture.models.Patient;
import ci.techpioneers.santefurture.service.dto.PatientDTO;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface PatientService {
    PatientDTO getPatientById(Long id);
    Optional<Patient> getPatientByUserId(Long userId);
    List<PatientDTO> getAllPatients();
    void deletePatient(Long id);

    PatientDTO registerPatient(PatientDTO patientDTO);
    void activation(Map<String, String> activation);
}