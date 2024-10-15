package ci.techpioneers.santefurture.web.controller;

import ci.techpioneers.santefurture.models.AideSoignant;
import ci.techpioneers.santefurture.models.Medecin;
import ci.techpioneers.santefurture.models.Patient;
import ci.techpioneers.santefurture.models.User;
import ci.techpioneers.santefurture.models.enums.RoleName;
import ci.techpioneers.santefurture.repositories.UserRepository;
import ci.techpioneers.santefurture.security.SecurityUtils;
import ci.techpioneers.santefurture.service.AideSoignantService;
import ci.techpioneers.santefurture.service.MedecinService;
import ci.techpioneers.santefurture.service.PatientService;
import ci.techpioneers.santefurture.service.dto.JWTTokenDTO;
import ci.techpioneers.santefurture.service.dto.LoginDTO;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticateController {

    private final UserRepository userRepository;
    private final PatientService patientService;
    private final MedecinService medecinService;
    private final AideSoignantService aideSoignantService;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    @Value("${security.authentication.jwt.token-validity-in-seconds:0}")
    private long tokenValidityInSeconds;

    @Value("${security.authentication.jwt.token-validity-in-seconds-for-remember-me:0}")
    private long tokenValidityInSecondsForRememberMe;

    private final JwtEncoder jwtEncoder;

    @PostMapping("/authenticate")
    public JWTTokenDTO authorize(@RequestBody LoginDTO login) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                login.getUsername(),
                login.getPassword()
        );

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = createToken(authentication, login.isRumemberMe());
        return new JWTTokenDTO(jwt);
    }

    public String createToken(Authentication authentication, boolean rememberMe) {
        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        Long userId = user.getId();
        Long idUtilisateur = null;

        if (user.getRoles().stream().anyMatch(role -> role.getLibelle() == RoleName.PATIENT)) {
            Patient patient = patientService.getPatientByUserId(userId)
                    .orElseThrow(() -> new UsernameNotFoundException("Patient not found for user ID: " + userId));
            idUtilisateur = patient.getIdPerson();
        } else if (user.getRoles().stream().anyMatch(role -> role.getLibelle() == RoleName.MEDECIN)) {
            Medecin medecin = medecinService.getMedecinByUserId(userId)
                    .orElseThrow(() -> new UsernameNotFoundException("Medecin not found for user ID: " + userId));
            idUtilisateur = medecin.getIdPerson();
        } else if (user.getRoles().stream().anyMatch(role -> role.getLibelle() == RoleName.AIDE_SOIGNANT)) {
            AideSoignant aideSoignant = aideSoignantService.getAideSoignantByUserId(userId)
                    .orElseThrow(() -> new UsernameNotFoundException("AideSoignant not found for user ID: " + userId));
            idUtilisateur = aideSoignant.getIdPerson();
        } else {
            throw new UsernameNotFoundException("User role not recognized.");
        }

        String authorities = user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        Instant now = Instant.now();
        Instant validity = rememberMe ?
                now.plus(this.tokenValidityInSecondsForRememberMe, ChronoUnit.SECONDS) :
                now.plus(this.tokenValidityInSeconds, ChronoUnit.SECONDS);

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuedAt(now)
                .expiresAt(validity)
                .subject(authentication.getName())
                .claim(getIdClaimKey(user), idUtilisateur)
                .claim(SecurityUtils.AUTHORITIES_KEY, authorities)
                .build();

        JwsHeader jwsHeader = JwsHeader.with(SecurityUtils.JWT_ALGORITHM).build();
        return this.jwtEncoder.encode(JwtEncoderParameters.from(jwsHeader, claims)).getTokenValue();
    }

    private String getIdClaimKey(User user) {
        if (user.getRoles().stream().anyMatch(role -> role.getLibelle() == RoleName.PATIENT)) {
            return "patientId";
        } else if (user.getRoles().stream().anyMatch(role -> role.getLibelle() == RoleName.MEDECIN)) {
            return "medecinId";
        } else if (user.getRoles().stream().anyMatch(role -> role.getLibelle() == RoleName.AIDE_SOIGNANT)) {
            return "aideSoignantId";
        }
        return "PersonId";
    }
}