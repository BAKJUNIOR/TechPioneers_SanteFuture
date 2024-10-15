package ci.techpioneers.santefurture.service.mappers.Impl;

import ci.techpioneers.santefurture.models.Patient;
import ci.techpioneers.santefurture.service.dto.PatientDTO;
import ci.techpioneers.santefurture.service.mappers.PatientMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PatientMapperImpl implements PatientMapper {

    private final ModelMapper modelMapper;

    @Override
    public PatientDTO fromEntity(Patient entity) {
        return modelMapper.map(entity, PatientDTO.class);
    }

    @Override
    public Patient toEntity(PatientDTO dto) {
        return modelMapper.map(dto, Patient.class);
    }
}