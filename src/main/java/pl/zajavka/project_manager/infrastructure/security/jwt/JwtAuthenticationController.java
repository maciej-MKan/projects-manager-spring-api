package pl.zajavka.project_manager.infrastructure.security.jwt;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import pl.zajavka.project_manager.domian.exception.InvalidCredentialsError;
import pl.zajavka.project_manager.infrastructure.security.ProjectManagerUserDetailsService;

@Slf4j
@RestController
@RequestMapping("/login")
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private ProjectManagerUserDetailsService userDetailsService;

	@GetMapping
	public ResponseEntity<?> test(){
		return ResponseEntity.ok("ok");
	}
	@PostMapping
	public ResponseEntity<?> createAuthenticationToken( @RequestBody @Valid JwtRequest authenticationRequest) throws Exception {

		authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());

		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getEmail());

		final String token = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new JwtResponse(token));
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (BadCredentialsException e) {
			log.warn("INVALID_CREDENTIALS");
			throw new InvalidCredentialsError("INVALID_CREDENTIALS", e);
		}
	}
}