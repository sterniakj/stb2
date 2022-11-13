package pl.akademiaqa.requests.task;

import io.restassured.response.Response;
import org.json.JSONObject;
import pl.akademiaqa.dto.request.CreateTaskRequestDto;
import pl.akademiaqa.dto.response.CreateTaskCreatorResponseDto;
import pl.akademiaqa.dto.response.CreateTaskResponseDto;
import pl.akademiaqa.requests.BaseRequest;
import pl.akademiaqa.url.ClickUpUrl;

import static io.restassured.RestAssured.given;

public class CreateTaskRequest {
    public static Response createTask(JSONObject task, String listId) {
        return given()
                .when()
                .spec(BaseRequest.requestSpecWithLogs())
                .body(task.toString())
                .post(ClickUpUrl.getTasksUrl(listId))
                .then()
                .log().ifError()
                .extract()
                .response();

    }
//serializacja - zamiast JSON przekazujemy obiekt JAVA
    //deserializacja - zamiana JSONA na obiekt JAVowy tu status code musimy sprawdzic wczesniej
// bo nie bedziemy juz mieli klasy response tylko ta co utworzylismy i tam nie ma tych opcji
    public static CreateTaskResponseDto createTaskDto(CreateTaskRequestDto taskDto, String listId) {
        return given()
                .when()
                .spec(BaseRequest.requestSpecWithLogs())
                .body(taskDto)
                .post(ClickUpUrl.getTasksUrl(listId))
                .then()
                .statusCode(200)
                .log().ifError()
                .extract()
                .response()
                .as(CreateTaskResponseDto.class);

    }
}
