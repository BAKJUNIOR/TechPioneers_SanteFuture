package ci.techpioneers.santefurture.service.Impl;

import ci.techpioneers.santefurture.models.Medecin;
import ci.techpioneers.santefurture.models.Service;
import ci.techpioneers.santefurture.models.Role;
import ci.techpioneers.santefurture.models.User;
import ci.techpioneers.santefurture.models.enums.StatutMedecin;
import ci.techpioneers.santefurture.repositories.MedecinRepository;
import ci.techpioneers.santefurture.repositories.RoleRepository;
import ci.techpioneers.santefurture.repositories.ServiceRepository;
import ci.techpioneers.santefurture.repositories.UserRepository;
import ci.techpioneers.santefurture.service.MedecinService;
import ci.techpioneers.santefurture.service.dto.MedecinDTO;
import ci.techpioneers.santefurture.service.dto.RoleDTO;
import ci.techpioneers.santefurture.service.dto.UserDTO;
import ci.techpioneers.santefurture.service.mappers.MedecinMapper;
import ci.techpioneers.santefurture.service.mappers.UserMapper;
import ci.techpioneers.santefurture.utils.SlugifyUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
@Slf4j
public class MedecinServiceImpl implements MedecinService {
    private final MedecinRepository medecinRepository;
    private final ServiceRepository serviceRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final MedecinMapper medecinMapper;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public Optional<Medecin> getMedecinByUserId(Long userId) {
        return medecinRepository.findByUser_Id(userId);
    }

    @Override
    public MedecinDTO getAMedecinById(Long id) {
        log.debug("Request to get Medecin by ID: {}", id);
        Optional<Medecin> medecinOptional = medecinRepository.findById(id);
        if (medecinOptional.isEmpty()) {
            throw new RuntimeException("Medecin not found with ID: " + id);
        }
        MedecinDTO medecinDTO = medecinMapper.fromEntity(medecinOptional.get());
        log.debug("Medecin found: {}", medecinDTO);
        return medecinDTO;
    }

    @Override
    public List<MedecinDTO> getAllMedecin() {
        log.debug("Request to get all Medecins");
        List<Medecin> medecins = medecinRepository.findAll();
        List<MedecinDTO> medecinDTOs = medecins.stream()
                .map(medecinMapper::fromEntity)
                .collect(Collectors.toList());
        log.debug("Total Medecins found: {}", medecinDTOs.size());
        return medecinDTOs;
    }

    @Override
    public void deleteMedecin(Long id) {
        log.debug("Request to delete Medecin with ID: {}", id);
        if (!medecinRepository.existsById(id)) {
            throw new RuntimeException("Medecin not found with ID: " + id);
        }
        medecinRepository.deleteById(id);
        log.debug("Medecin deleted successfully: {}", id);
    }

    @Override
    public MedecinDTO registerMedecin(MedecinDTO medecinDTO) {
        log.debug("Request to register new Medecin: {}", medecinDTO);

        if (medecinDTO.getServiceId() == null) {
            throw new RuntimeException("Le service doit être spécifié.");
        }


        Service service = serviceRepository.findById(medecinDTO.getServiceId())
                .orElseThrow(() -> new RuntimeException("Service non trouvé avec ID: " + medecinDTO.getServiceId()));

        Set<RoleDTO> rolesDTO = medecinDTO.getUser().getRoles();
        Set<Role> roles = rolesDTO.stream()
                .map(roleDTO -> {
                    log.debug("Checking role : {}", roleDTO.getLibelle());
                    return roleRepository.findByLibelle(roleDTO.getLibelle())
                            .orElseThrow(() -> new RuntimeException("Rôle non trouvé : " + roleDTO.getLibelle()));
                })
                .collect(Collectors.toSet());

        User user = userMapper.toEntity(medecinDTO.getUser());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setDateCreation(Instant.now());
        user.setRoles(roles);
        user.setActive(true);
        log.debug(" User created for registration: {}", user);

        if (!user.getEmail().contains("@") || !user.getEmail().contains(".")) {
            throw new RuntimeException("Votre  email est invalide");
        }

        Optional<User> utilisateurOptional = this.userRepository.findByEmail(user.getEmail());
        if (utilisateurOptional.isPresent()) {
            throw new RuntimeException("Votre email est déjà utilisé");
        }

        User savedUser = userRepository.save(user);
        log.debug("User saved successfully: {}", savedUser);

        Medecin medecin = medecinMapper.toEntity(medecinDTO);
        medecin.setUser(savedUser);
        medecin.setSlug(SlugifyUtils.generate(medecinDTO.getFirstName()));
        medecin.setStatut(StatutMedecin.ACTIF);
        medecin.setService(service);



        Medecin savedMedecin = medecinRepository.save(medecin);
        log.debug("Medecin registered successfully: {}", savedMedecin);

        MedecinDTO savedMedecinDTO = medecinMapper.fromEntity(savedMedecin);
        savedMedecinDTO.setUser(userMapper.fromEntity(savedUser));

        return savedMedecinDTO;
    }
}
