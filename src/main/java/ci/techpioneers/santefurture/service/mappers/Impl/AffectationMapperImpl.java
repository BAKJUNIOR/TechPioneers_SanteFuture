package ci.techpioneers.santefurture.service.mappers.Impl;


import ci.techpioneers.santefurture.models.Affectation;
import ci.techpioneers.santefurture.service.dto.AffectationDTO;
import ci.techpioneers.santefurture.service.mappers.AffectationMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AffectationMapperImpl implements AffectationMapper {

    private final ModelMapper modelMapper;

    @Override
    public AffectationDTO fromEntity(Affectation entity) {
        return modelMapper.map(entity, AffectationDTO.class);
    }

    @Override
    public Affectation toEntity(AffectationDTO dto) {
        return modelMapper.map(dto, Affectation.class);
    }
}