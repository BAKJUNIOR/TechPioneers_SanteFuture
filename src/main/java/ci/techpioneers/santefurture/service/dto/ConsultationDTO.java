package ci.techpioneers.santefurture.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConsultationDTO {
    private Long id;
    private Long ticketId;
    private Long medecinId;
    private LocalDateTime dateConsultation;
    private String resultats;
    private String diagnostic;
    private Long dossierMedicalId;
}