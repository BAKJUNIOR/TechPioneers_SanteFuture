package ci.techpioneers.santefurture.service.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Data
@Getter
@Setter
public class RegistrationDTO extends PatientDTO {
    private MultipartFile file;
}
