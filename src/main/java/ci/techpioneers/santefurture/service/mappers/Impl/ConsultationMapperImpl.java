package ci.techpioneers.santefurture.service.mappers.Impl;

import ci.techpioneers.santefurture.models.Consultation;
import ci.techpioneers.santefurture.service.dto.ConsultationDTO;
import ci.techpioneers.santefurture.service.mappers.ConsultationMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ConsultationMapperImpl implements ConsultationMapper {

    private final ModelMapper modelMapper;

    @Override
    public ConsultationDTO fromEntity(Consultation entity) {
        return modelMapper.map(entity, ConsultationDTO.class);
    }

    @Override
    public Consultation toEntity(ConsultationDTO dto) {
        return modelMapper.map(dto, Consultation.class);
    }
}