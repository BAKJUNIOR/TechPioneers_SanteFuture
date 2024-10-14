package ci.techpioneers.santefurture.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketDTO {
    private Long id;
    private String code;
    private Instant dateCreation;
    private String statut;
    private double total;
    private Long patientId;
    private List<ServiceDTO> services;
    private Long consultationId;
}