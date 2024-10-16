package ci.techpioneers.santefurture.service.Impl;

import ci.techpioneers.santefurture.models.User;
import ci.techpioneers.santefurture.repositories.UserRepository;
import ci.techpioneers.santefurture.service.UserService;
import ci.techpioneers.santefurture.service.dto.UserDTO;
import ci.techpioneers.santefurture.service.mappers.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDTO getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username;

        if (authentication != null) {
            if (authentication.getPrincipal() instanceof UserDetails springSecurityUser) {
                username = springSecurityUser.getUsername();
            } else if (authentication.getPrincipal() instanceof String s) {
                username = s;
            } else {
                username = null;
            }
        } else {
            username = null;
        }

        if (username == null) {
            throw new RuntimeException("Utilisateur non authentifié");
        }

        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé : " + username));

        return userMapper.fromEntity(user);
    }
}
