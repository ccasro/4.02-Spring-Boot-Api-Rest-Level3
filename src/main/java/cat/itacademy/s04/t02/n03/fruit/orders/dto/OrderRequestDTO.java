package cat.itacademy.s04.t02.n03.fruit.orders.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public record OrderRequestDTO(
        @NotBlank(message = "Client name must not be blank") String clientName,
        @NotNull(message = "Delivery date is required") LocalDate deliveryDate,
        @NotEmpty(message = "At least one item is required") List<@Valid OrderItemRequestDTO> items
) {
}
