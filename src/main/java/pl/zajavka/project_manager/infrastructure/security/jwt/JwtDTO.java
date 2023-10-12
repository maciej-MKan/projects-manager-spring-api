package pl.zajavka.project_manager.infrastructure.security.jwt;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@RequiredArgsConstructor
public class JwtDTO implements Serializable {

	@Serial
	private static final long serialVersionUID = -8091879091924046844L;

	@JsonProperty("token")
	private final String jwttoken;

	@JsonProperty("user_id")
	private final String userId;

}