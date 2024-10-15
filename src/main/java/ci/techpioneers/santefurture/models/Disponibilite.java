package ci.techpioneers.santefurture.models;

import ci.techpioneers.santefurture.models.enums.JourSemaine;
import ci.techpioneers.santefurture.models.enums.StatutDisponibilite;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "disponibilites")
public class Disponibilite implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "medecin_id", nullable = false)
    private Medecin medecin;

    @Enumerated(EnumType.STRING)
    private JourSemaine jour;

    private LocalTime heureDebut;
    private LocalTime heureFin;

    private LocalDate dateDebut;
    private LocalDate dateFin;

    @Enumerated(EnumType.STRING)
    private StatutDisponibilite statut;

    private String raisonModification;

    private LocalDateTime dateModification;
}