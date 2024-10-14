package ci.techpioneers.santefurture.service.dto;

import ci.techpioneers.santefurture.models.enums.RoleName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleDTO {
    private Long idRole;
    private RoleName libelle;
}