package ci.techpioneers.santefurture.repositories;

import ci.techpioneers.santefurture.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}