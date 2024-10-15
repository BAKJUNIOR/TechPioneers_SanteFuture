package ci.techpioneers.santefurture.service;

import ci.techpioneers.santefurture.service.dto.UserDTO;
import ci.techpioneers.santefurture.service.dto.ValidationCodeDTO;

public interface ValidationCodeService {

    void enregistrerCode(UserDTO userDTO);
     ValidationCodeDTO lireEnFonctionDuCode(String code);

}