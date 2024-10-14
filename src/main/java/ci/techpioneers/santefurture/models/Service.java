package ci.techpioneers.santefurture.models;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
@Table(name = "services")
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private double prixUnitaire;


    @OneToMany(mappedBy = "service", cascade = CascadeType.ALL)
    private Set<Medecin> medecins;

    @ManyToOne
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;



}