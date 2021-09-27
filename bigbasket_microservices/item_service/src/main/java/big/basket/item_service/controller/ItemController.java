package big.basket.item_service.controller;

import big.basket.item_service.entity.Item;
import big.basket.item_service.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ItemController {
    @Autowired
    private ItemService itemService;

    @RequestMapping("/home")
    public String home(){
        return "<h1>This is the home page for item service</h1>";
    }
    
    @RequestMapping("/showCatalog")
    public List<Item> getItems(){
        return itemService.getItems();
    }

    @RequestMapping("/showCatalog/{id}")
    public Item getItem(@PathVariable int id) {
        return itemService.getItem(id);
    }

    @RequestMapping("/addItem")
    public String addStudent(@RequestParam int productID, @RequestParam String productName, @RequestParam int productPrice) {
        Item item = new Item(productID, productName, productPrice);
        return itemService.addItem(item);
    }

    @PostMapping("/addItems")
    public String addStudents(@RequestBody List<Item> items) {
        return itemService.addItems(items);
    }

    @DeleteMapping("/deleteItem/{id}")
    public String deleteStudent(@PathVariable int id) {
        return itemService.deleteItem(id);
    }

    @PutMapping("/updateItem")
    public String updateStudent(@RequestBody Item item) {
        return itemService.updateItem(item);
    }
}
