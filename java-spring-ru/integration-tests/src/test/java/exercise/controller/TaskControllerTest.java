package exercise.controller;

import org.junit.jupiter.api.Test;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static org.assertj.core.api.Assertions.assertThat;

import org.instancio.Instancio;
import org.instancio.Select;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import java.util.HashMap;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.datafaker.Faker;
import exercise.repository.TaskRepository;
import exercise.model.Task;
import org.springframework.transaction.annotation.Transactional;

// BEGIN
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
        // END
class ApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Faker faker;

    @Autowired
    private ObjectMapper om;

    @Autowired
    private TaskRepository taskRepository;


    @Test
    public void testWelcomePage() throws Exception {
        var result = mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andReturn();

        var body = result.getResponse()
                .getContentAsString();
        assertThat(body).contains("Welcome to Spring!");
    }

    @Test
    public void testIndex() throws Exception {
        var result = mockMvc.perform(get("/tasks"))
                .andExpect(status().isOk())
                .andReturn();

        var body = result.getResponse()
                .getContentAsString();
        assertThatJson(body).isArray();
    }


    // BEGIN
    @Test
    public void testShow() throws Exception {
        mockMvc.perform(get("/tasks/1"))
                .andExpect(status().is(404));

        var task = Instancio.of(Task.class)
                .ignore(Select.field(Task::getId))
                .supply(Select.field(Task::getTitle), () -> faker.finalFantasyXIV()
                        .character())
                .supply(Select.field(Task::getDescription), () -> faker.darkSouls()
                        .covenants())
                .create();

        taskRepository.save(task);

        var result = mockMvc.perform(get("/tasks/" + task.getId()))
                .andExpect(status().isOk())
                .andReturn();

        var body = result.getResponse()
                .getContentAsString();
        var responseTask = om.readValue(body, Task.class);

        assertThat(responseTask.getTitle()).isEqualTo(task.getTitle());
        assertThat(responseTask.getDescription()).isEqualTo(task.getDescription());
    }

    @Test
    public void testCreate() throws Exception {
        var task = Instancio.of(Task.class)
                .ignore(Select.field(Task::getId))
                .supply(Select.field(Task::getTitle), () -> faker.finalFantasyXIV()
                        .character())
                .supply(Select.field(Task::getDescription), () -> faker.darkSouls()
                        .covenants())
                .create();

        var data = new HashMap<>();
        data.put("title", task.getTitle());
        data.put("description", task.getDescription());

        var request = post("/tasks").contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(data));

        var result = mockMvc.perform(request)
                .andExpect(status().isCreated())
                .andReturn();

        var body = result.getResponse()
                .getContentAsString();
        var responseTask = om.readValue(body, Task.class);

        assertThat(responseTask.getTitle()).isEqualTo(task.getTitle());
        assertThat(responseTask.getDescription()).isEqualTo(task.getDescription());
    }


    @Test
    public void testUpdate() throws Exception {
        var task = Instancio.of(Task.class)
                .ignore(Select.field(Task::getId))
                .supply(Select.field(Task::getTitle), () -> faker.finalFantasyXIV()
                        .character())
                .supply(Select.field(Task::getDescription), () -> faker.darkSouls()
                        .covenants())
                .create();

        taskRepository.save(task);

        var updatedData = new HashMap<>();
        updatedData.put("title", "Updated Title");
        updatedData.put("description", "Updated Description");

        var request = put("/tasks/" + task.getId()).contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(updatedData));

        var result = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andReturn();

        var body = result.getResponse()
                .getContentAsString();
        var responseTask = om.readValue(body, Task.class);

        assertThat(responseTask.getTitle()).isEqualTo("Updated Title");
        assertThat(responseTask.getDescription()).isEqualTo("Updated Description");
    }

    @Test
    public void testDelete() throws Exception {
        var task = Instancio.of(Task.class)
                .ignore(Select.field(Task::getId))
                .supply(Select.field(Task::getTitle), () -> faker.finalFantasyXIV()
                        .character())
                .supply(Select.field(Task::getDescription), () -> faker.darkSouls()
                        .covenants())
                .create();

        taskRepository.save(task);

        var request = delete("/tasks/" + task.getId());

        mockMvc.perform(request)
                .andExpect(status().isOk());

        var deletedTask = taskRepository.findById(task.getId());
        assertThat(deletedTask).isEmpty();
    }
    // END
}
