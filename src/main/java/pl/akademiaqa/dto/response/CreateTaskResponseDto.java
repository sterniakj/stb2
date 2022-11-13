package pl.akademiaqa.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true) // to powoduje ze jak zamienia z JSONa na obiekt to
// zamieni tylko pola potrzebne w obiekcie a reszte pominie to jest ten jackson w dependency
public class CreateTaskResponseDto {
    private String id;
    private String name;
    private String description;
    private CreateTaskStatusResponseDto status;
    private CreateTaskCreatorResponseDto creator;

}
