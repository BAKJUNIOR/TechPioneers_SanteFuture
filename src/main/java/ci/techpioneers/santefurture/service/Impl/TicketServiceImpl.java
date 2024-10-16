package ci.techpioneers.santefurture.service.Impl;

import ci.techpioneers.santefurture.models.Patient;
import ci.techpioneers.santefurture.models.Ticket;
import ci.techpioneers.santefurture.models.User;
import ci.techpioneers.santefurture.repositories.PatientRepository;
import ci.techpioneers.santefurture.repositories.ServiceRepository;
import ci.techpioneers.santefurture.repositories.TicketRepository;
import ci.techpioneers.santefurture.service.TicketService;
import ci.techpioneers.santefurture.service.UserService;
import ci.techpioneers.santefurture.service.dto.PatientDTO;
import ci.techpioneers.santefurture.service.dto.ServiceDTO;
import ci.techpioneers.santefurture.service.dto.TicketDTO;
import ci.techpioneers.santefurture.service.dto.UserDTO;
import ci.techpioneers.santefurture.service.mappers.PatientMapper;
import ci.techpioneers.santefurture.service.mappers.TicketMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final UserService userService;
    private final TicketMapper ticketMapper;
    private final ServiceRepository serviceRepository;
    private final PatientRepository patientRepository;
    private final EmailService emailService;
    private final PatientMapper patientMapper;


    @Override
    public TicketDTO createTicket(TicketDTO ticketDTO) {

        Patient patient = patientRepository.findById(ticketDTO.getPatientId())
                .orElseThrow(() -> new RuntimeException("Patient non trouvé: " + ticketDTO.getPatientId()));



        String emailPatient = patient.getUser().getEmail();
        String nom = patient.getFirstName();
        String prenom = patient.getLastName();
        String nomComplet = nom + " " + prenom;
        LocalDate DateNaissance = patient.getDateOfBirth();

        LocalDate dateNaissance = patient.getDateOfBirth();
        int age = Period.between(dateNaissance, LocalDate.now()).getYears();

        double prixTotal = 0.0;
        for (ServiceDTO serviceDTO : ticketDTO.getServices()) {
            ci.techpioneers.santefurture.models.Service service = serviceRepository.findById(serviceDTO.getId())
                    .orElseThrow(() -> new RuntimeException("Service non trouvé: " + serviceDTO.getId()));
            prixTotal += service.getPrixUnitaire() * ticketDTO.getQuantity();
        }

        String codeFacture = String.format("TICKET-%07d", ticketRepository.count() + 1);

        Instant dateEmission = Instant.now();
        Instant dateValidite = dateEmission.plusSeconds(3 * 24 * 60 * 60);

        Ticket ticket = new Ticket();
        ticket.setCodeFacture(codeFacture);
        ticket.setQuantity(ticketDTO.getQuantity());
        ticket.setPrixTotal(prixTotal);
        ticket.setDateEmission(LocalDateTime.now());
        ticket.setDateValidite(LocalDateTime.ofInstant(dateValidite, ZoneId.systemDefault()));
        ticket.setDateCreation(dateEmission);
        ticket.setPatient(patient);

        Set<ci.techpioneers.santefurture.models.Service> services = new HashSet<>();
        for (ServiceDTO serviceDTO : ticketDTO.getServices()) {
            ci.techpioneers.santefurture.models.Service service = serviceRepository.findById(serviceDTO.getId())
                    .orElseThrow(() -> new RuntimeException("Service non trouvé: " + serviceDTO.getId()));
            services.add(service);
        }
        ticket.setServices(services);
        Ticket savedTicket = ticketRepository.save(ticket);

        TicketDTO createdTicketDTO = ticketMapper.fromEntity(savedTicket);
        createdTicketDTO.setEmailPatient(emailPatient);
        createdTicketDTO.setNomComplet(nomComplet);
        createdTicketDTO.setAge(age + " ans");
        createdTicketDTO.setDateNaissance(DateNaissance);

        emailService.sendTicketEmail(createdTicketDTO);

        return createdTicketDTO;
    }

    @Override
    public TicketDTO updateTicket(Long id, TicketDTO ticketDTO) {
        log.debug("Updating ticket with ID: {}", id);
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found: " + id));

        ticket.setCodeFacture(ticketDTO.getCodeFacture());
        ticket.setDateCreation(ticketDTO.getDateCreation());
        ticket.setPrixTotal(ticketDTO.getPrixTotal());
        ticket.setDateCreation(Instant.now());
        ticket.setQuantity(ticketDTO.getQuantity());


        Ticket updatedTicket = ticketRepository.save(ticket);
        return ticketMapper.fromEntity(updatedTicket);
    }

    @Override
    public void deleteTicket(Long id) {
        log.debug("Deleting ticket with ID: {}", id);
        ticketRepository.deleteById(id);
    }

    @Override
    public List<TicketDTO> getAllTickets() {
        log.debug("Retrieving all tickets");
        List<Ticket> tickets = ticketRepository.findAll();
        return tickets.stream()
                .map(ticketMapper::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public TicketDTO getTicketById(Long id) {
        log.debug("Retrieving ticket with ID: {}", id);
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found: " + id));
        return ticketMapper.fromEntity(ticket);
    }


}