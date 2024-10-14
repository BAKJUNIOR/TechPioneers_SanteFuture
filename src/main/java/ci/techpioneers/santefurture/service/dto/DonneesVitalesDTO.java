package ci.techpioneers.santefurture.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DonneesVitalesDTO {
    private Long id;
    private Long patientId;
    private double temperature;
    private double poids;
    private double taille;
    private String symptomes;
    private String remarques;
    private Instant dateEnregistrement;
    private Long aideSoignantId;
    private Long dossierMedicalId;
}