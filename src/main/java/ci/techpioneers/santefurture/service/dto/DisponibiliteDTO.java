package ci.techpioneers.santefurture.service.dto;

import ci.techpioneers.santefurture.models.enums.JourSemaine;
import ci.techpioneers.santefurture.models.enums.StatutDisponibilite;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DisponibiliteDTO {
    private Long id;
    private Long medecinId; // Référence au médecin
    private JourSemaine jour;
    private LocalTime heureDebut;
    private LocalTime heureFin;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private StatutDisponibilite statut;
    private String raisonModification;
    private LocalDateTime dateModification;
}