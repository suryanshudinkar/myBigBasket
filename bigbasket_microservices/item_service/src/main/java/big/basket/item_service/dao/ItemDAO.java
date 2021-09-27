package big.basket.item_service.dao;

import big.basket.item_service.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ItemDAO extends JpaRepository<Item, Integer> {
}
