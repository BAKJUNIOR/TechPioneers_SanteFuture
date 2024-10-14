package ci.techpioneers.santefurture.models;


import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.Instant;

@Data
@Entity
@Table(name = "donnees_vitales")
public class DonneesVitales implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    private double temperature;
    private double poids;
    private double taille;
    private String symptomes;
    private String remarques;

    @Column(name = "date_enregistrement")
    private Instant dateEnregistrement;

    @ManyToOne
    @JoinColumn(name = "aide_soignant_id")
    private AideSoignant aideSoignant;

    @ManyToOne
    @JoinColumn(name = "dossier_medical_id")
    private DossierMedical dossierMedical;



}