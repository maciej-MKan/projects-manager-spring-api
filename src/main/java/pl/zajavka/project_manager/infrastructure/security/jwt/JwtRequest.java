package pl.zajavka.project_manager.infrastructure.security.jwt;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JwtRequest implements Serializable {

	@Serial
	private static final long serialVersionUID = 5926468583005150707L;

	@Valid
	@NotEmpty
	private String email;

	private String password;

}