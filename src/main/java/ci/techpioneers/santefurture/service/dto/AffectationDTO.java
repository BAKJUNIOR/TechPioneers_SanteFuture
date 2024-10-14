package ci.techpioneers.santefurture.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AffectationDTO {
    private Long id;
    private Long medecinId;
    private Long patientId;
    private LocalDateTime dateHeureDebut;
    private LocalDateTime dateHeureFin;
}