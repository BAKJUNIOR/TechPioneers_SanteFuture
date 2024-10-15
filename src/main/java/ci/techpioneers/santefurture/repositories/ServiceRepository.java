package ci.techpioneers.santefurture.repositories;

import ci.techpioneers.santefurture.models.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ServiceRepository extends JpaRepository<Service, Long> {
}