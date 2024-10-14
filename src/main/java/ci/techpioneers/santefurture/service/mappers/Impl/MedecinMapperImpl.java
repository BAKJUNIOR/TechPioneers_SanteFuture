package ci.techpioneers.santefurture.service.mappers.Impl;

import ci.techpioneers.santefurture.models.Medecin;
import ci.techpioneers.santefurture.service.dto.MedecinDTO;
import ci.techpioneers.santefurture.service.mappers.MedecinMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MedecinMapperImpl implements MedecinMapper {

    private final ModelMapper modelMapper;

    @Override
    public MedecinDTO fromEntity(Medecin entity) {
        return modelMapper.map(entity, MedecinDTO.class);
    }

    @Override
    public Medecin toEntity(MedecinDTO dto) {
        return modelMapper.map(dto, Medecin.class);
    }
}