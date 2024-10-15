package ci.techpioneers.santefurture.service.Impl;

import ci.techpioneers.santefurture.models.AideSoignant;
import ci.techpioneers.santefurture.models.Patient;
import ci.techpioneers.santefurture.models.Role;
import ci.techpioneers.santefurture.models.User;
import ci.techpioneers.santefurture.repositories.AideSoignantRepository;
import ci.techpioneers.santefurture.repositories.RoleRepository;
import ci.techpioneers.santefurture.repositories.UserRepository;
import ci.techpioneers.santefurture.service.AideSoignantService;
import ci.techpioneers.santefurture.service.dto.*;
import ci.techpioneers.santefurture.service.mappers.AideSoignantMapper;
import ci.techpioneers.santefurture.service.mappers.UserMapper;
import ci.techpioneers.santefurture.utils.SlugifyUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@Slf4j
@Service
@RequiredArgsConstructor
public class AideSoignantServiceImpl implements AideSoignantService {
    private final AideSoignantRepository aideSoignantRepository;
    private final AideSoignantMapper aideSoignantMapper;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserMapper userMapper;


    @Override
    public Optional<AideSoignant> getAideSoignantByUserId(Long userId) {
        return aideSoignantRepository.findByUser_Id(userId);
    }

    @Override
    public AideSoignantDTO getAideSoignantById(Long id) {
        log.debug("Request to get aideSoignant by ID: {}", id);

        AideSoignant aideSoignant = aideSoignantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("AideSoignant not found"));
        log.debug("AideSoignant found: {}", aideSoignant);

        return aideSoignantMapper.fromEntity(aideSoignant);
    }

    @Override
    public List<AideSoignantDTO> getAllAideSoignant() {
        log.debug("Request to get all aideSoignant");

        List<AideSoignantDTO> aideSoignant = aideSoignantRepository.findAll().stream()
                .map(aideSoignantMapper::fromEntity)
                .toList();
        log.debug("Total aideSoignant found: {}", aideSoignant.size());
        return aideSoignant;
    }
    @Override
    public void deleteAideSoignant(Long aideSoignantId) {
        log.debug("Request to delete AideSoignant with ID: {}", aideSoignantId);

        if (!aideSoignantRepository.existsById(aideSoignantId)) {
            throw new RuntimeException("AideSoignant not found");
        }

        aideSoignantRepository.deleteById(aideSoignantId);
        log.debug("AideSoignant deleted successfully: {}", aideSoignantId);
    }

    @Override
    public AideSoignantDTO registerAideSoignant(AideSoignantDTO aideSoignantDTO) {
        log.debug("Request to register new AideSoignant: {}", aideSoignantDTO);

        Set<RoleDTO> rolesDTO = aideSoignantDTO.getUser().getRoles();
        Set<Role> roles = rolesDTO.stream()
                .map(roleDTO -> {
                    log.debug("Checking role: {}", roleDTO.getLibelle());
                    return roleRepository.findByLibelle(roleDTO.getLibelle())
                            .orElseThrow(() -> new RuntimeException("Rôle non trouvé : " + roleDTO.getLibelle()));
                })
                .collect(Collectors.toSet());

        User user = userMapper.toEntity(aideSoignantDTO.getUser());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setDateCreation(Instant.now());
        user.setRoles(roles);
        user.setActive(true);
        log.debug("User  created for registration: {}", user);

        if (!user.getEmail().contains("@") || !user.getEmail().contains(".")) {
            throw new RuntimeException("Votre email est  invalide");
        }

        Optional<User> utilisateurOptional = this.userRepository.findByEmail(user.getEmail());
        if (utilisateurOptional.isPresent()) {
            throw new RuntimeException("Votre email est déjà utilisé");
        }

        User savedUser = userRepository.save(user);
        log.debug("User saved successfully: {}", savedUser);

        AideSoignant aideSoignant = aideSoignantMapper.toEntity(aideSoignantDTO);
        aideSoignant.setUser(savedUser);
        aideSoignant.setSlug(SlugifyUtils.generate(aideSoignantDTO.getFirstName()));

        AideSoignant savedAideSoignant = aideSoignantRepository.save(aideSoignant);
        log.debug("AideSoignant registered successfully: {}", savedAideSoignant);

        AideSoignantDTO savedAideSoignantDTO = aideSoignantMapper.fromEntity(savedAideSoignant);
        savedAideSoignantDTO.setUser(userMapper.fromEntity(savedUser));

        return savedAideSoignantDTO;
    }
}
