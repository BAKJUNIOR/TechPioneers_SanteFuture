package ci.techpioneers.santefurture.web.controller;

import ci.techpioneers.santefurture.service.MedecinService;
import ci.techpioneers.santefurture.service.dto.MedecinDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medecins")
@RequiredArgsConstructor
@Slf4j
public class MedecinController {

    private final MedecinService medecinService;

    @PostMapping("/register")
    public ResponseEntity<?> registerMedecin(@RequestBody MedecinDTO medecinDTO) {
        log.debug("REST Request to register Medecin: {}", medecinDTO);
        if (medecinDTO.getUser() == null || medecinDTO.getUser().getRoles() == null) {
            log.error("User information and roles are mandatory.");
            return ResponseEntity.badRequest().body("Les informations de l'utilisateur et les rôles sont obligatoires.");
        }
        try {
            MedecinDTO registeredMedecin = medecinService.registerMedecin(medecinDTO);
            log.debug("Medecin registered successfully: {}", registeredMedecin);
            return new ResponseEntity<>(registeredMedecin, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            log.error("Error registering Medecin: {}", e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedecinDTO> getMedecinById(@PathVariable Long id) {
        log.debug("REST Request to get Medecin by ID: {}", id);
        try {
            MedecinDTO medecinDTO = medecinService.getAMedecinById(id);
            log.debug("Medecin found: {}", medecinDTO);
            return ResponseEntity.ok(medecinDTO);
        } catch (RuntimeException e) {
            log.error("Error retrieving Medecin by ID: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<MedecinDTO>> getAllMedecins() {
        log.debug("REST Request to get all Medecins");
        List<MedecinDTO> medecins = medecinService.getAllMedecin();
        log.debug("Total Medecins found: {}", medecins.size());
        return ResponseEntity.ok(medecins);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedecin(@PathVariable Long id) {
        log.debug("REST Request to delete Medecin with ID: {}", id);
        try {
            medecinService.deleteMedecin(id);
            log.debug("Medecin deleted successfully: {}", id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            log.error("Error deleting Medecin with ID: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
