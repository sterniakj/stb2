package pl.akademiaqa.tests.space;

import org.assertj.core.api.Assertions;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import pl.akademiaqa.dto.request.UpdateSpaceRequestDto;
import pl.akademiaqa.requests.space.CreateSpaceRequest;
import pl.akademiaqa.requests.space.DeleteSpaceRequest;
import pl.akademiaqa.requests.space.UpdateSpaceRequest;


class CreateSpaceTest {
    private static final String SPACE_NAME = "Test space from JAVA";
    private static final String UPDATED_SPACE_NAME = "New space name";


    @Test
    void createSpace() {
        JSONObject space = new JSONObject();
        space.put("name", SPACE_NAME);

        final var response = CreateSpaceRequest.createSpace(space);
        Assertions.assertThat(response.statusCode()).isEqualTo(200);
        Assertions.assertThat(response.jsonPath().getString("name")).isEqualTo(SPACE_NAME);
        final var spaceId = response.jsonPath().getString("id");

//        JSONObject updatedSpace = new JSONObject();
//        updatedSpace.put("name", UPDATED_SPACE_NAME);
//
//        final var updateResponse = UpdateSpaceRequest.updateSpace(updatedSpace,spaceId);
//        Assertions.assertThat(updateResponse.statusCode()).isEqualTo(200);
//        Assertions.assertThat(updateResponse.jsonPath().getString("name")).isEqualTo(UPDATED_SPACE_NAME);

        UpdateSpaceRequestDto updatedSpace = new UpdateSpaceRequestDto();
        updatedSpace.setName(UPDATED_SPACE_NAME);

        final var updateResponse = UpdateSpaceRequest.updateSpaceDto(updatedSpace, spaceId);
        Assertions.assertThat(updateResponse.statusCode()).isEqualTo(200);
        Assertions.assertThat(updateResponse.jsonPath().getString("name")).isEqualTo(UPDATED_SPACE_NAME);


        final var deleteResponse = DeleteSpaceRequest.deleteSpace(spaceId);
        Assertions.assertThat(deleteResponse.statusCode()).isEqualTo(200);


    }
}
