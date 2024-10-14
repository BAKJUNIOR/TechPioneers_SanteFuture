package ci.techpioneers.santefurture.service.dto;

import ci.techpioneers.santefurture.models.enums.StatutMedecin;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedecinDTO extends UserDTO {
    private String specialite;
    private int experience;
    private double consultationFee;
    private StatutMedecin statut;
    private Long serviceId;
}