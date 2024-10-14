package ci.techpioneers.santefurture.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationDTO {
    private Long id;
    private Long medecinId;
    private String message;
    private LocalDateTime dateCreation;
    private boolean lu;
}