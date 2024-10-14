package ci.techpioneers.santefurture.service.mappers.Impl;

import ci.techpioneers.santefurture.models.Service;
import ci.techpioneers.santefurture.service.dto.ServiceDTO;
import ci.techpioneers.santefurture.service.mappers.ServiceMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ServiceMapperImpl implements ServiceMapper {

    private final ModelMapper modelMapper;

    @Override
    public ServiceDTO fromEntity(Service entity) {
        return modelMapper.map(entity, ServiceDTO.class);
    }

    @Override
    public Service toEntity(ServiceDTO dto) {
        return modelMapper.map(dto, Service.class);
    }
}