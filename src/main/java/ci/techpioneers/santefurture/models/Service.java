package ci.techpioneers.santefurture.models;


import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

@Data
@Entity
@Table(name = "services")
public class Service implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private double prixUnitaire;

    @OneToMany(mappedBy = "service", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Medecin> medecins;


    @ManyToOne
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;



}