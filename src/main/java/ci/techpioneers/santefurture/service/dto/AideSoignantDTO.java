package ci.techpioneers.santefurture.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AideSoignantDTO extends PersonDTO {
    private String specialite;
    private int experience;
}