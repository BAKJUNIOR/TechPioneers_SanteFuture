package ci.techpioneers.santefurture.models;


import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Set;

@Data
@Entity
@Table(name = "aide_soignant")
public class AideSoignant extends Person {
    private String specialite;
    private int experience;
    private boolean disponibilite;

    @OneToMany(mappedBy = "aideSoignant")
    private Set<DonneesVitales> donneesVitales;

}