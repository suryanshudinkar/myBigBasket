package big.basket.item_service.service;

import big.basket.item_service.dao.ItemDAO;
import big.basket.item_service.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    
    @Autowired
    private ItemDAO itemDAO;

    public Item getItem(int productID){
        return itemDAO.findById(productID).get();
    }
    public List<Item> getItems(){
        return itemDAO.findAll();
    }
    public String addItem (Item item){
        itemDAO.save(item);
        return "Item successfully added with productID" + item.getProductID();
    }
    public String addItems (List<Item> itemList){
        itemDAO.saveAll(itemList);
        return itemList.size() + " items added to the catalog";
    }
    public String deleteItem(int productID) {
        Item item = itemDAO.getById(productID);
        itemDAO.delete(item);
        return "Item is deleted having product ID :" + item.getProductID();
    }

    public String updateItem(Item item) {
        itemDAO.save(item);
        return "Product with following ID is updated :"+item.getProductID();
    }
}
