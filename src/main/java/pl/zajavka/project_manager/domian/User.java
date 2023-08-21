package pl.zajavka.project_manager.domian;

import lombok.*;
import pl.zajavka.project_manager.infrastructure.database.entity.ProjectEntity;

import java.util.List;
@With
@Value
@Builder
@EqualsAndHashCode(of = "email")
@ToString(of = {"name", "surname"})
public class User {
    Integer userId;
    String name;
    String surname;
    String password;
    Boolean superUser;
    Integer age;
    String gender;
    String email;
    String phone;
    List<ProjectEntity> projects;
}
