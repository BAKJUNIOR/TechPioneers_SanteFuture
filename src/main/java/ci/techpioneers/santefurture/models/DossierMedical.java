package ci.techpioneers.santefurture.models;


import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.Instant;
import java.util.Set;

@Data
@Entity
@Table(name = "dossier_medical")
public class DossierMedical implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @Column(name = "date_creation")
    private Instant dateCreation;

    private String notes;

    @OneToMany(mappedBy = "dossierMedical", cascade = CascadeType.ALL)
    private Set<Consultation> consultations;


    @OneToMany(mappedBy = "dossierMedical", cascade = CascadeType.ALL)
    private Set<DonneesVitales> donneesVitales;

}