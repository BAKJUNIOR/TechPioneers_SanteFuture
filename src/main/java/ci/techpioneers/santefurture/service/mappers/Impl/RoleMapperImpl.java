package ci.techpioneers.santefurture.service.mappers.Impl;


import ci.techpioneers.santefurture.models.Role;
import ci.techpioneers.santefurture.service.dto.RoleDTO;
import ci.techpioneers.santefurture.service.mappers.RoleMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoleMapperImpl implements RoleMapper {

    private final ModelMapper modelMapper;

    @Override
    public RoleDTO fromEntity(Role entity) {
        return modelMapper.map(entity, RoleDTO.class);
    }

    @Override
    public Role toEntity(RoleDTO dto) {
        return modelMapper.map(dto, Role.class);
    }
}