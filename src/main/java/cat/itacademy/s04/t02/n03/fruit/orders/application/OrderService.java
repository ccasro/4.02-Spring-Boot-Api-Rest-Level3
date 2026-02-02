package cat.itacademy.s04.t02.n03.fruit.orders.application;

import cat.itacademy.s04.t02.n03.fruit.orders.dto.OrderRequestDTO;
import cat.itacademy.s04.t02.n03.fruit.orders.dto.OrderResponseDTO;
import cat.itacademy.s04.t02.n03.fruit.orders.mapper.OrderMapper;
import cat.itacademy.s04.t02.n03.fruit.orders.persistence.OrderRepository;
import cat.itacademy.s04.t02.n03.fruit.shared.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository repository;

    public OrderResponseDTO createOrder(OrderRequestDTO dto){
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        if (dto.deliveryDate().isBefore(tomorrow)){
            throw new BadRequestException("Delivery date must be at least tomorrow");
        }

        var entity = OrderMapper.toEntity(dto);
        var saved = repository.save(entity);
        return OrderMapper.toResponseDTO(saved);
    }

    public List<OrderResponseDTO> getAllOrders(){
        return repository.findAll().stream()
                .map(OrderMapper::toResponseDTO).toList();
    }
}
