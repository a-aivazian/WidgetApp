package com.example.WidgetApp;

import com.example.WidgetApp.model.CreateRequestBody;
import com.example.WidgetApp.model.UpdateRequestBody;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class WidgetIntegrationTests {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void restSuccess() throws Exception {
        CreateRequestBody requestBody = new CreateRequestBody(1, 2, 3, 4,5);

        mockMvc.perform(post("/widgets")
                        .content(asJsonString(requestBody))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value("1"))
                .andExpect(jsonPath("x").value("1"))
                .andExpect(jsonPath("y").value("2"))
                .andExpect(jsonPath("z").value("3"))
                .andExpect(jsonPath("width").value("4"))
                .andExpect(jsonPath("height").value("5"));

        mockMvc.perform(get("/widgets/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value("1"))
                .andExpect(jsonPath("x").value("1"))
                .andExpect(jsonPath("y").value("2"))
                .andExpect(jsonPath("z").value("3"))
                .andExpect(jsonPath("width").value("4"))
                .andExpect(jsonPath("height").value("5"));

        mockMvc.perform(put("/widgets")
                        .content(asJsonString(new UpdateRequestBody(1, 13, 14, 15, 16, 17)))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value("1"))
                .andExpect(jsonPath("x").value("13"))
                .andExpect(jsonPath("y").value("14"))
                .andExpect(jsonPath("z").value("15"))
                .andExpect(jsonPath("width").value("16"))
                .andExpect(jsonPath("height").value("17"));

        mockMvc.perform(delete("/widgets/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void createWithEmptyFieldsShouldFail() throws Exception {
        mockMvc.perform(post("/widgets")
                        .content(asJsonString(new CreateRequestBody()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void updateWithEmptyFieldsShouldFail() throws Exception {
        mockMvc.perform(put("/widgets")
                        .content(asJsonString(new UpdateRequestBody()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
