package ci.techpioneers.santefurture.service;

import ci.techpioneers.santefurture.models.Medecin;
import ci.techpioneers.santefurture.models.Patient;
import ci.techpioneers.santefurture.service.dto.AideSoignantDTO;
import ci.techpioneers.santefurture.service.dto.MedecinDTO;
import ci.techpioneers.santefurture.service.dto.PatientDTO;

import java.util.List;
import java.util.Optional;

public interface MedecinService {

    Optional<Medecin> getMedecinByUserId(Long userId);
    MedecinDTO getAMedecinById(Long id);
    List<MedecinDTO> getAllMedecin();
    void deleteMedecin(Long id);

    MedecinDTO registerMedecin(MedecinDTO medecinDTO);

}
