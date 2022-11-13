package pl.akademiaqa.dto.request;

import lombok.Data;

@Data // lombok - ta adnotacja dodaje gettery settery konstruktor to string
public class CreateTaskRequestDto {
    private String name;
    private String description;
    private String status;
    private String priority;
    private String parent;
    private String time_estimate;
    private String assignees;
    private boolean archived;


}
