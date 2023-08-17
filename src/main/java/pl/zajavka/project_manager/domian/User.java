package pl.zajavka.project_manager.domian;

import lombok.*;
import pl.zajavka.project_manager.infrastructure.database.entity.ProjectEntity;

import java.util.List;
@With
@Value
@Builder
@EqualsAndHashCode(of = "userId")
@ToString(of = {"name", "surname"})
public class User {
    Integer userId;
    String name;
    String surname;
    Integer age;
    String gender;
    String email;
    String phone;
    List<ProjectEntity> projects;
}
