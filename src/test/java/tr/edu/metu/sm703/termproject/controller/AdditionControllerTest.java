package tr.edu.metu.sm703.termproject.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
public class AdditionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void additionControllerReturnsCorrectSum() throws Exception {
        ResultActions resultActions = mockMvc.perform(get("/add").param("a", "5").param("b", "3"))
            .andExpect(status().isOk())
            .andExpect(content().string("7"));
    }
}

