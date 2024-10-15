package ci.techpioneers.santefurture.models;

import ci.techpioneers.santefurture.models.enums.StatutAffectation;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "files_attente")
public class FileAttente implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "date_heure_arrivee")
    private LocalDateTime dateHeureArrivee;

    @Column(name = "statut")
    private StatutAffectation statut;

    @OneToOne
    @JoinColumn(name = "service_id")
    private Service service;


    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;



}