package cat.itacademy.s04.t02.n03.fruit.api;

import cat.itacademy.s04.t02.n03.fruit.orders.dto.OrderItemRequestDTO;
import cat.itacademy.s04.t02.n03.fruit.orders.dto.OrderRequestDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class OrderControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    @Test
    void shouldCreateOrderWhenDataIsValid() throws Exception {
        OrderRequestDTO request = new OrderRequestDTO(
                "Ccr",
                LocalDate.now().plusDays(1),
                List.of(new OrderItemRequestDTO("Banana", 3))
        );

        mockMvc.perform(post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isString())
                .andExpect(jsonPath("$.clientName").value("Ccr"))
                .andExpect(jsonPath("$.items[0].fruitName").value("Banana"))
                .andExpect(jsonPath("$.items[0].quantityInKilos").value(3));
    }

    @Test
    void shouldReturnBadRequestWhenDeliveryDateIsBeforeTomorrow() throws Exception {
        OrderRequestDTO request = new OrderRequestDTO(
                "ccr",
                LocalDate.now(),
                List.of(new OrderItemRequestDTO("Banana", 3))
        );

        mockMvc.perform(post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnBadRequestWhenItemsAreEmpty() throws Exception {
        OrderRequestDTO request = new OrderRequestDTO(
                "ccr",
                LocalDate.now().plusDays(1),
                List.of()
        );

        mockMvc.perform(post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnBadRequestWhenItemQuantityIsNotPositive() throws Exception {
        OrderRequestDTO request = new OrderRequestDTO(
                "ccr",
                LocalDate.now().plusDays(1),
                List.of(new OrderItemRequestDTO("Banana", 0))
        );

        mockMvc.perform(post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }
}
