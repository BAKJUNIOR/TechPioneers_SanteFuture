package ci.techpioneers.santefurture.service.dto;

import ci.techpioneers.santefurture.models.Person;
import ci.techpioneers.santefurture.models.enums.PrioritePatient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientDTO extends PersonDTO {
//    private List<TicketDTO> tickets;
    private PrioritePatient priorite;
//    private Long dossierMedicalId;
//    private List<AffectationDTO> affectations;
}