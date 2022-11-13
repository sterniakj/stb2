package pl.akademiaqa.requests.space;

import io.restassured.response.Response;
import org.json.JSONObject;
import pl.akademiaqa.dto.request.CreateTaskRequestDto;
import pl.akademiaqa.dto.request.UpdateSpaceRequestDto;
import pl.akademiaqa.dto.response.CreateTaskResponseDto;
import pl.akademiaqa.properties.ClickupProperties;
import pl.akademiaqa.requests.BaseRequest;
import pl.akademiaqa.url.ClickUpUrl;

import static io.restassured.RestAssured.given;

public class UpdateSpaceRequest {
    public static Response updateSpace(JSONObject space, String spaceId) {
        return given()
                .when()
                .spec(BaseRequest.requestSpecWithLogs())
                .body(space.toString())
                .put(ClickUpUrl.getSpaceUrl(spaceId))
                .then()
                .log().ifError()
                .extract()
                .response();

    }

    public static Response updateSpaceDto(UpdateSpaceRequestDto spaceDto, String spaceId) {
        return given()
                .when()
                .spec(BaseRequest.requestSpecWithLogs())
                .body(spaceDto)
                .put(ClickUpUrl.getSpaceUrl(spaceId))
                .then()
                .log().ifError()
                .extract()
                .response();
    }
}
