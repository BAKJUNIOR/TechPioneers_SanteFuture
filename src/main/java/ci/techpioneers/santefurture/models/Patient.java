package ci.techpioneers.santefurture.models;


import ci.techpioneers.santefurture.models.enums.PrioritePatient;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "patients")
public class Patient extends Person {

    @OneToMany(mappedBy = "patient")
    private Set<Ticket> tickets;

    @Enumerated(EnumType.STRING)
    private PrioritePatient priorite;

    @OneToOne(mappedBy = "patient", cascade = CascadeType.ALL)
    private DossierMedical dossierMedical;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<Affectation> affectations;

}