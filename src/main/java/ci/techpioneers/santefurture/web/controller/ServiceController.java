package ci.techpioneers.santefurture.web.controller;

import ci.techpioneers.santefurture.service.SrviceService;
import ci.techpioneers.santefurture.service.dto.ServiceDTO;
import ci.techpioneers.santefurture.service.dto.MedecinDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@Slf4j
@RestController
@RequestMapping("/api/services")
@RequiredArgsConstructor
public class ServiceController {

    private final SrviceService serviceService;

    @PostMapping
    public ResponseEntity<ServiceDTO> createService(@RequestBody ServiceDTO serviceDTO) {
        log.debug("Création d'un nouveau service");
        ServiceDTO createdService = serviceService.addService(serviceDTO);
        return ResponseEntity.status(201).body(createdService);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceDTO> updateService(@PathVariable Long id, @RequestBody ServiceDTO serviceDTO) {
        log.debug("Mise à jour du service avec ID: {}", id);
        ServiceDTO updatedService = serviceService.updateService(id, serviceDTO);
        return ResponseEntity.ok(updatedService);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteService(@PathVariable Long id) {
        log.debug("Suppression du service avec ID: {}", id);
        serviceService.deleteService(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<ServiceDTO>> getAllServices() {
        log.debug("Récupération de tous les services");
        List<ServiceDTO> services = serviceService.getAllServices();
        return ResponseEntity.ok(services);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceDTO> getServiceById(@PathVariable Long id) {
        log.debug("Récupération du service avec ID: {}", id);
        ServiceDTO serviceDTO = serviceService.getServiceById(id);
        return ResponseEntity.ok(serviceDTO);
    }

    @GetMapping("/{serviceId}/medecins")
    public ResponseEntity<Set<MedecinDTO>> getMedecinsByServiceId(@PathVariable Long serviceId) {
        log.debug("Récupération des médecins pour le service avec ID: {}", serviceId);
        Set<MedecinDTO> medecins = serviceService.getMedecinsByServiceId(serviceId);
        return ResponseEntity.ok(medecins);
    }
}
