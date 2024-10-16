package ci.techpioneers.santefurture.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketDTO {
    private Long id;
    private String codeFacture;
    private int quantity;
    private double prixTotal;
    private LocalDateTime dateEmission;
    private LocalDateTime dateValidite;
    private Instant dateCreation;
    private double total;
    private String emailPatient;
    private String nomComplet;
    private String age;
    private LocalDate DateNaissance;
    private Long patientId;
    private List<ServiceDTO> services;
//    private Long consultationId;


}