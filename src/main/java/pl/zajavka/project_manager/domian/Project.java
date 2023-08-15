package pl.zajavka.project_manager.domian;

import lombok.*;

import java.util.List;

@With
@Value
@Builder
@EqualsAndHashCode(of = "projectId")
@ToString(of = {"title", "author"})
public class Project {

    String projectId;
    String title;
    String description;
    Integer startDate;
    Integer endDate;
    User author;
    List<User> users;

}
