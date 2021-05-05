package es.ysegura.tutoriales.githubactions.rest;

import es.ysegura.tutoriales.githubactions.domain.services.Calculator;
import es.ysegura.tutoriales.githubactions.domain.vo.Result;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class CalculatorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private Calculator calculatorMock;

    @Test
    @DisplayName("POST /add success")
    void testAdd() throws Exception {
        when(calculatorMock.add(1, 3)).thenReturn(new Result(4));

        mockMvc.perform(post("/add").content("{\"a\":1,\"b\":3}").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.result", is(4)));
    }

    @Test
    @DisplayName("POST /subtract success")
    void testSubtract() throws Exception {
        when(calculatorMock.subtract(1, 3)).thenReturn(new Result(-2));

        mockMvc.perform(post("/subtract").content("{\"a\":1,\"b\":3}").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.result", is(-2)));
    }

    @Test
    @DisplayName("POST /multiply success")
    void testMultiply() throws Exception {
        when(calculatorMock.multiply(anyInt(), anyInt())).thenCallRealMethod();

        mockMvc.perform(post("/multiply").content("{\"a\":1,\"b\":3}").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.result", is(3)));
    }

    @Test
    @DisplayName("POST /divide success")
    void testDivideOk() throws Exception {
        when(calculatorMock.divide(9, 3)).thenReturn(new Result(3));

        mockMvc.perform(post("/divide").content("{\"a\":9,\"b\":3}").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.result", is(3)));
    }

    @Test
    @DisplayName("POST /divide error by zero not supported")
    void testDivideByZero() throws Exception {
        when(calculatorMock.divide(anyInt(), anyInt())).thenCallRealMethod();

        mockMvc.perform(post("/divide").content("{\"a\":1,\"b\":0}").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }


}