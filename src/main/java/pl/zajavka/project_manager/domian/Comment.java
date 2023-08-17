package pl.zajavka.project_manager.domian;

import lombok.*;

@With
@Value
@Builder
@EqualsAndHashCode(of = "commentId")
@ToString(of = {"date", "comment"})
public class Comment {

    Integer commentId;
    String comment;
    Integer date;
    User author;
}
