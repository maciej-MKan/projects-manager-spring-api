package pl.zajavka.project_manager.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDTO {

    private String name;
//    private String description;
    private Integer startDate;
    private Integer endDate;
    private UserDTO author;
}
