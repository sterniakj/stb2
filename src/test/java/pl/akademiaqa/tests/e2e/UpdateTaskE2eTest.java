package pl.akademiaqa.tests.e2e;

import org.assertj.core.api.Assertions;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.akademiaqa.dto.request.CreateTaskRequestDto;
import pl.akademiaqa.requests.list.CreateListRequest;
import pl.akademiaqa.requests.space.CreateSpaceRequest;
import pl.akademiaqa.requests.space.DeleteSpaceRequest;
import pl.akademiaqa.requests.task.CreateTaskRequest;
import pl.akademiaqa.requests.task.UpdateTaskRequest;

class UpdateTaskE2eTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateTaskE2eTest.class);
    public static String spaceName = "Space e2e";
    public static String listName = "Zadania";
    public static String taskName = "Task testowy";
    private String spaceId;
    private String listId;
    private String taskId;


    @Test
    void updateTaskE2ETest() {
        spaceId = createSpaceStep();
        LOGGER.info("New space created with id {}", spaceId);
        listId = createListStep();
        LOGGER.info("New list created with id {}", listId);
        taskId = createTaskStep();
        LOGGER.info("New task created with id {}", listId);
        updateTaskStep();
        closeTaskStep();
        deleteSpaceStep();
    }

    private String createSpaceStep() {

        JSONObject space = new JSONObject();
        space.put("name", spaceName);

        final var response = CreateSpaceRequest.createSpace(space);
        Assertions.assertThat(response.statusCode()).isEqualTo(200);
        Assertions.assertThat(response.jsonPath().getString("name")).isEqualTo(spaceName);
        return response.jsonPath().getString("id");
    }

    private String createListStep() {

        JSONObject list = new JSONObject();
        list.put("name", listName);

        final var response = CreateListRequest.createList(list, spaceId);
        Assertions.assertThat(response.statusCode()).isEqualTo(200);
        Assertions.assertThat(response.jsonPath().getString("name")).isEqualTo(listName);
        return response.jsonPath().getString("id");
    }

    private String createTaskStep() {

//        JSONObject task = new JSONObject();
//        task.put("name", taskName);
//        task.put("description", "Ciekawe jak to dziala");
//        task.put("status", "to do");
//        task.put("priority", JSONObject.NULL);
//        task.put("parent", JSONObject.NULL);
//        task.put("time_estimate", JSONObject.NULL);
//        task.put("assignees", JSONObject.NULL);
//        task.put("archived", false);

//        final var response = CreateTaskRequest.createTask(task,listId);

        CreateTaskRequestDto taskDto = new CreateTaskRequestDto();
        taskDto.setName(taskName);
        taskDto.setDescription("Ciekawe jak to działa");
        taskDto.setStatus("to do");
        taskDto.setArchived(false);

        final var response = CreateTaskRequest.createTaskDto(taskDto, listId);
        LOGGER.info("Create task obj {}", response);
//        Assertions.assertThat(response.statusCode()).isEqualTo(200);
//        Assertions.assertThat(response.jsonPath().getString("name")).isEqualTo(taskName);
//        return response.jsonPath().getString("id");

        Assertions.assertThat(response.getName()).isEqualTo(taskName);
        Assertions.assertThat(response.getDescription()).isEqualTo("Ciekawe jak to działa");

        return response.getId();
    }

    private void updateTaskStep() {
        JSONObject task = new JSONObject();
        task.put("name", "Zmieniam nazwe");
        task.put("description", "Zmieniam description");

        final var response = UpdateTaskRequest.updateTask(task, taskId);
        Assertions.assertThat(response.statusCode()).isEqualTo(200);
        Assertions.assertThat(response.jsonPath().getString("name")).isEqualTo("Zmieniam nazwe");
        Assertions.assertThat(response.jsonPath().getString("description")).isEqualTo("Zmieniam description");

    }

    private void closeTaskStep() {
        JSONObject task = new JSONObject();
        task.put("status", "complete");
        final var closeTask = UpdateTaskRequest.updateTask(task, taskId);
        Assertions.assertThat(closeTask.statusCode()).isEqualTo(200);
        Assertions.assertThat(closeTask.jsonPath().getString("status.status")).isEqualTo("complete");

    }


    private void deleteSpaceStep(){
        final var deleteResponse = DeleteSpaceRequest.deleteSpace(spaceId);
        Assertions.assertThat(deleteResponse.statusCode()).isEqualTo(200);

    }
}
