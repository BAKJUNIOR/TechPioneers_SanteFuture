package ci.techpioneers.santefurture.service.mappers.Impl;

import ci.techpioneers.santefurture.models.DonneesVitales;

import ci.techpioneers.santefurture.service.dto.DonneesVitalesDTO;
import ci.techpioneers.santefurture.service.mappers.DonneesVitalesMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DonneesVitalesMapperImpl implements DonneesVitalesMapper {

    private final ModelMapper modelMapper;

    @Override
    public DonneesVitalesDTO fromEntity(DonneesVitales entity) {
        return modelMapper.map(entity, DonneesVitalesDTO.class);
    }

    @Override
    public DonneesVitales toEntity(DonneesVitalesDTO dto) {
        return modelMapper.map(dto, DonneesVitales.class);
    }
}