package ci.techpioneers.santefurture.service.mappers.Impl;

import ci.techpioneers.santefurture.models.DossierMedical;
import ci.techpioneers.santefurture.service.dto.DossierMedicalDTO;
import ci.techpioneers.santefurture.service.mappers.DossierMedicalMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DossierMedicalMapperImpl implements DossierMedicalMapper {

    private final ModelMapper modelMapper;

    @Override
    public DossierMedicalDTO fromEntity(DossierMedical entity) {
        return modelMapper.map(entity, DossierMedicalDTO.class);
    }

    @Override
    public DossierMedical toEntity(DossierMedicalDTO dto) {
        return modelMapper.map(dto, DossierMedical.class);
    }
}