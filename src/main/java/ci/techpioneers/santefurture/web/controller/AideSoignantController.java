package ci.techpioneers.santefurture.web.controller;

import ci.techpioneers.santefurture.service.AideSoignantService;
import ci.techpioneers.santefurture.service.dto.AideSoignantDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/aideSoignant")
@RequiredArgsConstructor
@Slf4j
public class AideSoignantController {

    private final AideSoignantService aideSoignantService;

    @PostMapping("/register")
    public ResponseEntity<?> registerAideSoignant(@RequestBody AideSoignantDTO aideSoignantDTO) {
        log.debug("Request to register AideSoignant: {}", aideSoignantDTO);
        if (aideSoignantDTO.getUser() == null || aideSoignantDTO.getUser().getRoles() == null) {
            log.warn("Les informations de l'utilisateur et les rôles sont manquantes.");
            return ResponseEntity.badRequest().body("Les informations de l'utilisateur et les rôles sont obligatoires.");
        }

        AideSoignantDTO aideSoignant = aideSoignantService.registerAideSoignant(aideSoignantDTO);
        log.debug("AideSoignant registered successfully: {}", aideSoignantDTO);
        return ResponseEntity.ok(aideSoignant);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AideSoignantDTO> getAideSoignantById(@PathVariable Long id) {
        log.debug("Request to get AideSoignant by ID: {}", id);
        AideSoignantDTO aideSoignantDTO = aideSoignantService.getAideSoignantById(id);
        log.debug("AideSoignant found: {}", aideSoignantDTO);
        return ResponseEntity.ok(aideSoignantDTO);
    }

    @GetMapping
    public ResponseEntity<List<AideSoignantDTO>> getAllAideSoignants() {
        log.debug("Request to get all AideSoignants");
        List<AideSoignantDTO> aideSoignants = aideSoignantService.getAllAideSoignant();
        log.debug("Total AideSoignants found: {}", aideSoignants.size());
        return ResponseEntity.ok(aideSoignants);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAideSoignant(@PathVariable Long id) {
        log.debug("Request to delete AideSoignant with ID: {}", id);
        aideSoignantService.deleteAideSoignant(id);
        log.debug("AideSoignant deleted successfully: {}", id);
        return ResponseEntity.noContent().build();
    }
}
