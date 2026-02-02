package cat.itacademy.s04.t02.n03.fruit.api;

import cat.itacademy.s04.t02.n03.fruit.orders.domain.Order;
import cat.itacademy.s04.t02.n03.fruit.orders.domain.OrderItem;
import cat.itacademy.s04.t02.n03.fruit.orders.dto.OrderItemRequestDTO;
import cat.itacademy.s04.t02.n03.fruit.orders.dto.OrderRequestDTO;
import cat.itacademy.s04.t02.n03.fruit.orders.persistence.OrderRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class OrderControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    OrderRepository orderRepository;

    @BeforeEach
    void setup() {
        orderRepository.deleteAll();
    }

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

    @Test
    void shouldReturnEmptyListWhenNoOrdersExist() throws Exception {
        mockMvc.perform(get("/orders"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    void shouldReturnAllOrders() throws Exception {
        Order order1 = new Order(null, "Cr", LocalDate.now().plusDays(1),
                List.of(new OrderItem("Apple", 3)));

        Order order2 = new Order(null, "Cbr", LocalDate.now().plusDays(2),
                List.of(new OrderItem("Banana", 5)));

        orderRepository.save(order1);
        orderRepository.save(order2);

        mockMvc.perform(get("/orders"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(2));
    }
}
