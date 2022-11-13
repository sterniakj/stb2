package pl.akademiaqa.requests.space;

import io.restassured.response.Response;
import pl.akademiaqa.properties.ClickupProperties;
import pl.akademiaqa.requests.BaseRequest;
import pl.akademiaqa.url.ClickUpUrl;

import static io.restassured.RestAssured.given;

public class DeleteSpaceRequest {
    public static Response deleteSpace(String spaceId){
        return given()
                .when()
                .spec(BaseRequest.requestSpecWithLogs())
                .delete(ClickUpUrl.getSpaceUrl(spaceId))
                .then()
                .log().ifError()
                .extract()
                .response();
    }
}
