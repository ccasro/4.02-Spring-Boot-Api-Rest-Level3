package cat.itacademy.s04.t02.n03.fruit.orders.persistence;

import cat.itacademy.s04.t02.n03.fruit.orders.domain.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order, String> {

}
