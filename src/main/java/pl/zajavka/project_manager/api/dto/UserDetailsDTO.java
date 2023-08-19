package pl.zajavka.project_manager.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailsDTO {

    private Integer userId;
    private String name;
    private String surname;
    private String password;
    private Integer age;
    private String gender;
    private String email;
    private String phone;
}
