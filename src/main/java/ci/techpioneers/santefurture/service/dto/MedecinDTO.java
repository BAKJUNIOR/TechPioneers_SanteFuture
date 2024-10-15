package ci.techpioneers.santefurture.service.dto;

import ci.techpioneers.santefurture.models.enums.StatutMedecin;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedecinDTO extends PersonDTO {
    private String specialite;
    private String experience;
    private StatutMedecin statut;
    private Long serviceId;
}