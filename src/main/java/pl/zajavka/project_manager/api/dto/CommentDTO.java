package pl.zajavka.project_manager.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
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
