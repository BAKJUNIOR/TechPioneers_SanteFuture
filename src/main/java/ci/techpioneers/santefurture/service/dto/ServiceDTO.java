package ci.techpioneers.santefurture.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceDTO {
    private Long id;
    private String nom;
    private String description;
    private String code;
    private double prixUnitaire;
    private Set<MedecinDTO> medecins;
}