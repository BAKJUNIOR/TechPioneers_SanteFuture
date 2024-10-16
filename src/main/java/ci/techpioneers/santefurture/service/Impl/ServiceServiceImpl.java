package ci.techpioneers.santefurture.service.Impl;

import ci.techpioneers.santefurture.repositories.ServiceRepository;
import ci.techpioneers.santefurture.service.ServiceService;
import ci.techpioneers.santefurture.service.dto.ServiceDTO;
import ci.techpioneers.santefurture.service.dto.MedecinDTO;
import ci.techpioneers.santefurture.models.Service;
import ci.techpioneers.santefurture.service.mappers.MedecinMapper;
import ci.techpioneers.santefurture.service.mappers.ServiceMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class ServiceServiceImpl implements ServiceService {

    private final ServiceRepository serviceRepository;
    private final ServiceMapper serviceMapper;
    private final MedecinMapper medecinMapper;

    @Override
    public ServiceDTO addService(ServiceDTO serviceDTO) {
        log.debug("Création d'un nouveau service: {}", serviceDTO);
        Service service = serviceMapper.toEntity(serviceDTO);
        Service savedService = serviceRepository.save(service);
        return serviceMapper.fromEntity(savedService);
    }

    @Override
    public ServiceDTO updateService(Long id, ServiceDTO serviceDTO) {
        log.debug("Mise à jour du service avec ID: {}", id);
        Service service = serviceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Service non trouvé: " + id));

        service.setNom(serviceDTO.getNom());
        service.setPrixUnitaire(serviceDTO.getPrixUnitaire());

        Service updatedService = serviceRepository.save(service);
        return serviceMapper.fromEntity(updatedService);
    }

    @Override
    public void deleteService(Long id) {
        log.debug("Suppression du service avec ID: {}", id);
        serviceRepository.deleteById(id);
    }

    @Override
    public List<ServiceDTO> getAllServices() {
        log.debug("Récupération de tous les services");
        return serviceRepository.findAll().stream()
                .map(service -> {
                    ServiceDTO serviceDTO = serviceMapper.fromEntity(service);
                    serviceDTO.setMedecins(service.getMedecins().stream()
                            .map(medecinMapper::fromEntity)
                            .collect(Collectors.toSet()));
                    return serviceDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public ServiceDTO getServiceById(Long id) {
        log.debug("Récupération du service avec ID: {}", id);
        Service service = serviceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Service non trouvé: " + id));
        ServiceDTO serviceDTO = serviceMapper.fromEntity(service);

        serviceDTO.setMedecins(service.getMedecins().stream()
                .map(medecinMapper::fromEntity)
                .collect(Collectors.toSet()));

        return serviceDTO;
    }

    public Set<MedecinDTO> getMedecinsByServiceId(Long serviceId) {
        log.debug("Récupération des médecins pour le service avec ID: {}", serviceId);
        Service service = serviceRepository.findById(serviceId)
                .orElseThrow(() -> new RuntimeException("Service non trouvé: " + serviceId));

        return service.getMedecins().stream()
                .map(medecinMapper::fromEntity)
                .collect(Collectors.toSet());
    }
}
