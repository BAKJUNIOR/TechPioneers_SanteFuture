package ci.techpioneers.santefurture.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AideSoignantDTO extends UserDTO {
    private String specialite;
    private int experience;
    private boolean disponibilite;
}