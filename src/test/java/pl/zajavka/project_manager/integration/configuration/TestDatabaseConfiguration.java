package pl.zajavka.project_manager.integration.configuration;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import pl.zajavka.project_manager.infrastructure.database.entity.ProjectEntity;
import pl.zajavka.project_manager.infrastructure.database.entity.UserEntity;
import pl.zajavka.project_manager.infrastructure.database.repository.jpa.ProjectJpaRepository;
import pl.zajavka.project_manager.infrastructure.database.repository.jpa.UserJpaRepository;

import java.util.List;

import static pl.zajavka.project_manager.util.Fixtures.*;

@TestConfiguration
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class TestDatabaseConfiguration {

    private UserJpaRepository userJpaRepository;
    private ProjectJpaRepository projectJpaRepository;

    @Bean
    public void initializeDatabase() {
        UserEntity user1 = someUser1();
        UserEntity user2 = someUser2();
        userJpaRepository.saveAll(List.of(user1, user2));

        ProjectEntity project1 = someProject1();
        ProjectEntity project2 = someProject2();
        projectJpaRepository.saveAll(List.of(project1, project2));
    }
}
