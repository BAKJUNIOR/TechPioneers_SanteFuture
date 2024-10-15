package ci.techpioneers.santefurture.service;

import ci.techpioneers.santefurture.service.dto.ValidationCodeDTO;

public interface NotificationCodeService {
    void envoyerCode(ValidationCodeDTO validationCodeDTO);
}