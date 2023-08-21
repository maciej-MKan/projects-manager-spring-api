package pl.zajavka.project_manager.infrastructure.repository.jpa;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import pl.zajavka.project_manager.infrastructure.database.entity.CommentEntity;
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
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class CommentJpaRepositoryTest {

    private CommentJpaRepository commentJpaRepository;
    private ProjectJpaRepository projectJpaRepository;
    private UserJpaRepository userJpaRepository;

    @BeforeEach
    void initData() {
        List<UserEntity> users = List.of(someUser1(), someUser2(), someUser3());
        userJpaRepository.saveAllAndFlush(users);
        List<ProjectEntity> projects = List.of(someProject1(), someProject2(), someProject3());
        projectJpaRepository.saveAllAndFlush(projects);
    }

    @AfterEach
    void cleanData() {
        userJpaRepository.deleteAll();
        projectJpaRepository.deleteAll();
    }

    @Test
    void commentsSaveCorrectly() {
        List<CommentEntity> comments = List.of(someComment1(), someComment2(), someComment3());
        commentJpaRepository.saveAllAndFlush(comments);

        List<CommentEntity> commentEntityList = commentJpaRepository.findAll();

        assertThat(commentEntityList).hasSize(3);
    }

    @Test
    void updateComment() {

        //given
        CommentEntity comment = someComment1();
        comment.setCommentId(1);
        commentJpaRepository.saveAndFlush(comment);
        CommentEntity updatedComment = someComment1();
        updatedComment.setCommentId(1);
        updatedComment.setComment("Updated Comment");

        //when
        Optional<CommentEntity> foundComment = commentJpaRepository.findById(comment.getCommentId());
        commentJpaRepository.saveAndFlush(updatedComment);
        Optional<CommentEntity> result = commentJpaRepository.findById(comment.getCommentId());

        //then
        assertTrue(foundComment.isPresent());
        assertTrue(result.isPresent());
        assertThat(result.get().getComment()).isEqualTo("Updated Comment");
    }

    @Test
    void deleteComment() {

        //given
        CommentEntity comment = someComment1();
        commentJpaRepository.saveAndFlush(comment);

        //when
        commentJpaRepository.deleteById(comment.getCommentId());

        //then
        Optional<CommentEntity> deletedComment = commentJpaRepository.findById(comment.getCommentId());
        assertFalse(deletedComment.isPresent());
    }
}
