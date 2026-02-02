package cat.itacademy.s04.t02.n03.fruit.orders.api;

import cat.itacademy.s04.t02.n03.fruit.orders.application.OrderService;
import cat.itacademy.s04.t02.n03.fruit.orders.dto.OrderRequestDTO;
import cat.itacademy.s04.t02.n03.fruit.orders.dto.OrderResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService service;

    @PostMapping
    public ResponseEntity<OrderResponseDTO> createOrder(@Valid @RequestBody OrderRequestDTO request) {
        OrderResponseDTO response = service.createOrder(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<OrderResponseDTO>> getAllOrders() {
        List<OrderResponseDTO> response = service.getAllOrders();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDTO> getOrderById(@PathVariable String id) {
        OrderResponseDTO response = service.getOrderById(id);
        return ResponseEntity.ok(response);
    }
}
