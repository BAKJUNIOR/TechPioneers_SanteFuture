package ci.techpioneers.santefurture.service.Impl;

import ci.techpioneers.santefurture.models.User;
import ci.techpioneers.santefurture.models.ValidationCode;
import ci.techpioneers.santefurture.repositories.UserRepository;
import ci.techpioneers.santefurture.repositories.ValidationCodeRepository;
import ci.techpioneers.santefurture.service.NotificationCodeService;
import ci.techpioneers.santefurture.service.ValidationCodeService;
import ci.techpioneers.santefurture.service.dto.UserDTO;
import ci.techpioneers.santefurture.service.dto.ValidationCodeDTO;
import ci.techpioneers.santefurture.service.mappers.UserMapper;
import ci.techpioneers.santefurture.service.mappers.validationCodeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Random;

import static java.time.temporal.ChronoUnit.MINUTES;

@Service
@RequiredArgsConstructor
public class ValidationCodeServiceImpl implements ValidationCodeService {

    private final ValidationCodeRepository validationCodeRepository;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final NotificationCodeService notificationCodeService;
    private final validationCodeMapper validationCodeMapper;

    @Override
    public void enregistrerCode(UserDTO userDTO) {
        User user = userRepository.findById(userDTO.getId())
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        ValidationCodeDTO validationCodeDTO = new ValidationCodeDTO();
        validationCodeDTO.setUser(userMapper.fromEntity(user));
        Instant creation = Instant.now();
        validationCodeDTO.setCreation(creation);
        Instant expiration = creation.plus(10, MINUTES);
        validationCodeDTO.setExpiration(expiration);

        Random random = new Random();
        int randomInteger = random.nextInt(999999);
        String code = String.format("%06d", randomInteger);
        validationCodeDTO.setCode(code);

        ValidationCode validationCodeEntity = validationCodeMapper.toEntity(validationCodeDTO);

        validationCodeRepository.save(validationCodeEntity);
        notificationCodeService.envoyerCode(validationCodeDTO);
    }

    @Override
    public ValidationCodeDTO lireEnFonctionDuCode(String code) {
        ValidationCode validationCode = this.validationCodeRepository.findByCode(code)
                .orElseThrow(() -> new RuntimeException("Votre code est invalide"));
        return validationCodeMapper.fromEntity(validationCode);
    }

}
