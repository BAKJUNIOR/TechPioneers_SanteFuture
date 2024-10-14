package ci.techpioneers.santefurture.repositories;


import ci.techpioneers.santefurture.models.FileAttente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilleAttenteRepository extends JpaRepository<FileAttente, Long> {
}