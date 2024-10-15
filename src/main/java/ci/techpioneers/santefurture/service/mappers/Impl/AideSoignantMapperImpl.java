package ci.techpioneers.santefurture.service.mappers.Impl;

import ci.techpioneers.santefurture.models.AideSoignant;
import ci.techpioneers.santefurture.service.dto.AideSoignantDTO;
import ci.techpioneers.santefurture.service.mappers.AideSoignantMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AideSoignantMapperImpl implements AideSoignantMapper {

    private final ModelMapper modelMapper;

    @Override
    public AideSoignantDTO fromEntity(AideSoignant entity) {
        return modelMapper.map(entity, AideSoignantDTO.class);
    }

    @Override
    public AideSoignant toEntity(AideSoignantDTO dto) {
        return modelMapper.map(dto, AideSoignant.class);
    }
}