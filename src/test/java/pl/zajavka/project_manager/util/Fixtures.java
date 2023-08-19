package pl.zajavka.project_manager.util;

import lombok.experimental.UtilityClass;
import pl.zajavka.project_manager.infrastructure.database.entity.CommentEntity;
import pl.zajavka.project_manager.infrastructure.database.entity.ProjectEntity;
import pl.zajavka.project_manager.infrastructure.database.entity.UserEntity;

import java.util.List;

@UtilityClass
public class Fixtures {

    public static UserEntity someUser1() {
        return UserEntity.builder()
                .name("John")
                .surname("Doe")
                .password("password123")
                .age(30)
                .gender("Male")
                .email("john.doe@example.com")
                .phone("123-456-789")
                .build();
    }

    public static UserEntity someUser2() {
        return UserEntity.builder()
                .name("Alice")
                .surname("Smith")
                .password("alicePass")
                .age(28)
                .gender("Female")
                .email("alice.smith@example.com")
                .phone("987-654-321")
                .build();
    }

    public static UserEntity someUser3() {
        return UserEntity.builder()
                .name("Bob")
                .surname("Johnson")
                .password("bob123")
                .age(35)
                .gender("Male")
                .email("bob.johnson@example.com")
                .phone("555-555-555")
                .build();
    }

    public static ProjectEntity someProject1() {
        return ProjectEntity.builder()
                .name("Project 1")
                .description("Description for Project 1")
//                .author(someUser1())
//                .users(List.of(someUser1(), someUser2()))
                .build();
    }

    public static ProjectEntity someProject2() {
        return ProjectEntity.builder()
                .name("Project 2")
                .description("Description for Project 2")
//                .author(someUser2())
//                .users(List.of(someUser2(), someUser3()))
                .build();
    }

    public static ProjectEntity someProject3() {
        return ProjectEntity.builder()
                .name("Project 3")
                .description("Description for Project 3")
//                .author(someUser1())
//                .users(List.of(someUser1(), someUser3()))
                .build();
    }

    public static CommentEntity someComment1() {
        return CommentEntity.builder()
                .comment("This is a comment.")
//                .author(someUser1())
//                .project(someProject1())
                .build();
    }

    public static CommentEntity someComment2() {
        return CommentEntity.builder()
                .comment("Another comment.")
//                .author(someUser2())
//                .project(someProject2())
                .build();
    }

    public static CommentEntity someComment3() {
        return CommentEntity.builder()
                .comment("Yet another comment.")
//                .author(someUser3())
//                .project(someProject3())
                .build();
    }
}
