package ci.techpioneers.santefurture.service.mappers.Impl;

import ci.techpioneers.santefurture.models.User;
import ci.techpioneers.santefurture.models.ValidationCode;
import ci.techpioneers.santefurture.service.dto.UserDTO;
import ci.techpioneers.santefurture.service.dto.ValidationCodeDTO;
import ci.techpioneers.santefurture.service.mappers.validationCodeMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class validationCodeMapperImpl implements validationCodeMapper {
    private final ModelMapper modelMapper;


    @Override
    public ValidationCodeDTO fromEntity(ValidationCode entity) {
        return modelMapper.map(entity, ValidationCodeDTO.class);
    }

    @Override
    public ValidationCode toEntity(ValidationCodeDTO dto) {
        return modelMapper.map(dto, ValidationCode.class);
    }


}
