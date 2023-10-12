package pl.zajavka.project_manager.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Data
@With
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    @JsonProperty("user_id")
    private Integer userId;
    @JsonProperty("first_name")
    private String name;
    @JsonProperty("last_name")
    private String surname;
}
