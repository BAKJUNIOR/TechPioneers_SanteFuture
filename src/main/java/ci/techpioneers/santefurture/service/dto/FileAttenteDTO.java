package ci.techpioneers.santefurture.service.dto;

import ci.techpioneers.santefurture.models.enums.PrioritePatient;
import ci.techpioneers.santefurture.models.enums.StatutAffectation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileAttenteDTO {
    private Long id;
    private Long patientId;
    private Long medecinId;
    private String motif;
    private String dateArrivee;
    private StatutAffectation statut;

}