package pl.zajavka.project_manager.infrastructure.repository.jpa;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import pl.zajavka.project_manager.infrastructure.database.entity.UserEntity;
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
public class UserJpaRepositoryTest {

    private final UserJpaRepository userJpaRepository;
    @Test
    void usersSaveCorrectly(){

        //given
        List<UserEntity> users = List.of(someUser1(), someUser2(), someUser3());
        userJpaRepository.saveAllAndFlush(users);

        //when
        List<UserEntity> userEntityList = userJpaRepository.findAll();

        //then
        assertThat(userEntityList).hasSize(3);

    }

    @Test
    void updateUser() {
        // Given
        UserEntity user = someUser1();
        userJpaRepository.saveAndFlush(user);

        // When
        Optional<UserEntity> foundUser = userJpaRepository.findById(user.getUserId());
        assertTrue(foundUser.isPresent());

        foundUser.ifPresent(u -> {
            u.setName("Updated Name");
            userJpaRepository.saveAndFlush(u);
        });

        // Then
        Optional<UserEntity> updatedUser = userJpaRepository.findById(user.getUserId());
        assertTrue(updatedUser.isPresent());
        assertThat(updatedUser.get().getName()).isEqualTo("Updated Name");
    }

    @Test
    void deleteUser() {
        // Given
        UserEntity user = someUser1();
        userJpaRepository.saveAndFlush(user);

        // When
        userJpaRepository.deleteById(user.getUserId());

        // Then
        Optional<UserEntity> deletedUser = userJpaRepository.findById(user.getUserId());
        assertFalse(deletedUser.isPresent());
    }
}
