package pl.zajavka.project_manager.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import pl.zajavka.project_manager.domian.exception.InvalidCredentialsError;
import pl.zajavka.project_manager.infrastructure.security.ProjectManagerUserDetailsService;
import pl.zajavka.project_manager.infrastructure.security.jwt.JwtAuthenticationController;
import pl.zajavka.project_manager.infrastructure.security.jwt.CredentialsDTO;
import pl.zajavka.project_manager.infrastructure.security.jwt.JwtTokenUtil;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@Slf4j
@WebMvcTest(controllers = JwtAuthenticationController.class)
@AutoConfigureMockMvc(addFilters = false)
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class LoginControllerTest {

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @MockBean
    private JwtTokenUtil jwtTokenUtil;

    @MockBean
    private AuthenticationManager authenticationManager;

    @MockBean
    private ProjectManagerUserDetailsService userDetailsService;

    private static UserDetails someUserDetails() {

        return new User(
                "email@example.com",
                "password",
                true,
                true,
                true,
                true,
                List.of(new SimpleGrantedAuthority("User"))
        );
    }

    @Test
    void thatReturnsTokenCorrectly() throws Exception {
        //given
        UserDetails someUser = someUserDetails();
        CredentialsDTO credentials = CredentialsDTO
                .builder()
                .email(someUser.getUsername())
                .password(someUser.getPassword())
                .build();
        String requestBody = objectMapper.writeValueAsString(credentials);

        when(userDetailsService.loadUserByUsername(any())).thenReturn(someUser);

        when(userDetailsService.generateToken(any())).thenReturn("some token");

        //when, then
        MvcResult mvcResult = mockMvc.perform(post("/login")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").isNotEmpty())
                .andReturn();

        log.info(mvcResult.getResponse().getContentAsString());
    }

    @Test
    void thatReturnsTokenCorrectlyForUsernameAsEmail() throws Exception {
        //given
        UserDetails someUser = someUserDetails();
        Map<String, String> credentials = Map.of(
                "username", someUser.getUsername(),
                "password", someUser.getPassword()
        );
        String requestBody = objectMapper.writeValueAsString(credentials);

        when(userDetailsService.loadUserByUsername(any())).thenReturn(someUser);

        when(userDetailsService.generateToken(any())).thenReturn("some token");

        //when, then
        MvcResult mvcResult = mockMvc.perform(post("/login")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").isNotEmpty())
                .andReturn();

        log.info(mvcResult.getResponse().getContentAsString());

    }

    @Test
    void thatReturnsBadRequestForNoEmail() throws Exception {
        //given
        UserDetails someUser = someUserDetails();
        CredentialsDTO credentials = CredentialsDTO
                .builder()
                .password(someUser.getPassword())
                .build();
        String requestBody = objectMapper.writeValueAsString(credentials);

        //when, then
        MvcResult mvcResult = mockMvc.perform(post("/login")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").isNotEmpty())
                .andReturn();
        assertThat(mvcResult.getResponse().getContentAsString()).contains("Validation failed");

    }

    @Test
    void thatReturnsBadRequestForNoPassword() throws Exception {
        //given
        UserDetails someUser = someUserDetails();
        CredentialsDTO credentials = CredentialsDTO
                .builder()
                .email(someUser.getUsername())
                .build();
        String requestBody = objectMapper.writeValueAsString(credentials);

        //when, then
        MvcResult mvcResult = mockMvc.perform(post("/login")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").isNotEmpty())
                .andReturn();
        assertThat(mvcResult.getResponse().getContentAsString()).contains("Validation failed");

    }

    @Test
    void thatReturnsUnauthorizedForWrongEmail() throws Exception {
        //given
        UserDetails someUser = someUserDetails();
        CredentialsDTO credentials = CredentialsDTO
                .builder()
                .email("wrong@email.com")
                .password(someUser.getPassword())
                .build();
        String requestBody = objectMapper.writeValueAsString(credentials);

        doThrow(new InvalidCredentialsError("INVALID_CREDENTIALS", new BadCredentialsException("")))
                .when(userDetailsService)
                .checkAuthenticate(eq(credentials.getEmail()), any(), any(AuthenticationManager.class));
        when(userDetailsService.generateToken(any())).thenReturn("some token");

        //when, then
        MvcResult mvcResult = mockMvc.perform(post("/login")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.message").isNotEmpty())
                .andReturn();
        assertThat(mvcResult.getResponse().getContentAsString()).contains("Invalid Credentials");

    }

    @Test
    void thatReturnsUnauthorizedForWrongPassword() throws Exception {
        //given
        UserDetails someUser = someUserDetails();
        CredentialsDTO credentials = CredentialsDTO
                .builder()
                .email(someUser.getUsername())
                .password("wrongPassword")
                .build();
        String requestBody = objectMapper.writeValueAsString(credentials);

        doThrow(new InvalidCredentialsError("INVALID_CREDENTIALS", new BadCredentialsException("")))
                .when(userDetailsService)
                .checkAuthenticate(any(), eq(credentials.getPassword()), any(AuthenticationManager.class));

        when(authenticationManager.authenticate(any())).thenThrow(
                new InvalidCredentialsError("INVALID_CREDENTIALS",
                        new BadCredentialsException(""))
        );

        //when, then
        MvcResult mvcResult = mockMvc.perform(post("/login")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.message").isNotEmpty())
                .andReturn();
        assertThat(mvcResult.getResponse().getContentAsString()).contains("Invalid Credentials");

    }
}

