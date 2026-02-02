package cat.itacademy.s04.t02.n03.fruit.orders.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record OrderItemRequestDTO(@NotBlank(message = "Fruit name must not be blank") String fruitName,
                                  @NotNull(message = "Quantity is required")
                                    @Positive(message = "Quantity must be greater than zero") Integer quantityInKilos
) {}
