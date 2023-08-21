package pl.zajavka.project_manager.api.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@With
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailsDTO {

    private Integer userId;
    @NotEmpty(message = "Name is required.")
    @Size(min = 3, max = 30, message = "Name should be between 3 and 30 characters long.")
    private String name;
    @NotEmpty(message = "Surname is required.")
    @Size(min = 3, max = 30, message = "Surname should be between 3 and 30 characters long.")
    private String surname;
    @NotEmpty(message = "Password is required.")
    @Size(min = 3, max = 100, message = "Incorrect password")
    private String password;
    @NotNull(message = "Age is required.")
    @Min(value = 18, message = "Age should be at least 18.")
    @Max(value = 100, message = "Age cannot exceed 100.")
    private Integer age;
    private String gender;
    @NotEmpty(message = "Email is required.")
    @Email(message = "Invalid email format.")
    private String email;
    @Pattern(regexp = "^(\\+\\d{2,3}(\\s)?)?\\d{9}$", message = "Invalid phone number format.")
    private String phone;
    private Boolean superUser;
}
