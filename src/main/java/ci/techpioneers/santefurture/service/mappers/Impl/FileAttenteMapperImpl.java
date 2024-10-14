package ci.techpioneers.santefurture.service.mappers.Impl;


import ci.techpioneers.santefurture.models.FileAttente;
import ci.techpioneers.santefurture.service.dto.FileAttenteDTO;
import ci.techpioneers.santefurture.service.mappers.FileAttenteMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FileAttenteMapperImpl implements FileAttenteMapper {

    private final ModelMapper modelMapper;

    @Override
    public FileAttenteDTO fromEntity(FileAttente entity) {
        return modelMapper.map(entity, FileAttenteDTO.class);
    }

    @Override
    public FileAttente toEntity(FileAttenteDTO dto) {
        return modelMapper.map(dto, FileAttente.class);
    }
}