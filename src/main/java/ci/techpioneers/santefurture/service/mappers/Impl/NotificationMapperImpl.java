package ci.techpioneers.santefurture.service.mappers.Impl;

import ci.techpioneers.santefurture.models.Notification;
import ci.techpioneers.santefurture.service.dto.NotificationDTO;
import ci.techpioneers.santefurture.service.mappers.NotificationMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationMapperImpl implements NotificationMapper {

    private final ModelMapper modelMapper;

    @Override
    public NotificationDTO fromEntity(Notification entity) {
        return modelMapper.map(entity, NotificationDTO.class);
    }

    @Override
    public Notification toEntity(NotificationDTO dto) {
        return modelMapper.map(dto, Notification.class);
    }
}