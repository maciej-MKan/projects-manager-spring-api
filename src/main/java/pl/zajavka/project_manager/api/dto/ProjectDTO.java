package pl.zajavka.project_manager.api.dto;

import lombok.*;

@Data
@With
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDTO {

    private Integer projectId;
    private String name;
    private Integer startDate;
    private Integer endDate;
    private UserDTO author;
}
