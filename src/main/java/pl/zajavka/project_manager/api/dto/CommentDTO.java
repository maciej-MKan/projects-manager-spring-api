package pl.zajavka.project_manager.api.dto;

import lombok.*;

@Data
@With
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {

    private Integer commentId;
    private String comment;
    private Integer date;
    private ProjectDTO project;
    private UserDTO author;

}
