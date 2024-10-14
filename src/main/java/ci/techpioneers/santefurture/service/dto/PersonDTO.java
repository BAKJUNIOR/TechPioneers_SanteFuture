package ci.techpioneers.santefurture.service.dto;

import ci.techpioneers.santefurture.models.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonDTO {
    private Long idPerson;
    private String firstName;
    private String lastName;
    private String numbers;
    private LocalDate dateOfBirth;
    private String urlPicture;
    private String slug;
    private Gender gender;
    private String adresse;
    private UserDTO user;
}