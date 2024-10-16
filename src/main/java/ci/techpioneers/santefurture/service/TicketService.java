package ci.techpioneers.santefurture.service;

import ci.techpioneers.santefurture.models.Patient;
import ci.techpioneers.santefurture.service.dto.PatientDTO;
import ci.techpioneers.santefurture.service.dto.TicketDTO;

import java.util.List;

public interface TicketService {
    TicketDTO createTicket(TicketDTO ticketDTO);
    TicketDTO updateTicket(Long id, TicketDTO ticketDTO);
    void deleteTicket(Long id);
    List<TicketDTO> getAllTickets();
    TicketDTO getTicketById(Long id);

}