package ci.techpioneers.santefurture.repositories;

import ci.techpioneers.santefurture.models.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}