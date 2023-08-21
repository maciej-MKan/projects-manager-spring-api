package pl.zajavka.project_manager.infrastructure.repository.jpa;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
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

@Slf4j
@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.yml")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(PersistenceContainerTestConfiguration.class)
@AllArgsConstructor(onConstructor = @__(@Autowired))
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ProjectJpaRepositoryTest {

    private ProjectJpaRepository projectJpaRepository;
    private UserJpaRepository userJpaRepository;

    @BeforeEach
    void initData() {
        List<UserEntity> users = List.of(someUser1(), someUser2(), someUser3());
        users.forEach(userJpaRepository::saveAndFlush);
        List<UserEntity> userEntityList = userJpaRepository.findAll();
        assertFalse(userEntityList.isEmpty());
    }

    @AfterEach
    void cleanData() {
        userJpaRepository.deleteAll();
        projectJpaRepository.deleteAll();
    }

    @Test
    void projectsSaveCorrectly() {

        //given
        List<ProjectEntity> projects = List.of(someProject1(), someProject2(), someProject3());
        projectJpaRepository.saveAllAndFlush(projects);

        //when
        List<ProjectEntity> projectEntityList = projectJpaRepository.findAll();

        //then
        assertThat(projectEntityList).hasSize(3);
    }

    @Test
    void updateProject() {
        // Given
        ProjectEntity project = someProject1();
        project.setProjectId(1);
        projectJpaRepository.save(project);

        // When
        ProjectEntity updatedProject = someProject1();
        updatedProject.setProjectId(1);
        updatedProject.setName("Updated Project");
        projectJpaRepository.saveAndFlush(updatedProject);

        // Then
        Optional<ProjectEntity> result = projectJpaRepository.findById(1);
        assertTrue(result.isPresent());
        assertThat(result.get().getName()).isEqualTo("Updated Project");
    }

    @Test
    void deleteProject() {

        //given
        ProjectEntity project = someProject1();
        projectJpaRepository.saveAndFlush(project);

        //when
        projectJpaRepository.deleteById(project.getProjectId());

        //then
        Optional<ProjectEntity> deletedProject = projectJpaRepository.findById(project.getProjectId());
        assertFalse(deletedProject.isPresent());
    }
}
