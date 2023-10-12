package pl.zajavka.project_manager.infrastructure.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.zajavka.project_manager.domian.exception.InvalidCredentialsError;
import pl.zajavka.project_manager.infrastructure.database.entity.UserEntity;
import pl.zajavka.project_manager.infrastructure.database.repository.jpa.UserJpaRepository;
import pl.zajavka.project_manager.infrastructure.security.jwt.JwtTokenUtil;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProjectManagerUserDetailsService implements UserDetailsService {

    private final UserJpaRepository usersRepository;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        log.info("searching user for email [{}]", email);
        Optional<UserEntity> user = usersRepository.findAllByEmail(email);
        if (user.isEmpty()) {
            log.error("User with email [{}] not found", email);
            throw new UsernameNotFoundException("Not found!");
        }
        List<GrantedAuthority> authorities = new LinkedList<>();
        authorities.add(new SimpleGrantedAuthority("User"));
        if (user.get().getSuperUser()){
            authorities.add(new SimpleGrantedAuthority("SuperUser"));
        }
        return buildUserForAuthentication(user.get(), authorities);
    }

    public Integer findUserId(String email){
        log.info("searching userId for email [{}]", email);
        return usersRepository.findAllByEmail(email).orElseThrow().getUserId();
    }

    public void checkAuthenticate(String username, String password, AuthenticationManager authenticationManager) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (BadCredentialsException e) {
            log.warn("INVALID_CREDENTIALS");
            throw new InvalidCredentialsError("INVALID_CREDENTIALS", e);
        }
    }

    private UserDetails buildUserForAuthentication(UserEntity user, List<GrantedAuthority> authorities) {
        User userWithRoles = new User(
                user.getEmail(),
                user.getPassword(),
                true,
                true,
                true,
                true,
                authorities
        );
        log.info("Created user [{}] with auth [{}]", userWithRoles.getUsername(), userWithRoles.getAuthorities());
        return userWithRoles;
    }

    public String generateToken(String requestEmail) {
        jwtTokenUtil.generateToken(requestEmail);
        return jwtTokenUtil.generateToken(requestEmail);
    }
}
