package ci.techpioneers.santefurture.models;


import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.Instant;
import java.util.Set;

@Data
@Entity
@Table(name = "tickets")
public class Ticket implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    @Column(name = "date_creation")
    private Instant dateCreation;

    private String statut;
    private double total;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL)
    private Set<Service> services;


    @OneToOne(mappedBy = "ticket", cascade = CascadeType.ALL)
    private Consultation consultation;

}