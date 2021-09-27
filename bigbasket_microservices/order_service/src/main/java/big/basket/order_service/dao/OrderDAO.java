package big.basket.order_service.dao;

import big.basket.order_service.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderDAO extends JpaRepository<Order, Integer> {
    @Query(value = "SELECT * FROM order_data o WHERE o.uID = ?1", nativeQuery = true)
    public List<Order> getUserOrders(int userID);
}
