package ci.techpioneers.santefurture.service.mappers.Impl;


import ci.techpioneers.santefurture.models.User;
import ci.techpioneers.santefurture.service.dto.UserDTO;
import ci.techpioneers.santefurture.service.mappers.UserMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapperImpl implements UserMapper {

    private final ModelMapper modelMapper;

    @Override
    public UserDTO fromEntity(User entity) {
        return modelMapper.map(entity, UserDTO.class);
    }

    @Override
    public User toEntity(UserDTO dto) {
        return modelMapper.map(dto, User.class);
    }
}