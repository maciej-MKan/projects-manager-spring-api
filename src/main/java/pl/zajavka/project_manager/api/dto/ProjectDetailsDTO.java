package pl.zajavka.project_manager.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDetailsDTO {

    private Integer projectId;
    private String name;
    private String description;
    private Integer startDate;
    private Integer endDate;
    private UserDTO author;
    private List<UserDTO> users;
    private List<CommentDTO> comments;
}
