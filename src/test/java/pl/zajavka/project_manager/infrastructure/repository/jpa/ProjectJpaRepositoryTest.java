package pl.zajavka.project_manager.infrastructure.repository.jpa;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import pl.zajavka.project_manager.infrastructure.database.entity.ProjectEntity;
import pl.zajavka.project_manager.infrastructure.database.entity.UserEntity;
import pl.zajavka.project_manager.infrastructure.database.repository.jpa.CommentJpaRepository;
import pl.zajavka.project_manager.infrastructure.database.repository.jpa.ProjectJpaRepository;
import pl.zajavka.project_manager.infrastructure.database.repository.jpa.UserJpaRepository;
import pl.zajavka.project_manager.integration.configuration.PersistenceContainerTestConfiguration;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static pl.zajavka.project_manager.util.Fixtures.*;

@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.yml")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(PersistenceContainerTestConfiguration.class)
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ProjectJpaRepositoryTest {

    private ProjectJpaRepository projectJpaRepository;
    private UserJpaRepository userJpaRepository;

    @BeforeEach
    void initData() {
        List<UserEntity> users = List.of(someUser1(), someUser2(), someUser3());
        users.forEach(userJpaRepository::saveAndFlush);
    }

    @AfterEach
    void cleanData() {
        userJpaRepository.deleteAll();
    }

    @Test
    void projectsSaveCorrectly() {
        List<ProjectEntity> projects = List.of(someProject1(), someProject2(), someProject3());
        projectJpaRepository.saveAllAndFlush(projects);

        List<ProjectEntity> projectEntityList = projectJpaRepository.findAll();

        assertThat(projectEntityList).hasSize(3);
    }

    @Test
    void updateProject() {
        ProjectEntity project = someProject1();
        projectJpaRepository.saveAndFlush(project);

        Optional<ProjectEntity> foundProject = projectJpaRepository.findById(project.getProjectId());
        assertTrue(foundProject.isPresent());

        foundProject.ifPresent(p -> {
            p.setName("Updated Project");
            projectJpaRepository.saveAndFlush(p);
        });

        Optional<ProjectEntity> updatedProject = projectJpaRepository.findById(project.getProjectId());
        assertTrue(updatedProject.isPresent());
        assertThat(updatedProject.get().getName()).isEqualTo("Updated Project");
    }

    @Test
    void deleteProject() {
        ProjectEntity project = someProject1();
        projectJpaRepository.saveAndFlush(project);

        projectJpaRepository.deleteById(project.getProjectId());

        Optional<ProjectEntity> deletedProject = projectJpaRepository.findById(project.getProjectId());
        assertFalse(deletedProject.isPresent());
    }
}
