package ci.techpioneers.santefurture.service.mappers.Impl;

import ci.techpioneers.santefurture.models.Ticket;
import ci.techpioneers.santefurture.service.dto.TicketDTO;
import ci.techpioneers.santefurture.service.mappers.TicketMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class TicketMapperImpl implements TicketMapper {

    private final ModelMapper modelMapper;

    @Override
    public TicketDTO fromEntity(Ticket entity) {
        return modelMapper.map(entity, TicketDTO.class);
    }

    @Override
    public Ticket toEntity(TicketDTO dto) {
        return modelMapper.map(dto, Ticket.class);
    }
}