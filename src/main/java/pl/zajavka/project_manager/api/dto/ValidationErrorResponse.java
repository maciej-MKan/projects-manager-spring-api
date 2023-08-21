package pl.zajavka.project_manager.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
@AllArgsConstructor(staticName = "of")
public class ValidationErrorResponse {
    int status;
    String message;
    List<String> errors;
}
