package pl.zajavka.project_manager.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor(staticName = "of")
public class ErrorMessage {
    int status;
    String message;
}
