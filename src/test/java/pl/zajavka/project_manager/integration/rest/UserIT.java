package pl.zajavka.project_manager.integration.rest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.TestPropertySource;
import pl.zajavka.project_manager.infrastructure.database.entity.UserEntity;
import pl.zajavka.project_manager.infrastructure.database.repository.jpa.UserJpaRepository;
import pl.zajavka.project_manager.integration.configuration.AbstractIT;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertFalse;


@Disabled
@TestPropertySource(locations = "classpath:application-test.yml")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserIT extends AbstractIT {

//    @Autowired
    private UserJpaRepository userJpaRepository;

    @BeforeEach
    public void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
        List<UserEntity> userEntityList = userJpaRepository.findAll();
        assertFalse(userEntityList.isEmpty());
    }

    @AfterEach
    void cleanData() {
        userJpaRepository.deleteAll();
    }

    @Test
    public void givenExistingUserId_whenGetUser_thenRetrieveUserDetails() {
        given()
                .when()
                .get("/api/users/{userId}", 1)
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("userId", equalTo(1))
                .body("name", equalTo("John"))
                .body("surname", equalTo("Doe"));
    }

    // Add more test cases for other API operations

}

