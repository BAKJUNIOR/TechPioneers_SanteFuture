package ci.techpioneers.santefurture.service;

import ci.techpioneers.santefurture.service.dto.MedecinDTO;
import ci.techpioneers.santefurture.service.dto.ServiceDTO;

import java.util.List;
import java.util.Set;


public interface SrviceService {
    ServiceDTO addService(ServiceDTO serviceDTO);
    ServiceDTO updateService(Long id, ServiceDTO serviceDTO);
    void deleteService(Long id);
    List<ServiceDTO> getAllServices();
    Set<MedecinDTO> getMedecinsByServiceId(Long serviceId);

        ServiceDTO getServiceById(Long id);
}
