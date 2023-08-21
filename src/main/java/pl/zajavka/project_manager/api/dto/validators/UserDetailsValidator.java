package pl.zajavka.project_manager.api.dto.validators;

import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import pl.zajavka.project_manager.api.dto.UserDetailsDTO;

@Component
public class UserDetailsValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return UserDetailsDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(@NotNull Object target, Errors errors) {
        UserDetailsDTO userDetails = (UserDetailsDTO) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "field.required", "Name is required.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "surname", "field.required", "Surname is required.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "field.required", "Password is required.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "age", "field.required", "Age is required.");

        if (!userDetails.getEmail().matches("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$")) {
            errors.rejectValue("email", "email.invalid", "Invalid email format.");
        }

        if (!userDetails.getName().matches("^[a-zA-Z]{3,30}$")) {
            errors.rejectValue("name", "name.invalid", "Name should contain only letters and be between 3 and 30 characters long.");
        }

        if (!userDetails.getSurname().matches("^[a-zA-Z]{3,30}$")) {
            errors.rejectValue("surname", "surname.invalid", "Surname should contain only letters and be between 3 and 30 characters long.");
        }

        if (userDetails.getAge() != null && (userDetails.getAge() < 18 || userDetails.getAge() > 100)) {
            errors.rejectValue("age", "age.invalid", "Age should be a number between 18 and 100.");
        }
    }
}
