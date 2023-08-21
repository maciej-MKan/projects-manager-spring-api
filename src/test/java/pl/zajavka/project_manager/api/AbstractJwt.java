package pl.zajavka.project_manager.api;

import org.springframework.boot.test.mock.mockito.MockBean;
import pl.zajavka.project_manager.infrastructure.security.ProjectManagerUserDetailsService;
import pl.zajavka.project_manager.infrastructure.security.jwt.JwtTokenUtil;

public abstract class AbstractJwt {
    @MockBean
    private ProjectManagerUserDetailsService projectManagerUserDetailsService;

    @MockBean
    private JwtTokenUtil jwtTokenUtil;
}
