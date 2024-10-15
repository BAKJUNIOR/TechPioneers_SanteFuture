package ci.techpioneers.santefurture.service.Impl;

import ci.techpioneers.santefurture.models.Patient;
import ci.techpioneers.santefurture.models.Role;
import ci.techpioneers.santefurture.models.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import ci.techpioneers.santefurture.models.enums.RoleName;
import ci.techpioneers.santefurture.repositories.PatientRepository;
import ci.techpioneers.santefurture.repositories.RoleRepository;
import ci.techpioneers.santefurture.repositories.UserRepository;
import ci.techpioneers.santefurture.repositories.ValidationCodeRepository;
import ci.techpioneers.santefurture.service.*;
import ci.techpioneers.santefurture.service.dto.*;
import ci.techpioneers.santefurture.service.mappers.PatientMapper;
import ci.techpioneers.santefurture.service.mappers.UserMapper;
import ci.techpioneers.santefurture.utils.SlugifyUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PatientMapper patientMapper;
    private final UserMapper userMapper;
    private final ValidationCodeService validationCodeService;
    private final ValidationCodeRepository validationCodeRepository;
    private final BCryptPasswordEncoder passwordEncoder;


    @Override
    public PatientDTO getPatientById(Long id) {
        log.debug("Request to get patient by ID: {}", id);

        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found"));
        log.debug("Patient found: {}", patient);

        return patientMapper.fromEntity(patient);
    }

    @Override
    public Optional<Patient> getPatientByUserId(Long userId) {
        return patientRepository.findByUser_Id(userId);
    }

    @Override
    public List<PatientDTO> getAllPatients() {
        log.debug("Request to get all patients");

        List<PatientDTO> patients = patientRepository.findAll().stream()
                .map(patientMapper::fromEntity)
                .collect(Collectors.toList());
        log.debug("Total patients found: {}", patients.size());

        return patients;
    }

    @Override
    @Transactional
    public void deletePatient(Long patientId) {
        log.debug("Request to delete patient with ID: {}", patientId);

        if (!patientRepository.existsById(patientId)) {
            throw new RuntimeException("Patient not found");
        }
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found"));
        validationCodeRepository.deleteByUserId(patient.getUser().getId());

        patientRepository.deleteById(patientId);
        log.debug("Patient deleted successfully: {}", patientId);
    }

    @Override
    public PatientDTO registerPatient(PatientDTO patientDTO) {
        log.debug("Request to register new patient: {}", patientDTO);

        Role patientRole = roleRepository.findByLibelle(RoleName.PATIENT)
                .orElseGet(() -> {
                    log.debug("Patient role not found, creating a new role");
                    Role newRole = new Role();
                    newRole.setLibelle(RoleName.PATIENT);
                    return roleRepository.save(newRole);
                });

        User user = userMapper.toEntity(patientDTO.getUser());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setDateCreation(Instant.now());
        user.setRoles(Set.of(patientRole));
        log.debug("User created for registration: {}", user);

        if (!user.getEmail().contains("@") || !user.getEmail().contains(".")) {
            throw new RuntimeException("Votre email est invalide");
        }

        Optional<User> utilisateurOptional = this.userRepository.findByEmail(user.getEmail());
        if (utilisateurOptional.isPresent()) {
            throw new RuntimeException("Votre email est déjà utilisé");
        }

        User savedUser = userRepository.save(user);
        log.debug("User saved successfully: {}", savedUser);

        Patient patient = patientMapper.toEntity(patientDTO);
        patient.setUser(savedUser);
        patient.setUrlPicture(patientDTO.getUrlPicture());
        patient.setSlug(SlugifyUtils.generate(patientDTO.getFirstName()));
        patient.setGender(patientDTO.getGender());
        patient.setPriorite(patientDTO.getPriorite());
        patient.setAdresse(patientDTO.getAdresse());

        Patient savedPatient = patientRepository.save(patient);
        log.debug("Patient registered successfully: {}", savedPatient);

        PatientDTO savedPatientDTO = patientMapper.fromEntity(savedPatient);
        savedPatientDTO.setUser(userMapper.fromEntity(savedUser));

        UserDTO userDTO = userMapper.fromEntity(savedUser);
        this.validationCodeService.enregistrerCode(userDTO);
        log.debug("Validation code registered for user: {}", userDTO);

        return savedPatientDTO;
    }

    public void activation(Map<String, String> activation) {
        log.debug("Request to activate user with code: {}", activation.get("code"));
        ValidationCodeDTO validationCodeDTO = validationCodeService.lireEnFonctionDuCode(activation.get("code"));
        log.debug("Validation code found: {}", validationCodeDTO);

        if (Instant.now().isAfter(validationCodeDTO.getExpiration())) {
            throw new RuntimeException("Votre code a expiré");
        }
        User userActiver = this.userRepository.findById(validationCodeDTO.getUser().getId())
                .orElseThrow(() -> new RuntimeException("Utilisateur inconnu"));
        log.debug("User to activate found: {}", userActiver);

        userActiver.setActive(true);
        userRepository.save(userActiver);
        log.debug("User activated successfully: {}", userActiver);
    }
}