package pl.zajavka.project_manager.api.dto;

import lombok.*;

@Data
@With
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Integer userId;
    private String name;
    private String surname;
}
