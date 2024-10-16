package ci.techpioneers.santefurture.models;


import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Entity
@Table(name = "tickets")
public class Ticket implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String codeFacture;
    @Column(name = "date_creation")
    private Instant dateCreation;
    private int Quantity;
    private double prixTotal;
    private LocalDateTime dateEmission;
    private LocalDateTime dateValidite;


    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @OneToMany( cascade = CascadeType.ALL)
    private Set<Service> services;


    @OneToOne(mappedBy = "ticket", cascade = CascadeType.ALL)
    private Consultation consultation;

}