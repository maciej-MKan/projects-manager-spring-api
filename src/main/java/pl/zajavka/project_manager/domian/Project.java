package pl.zajavka.project_manager.domian;

import lombok.*;

import java.util.List;

@With
@Value
@Builder
@EqualsAndHashCode(of = "projectId")
@ToString(of = {"name", "author"})
public class Project {

    Integer projectId;
    String name;
    String description;
    Integer startDate;
    Integer endDate;
    User author;
    List<User> users;

}
