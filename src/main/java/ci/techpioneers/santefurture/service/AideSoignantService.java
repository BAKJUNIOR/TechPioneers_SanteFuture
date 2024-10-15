package ci.techpioneers.santefurture.service;

import ci.techpioneers.santefurture.models.AideSoignant;
import ci.techpioneers.santefurture.models.Patient;
import ci.techpioneers.santefurture.service.dto.AideSoignantDTO;

import java.util.List;
import java.util.Optional;

public interface AideSoignantService {
    Optional<AideSoignant> getAideSoignantByUserId(Long userId);

    AideSoignantDTO getAideSoignantById(Long id);
    List<AideSoignantDTO> getAllAideSoignant();
    void deleteAideSoignant(Long id);

    AideSoignantDTO registerAideSoignant(AideSoignantDTO aideSoignantDTO);
}