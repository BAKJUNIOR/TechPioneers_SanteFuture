package ci.techpioneers.santefurture.models;

import ci.techpioneers.santefurture.models.enums.StatutMedecin;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
@Table(name = "medecins")
public class Medecin extends Person  {
    private String specialite;
    private String experience;

    @Enumerated(EnumType.STRING)
    private StatutMedecin statut;

    @ManyToOne
    @JoinColumn(name = "service_id")
    private Service service;

    @OneToMany(mappedBy = "medecin", cascade = CascadeType.ALL)
    private Set<Consultation> consultations;



}