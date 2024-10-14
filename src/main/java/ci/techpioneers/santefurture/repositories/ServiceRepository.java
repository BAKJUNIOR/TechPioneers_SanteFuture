package ci.techpioneers.santefurture.repositories;

import ci.techpioneers.santefurture.models.Service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<Service, Long> {
}