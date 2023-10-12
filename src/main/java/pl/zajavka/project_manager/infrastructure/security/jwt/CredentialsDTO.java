package pl.zajavka.project_manager.infrastructure.security.jwt;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CredentialsDTO implements Serializable {

	@Serial
	private static final long serialVersionUID = 5926468583005150707L;

	@Valid
	@NotEmpty
	@JsonAlias({"username"})
	private String email;

	@Valid
	@NotEmpty
	private String password;

}