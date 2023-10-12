package pl.zajavka.project_manager.infrastructure.security.jwt;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;
import pl.zajavka.project_manager.infrastructure.security.ProjectManagerUserDetailsService;

@Slf4j
@RestController
@CrossOrigin(maxAge = 3600)
@RequestMapping(value = {"/login", "/login/"})
public class JwtAuthenticationController {

	@Autowired
	private ProjectManagerUserDetailsService userDetailsService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@GetMapping
	public ResponseEntity<?> test(){
		return ResponseEntity.ok("ok");
	}
	@PostMapping
	public ResponseEntity<?> createAuthenticationToken( @RequestBody @Valid CredentialsDTO authenticationRequest) throws Exception {

		log.info("Handle request body [{}]", authenticationRequest);

		String requestEmail = authenticationRequest.getEmail();
		userDetailsService.checkAuthenticate(requestEmail, authenticationRequest.getPassword(), authenticationManager);

		String token = userDetailsService.generateToken(requestEmail);

		String userId = userDetailsService.findUserId(requestEmail).toString();

		return ResponseEntity.ok(
				new JwtDTO(
						token,
						userId
				)
		);
	}

}