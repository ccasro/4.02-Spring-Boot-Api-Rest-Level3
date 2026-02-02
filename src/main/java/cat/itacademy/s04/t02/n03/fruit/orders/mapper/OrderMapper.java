package cat.itacademy.s04.t02.n03.fruit.orders.mapper;

import cat.itacademy.s04.t02.n03.fruit.orders.domain.Order;
import cat.itacademy.s04.t02.n03.fruit.orders.domain.OrderItem;
import cat.itacademy.s04.t02.n03.fruit.orders.dto.OrderItemResponseDTO;
import cat.itacademy.s04.t02.n03.fruit.orders.dto.OrderRequestDTO;
import cat.itacademy.s04.t02.n03.fruit.orders.dto.OrderResponseDTO;

import java.util.List;

public final class OrderMapper {

    private OrderMapper() {}

    public static Order toEntity(OrderRequestDTO dto) {
        List<OrderItem> items = dto.items().stream().map(i-> new OrderItem(i.fruitName(), i.quantityInKilos())).toList();

        Order order = new Order();
        order.setClientName(dto.clientName());
        order.setDeliveryDate(dto.deliveryDate());
        order.setItems(items);

        return order;
    }

    public static OrderResponseDTO toResponseDTO(Order order){
        List<OrderItemResponseDTO> items = order.getItems().stream().map(i -> new OrderItemResponseDTO(i.getFruitName(), i.getQuantityInKilos()))
                .toList();

        return new OrderResponseDTO(order.getId(), order.getClientName(), order.getDeliveryDate(), items);
    }
}
