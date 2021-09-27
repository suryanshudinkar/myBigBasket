package big.basket.order_service.controller;

import big.basket.order_service.entity.Order;
import big.basket.order_service.pojo.Item;
import big.basket.order_service.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class OrderController {


    @Autowired
    OrderService orderService;

    @GetMapping("/showOrder/{orderid}")
    public List<Item> show(@PathVariable int orderid){
        List<Item> itemList=orderService.getItemsforOrder(orderid);
        return itemList;
    }
    //query error
    @GetMapping("/showOrders/{userid}")
    public List<Order> showall(@PathVariable int userid){
        List<Order> orderList = orderService.getUserOrders(userid);
        return orderList;
    }

    @RequestMapping("/addOrder")
    public String addorder(@RequestParam int uid, @RequestParam String order){
        Order ord = new Order(uid, order);
        orderService.addOrder(ord);
        return "YOUR ORDER HAS BEEN PLACED";
    }
    
    @PutMapping("/deleteItemFromOrder/{orderID}/{productID}")
    public String deleteitemorder(@PathVariable int orderID,@PathVariable int productID){
        orderService.deleteItemfromOrder(orderID,productID);
        return "item successfully deleted from order";
    }
    @PutMapping("/addItemToOrder/{orderID}/{productID}")
    public String additemorder(@PathVariable int orderID,@PathVariable int productID){
        orderService.addItemtoOrder(orderID,productID);
        return "item succesfully added to item";
    }
    @GetMapping("/totalBill/{orderID}")
    public int totalbill(@PathVariable int orderID){
        int bill = orderService.totalBill(orderID);
        return bill;
    }

    @RequestMapping("/getAll")
    public List<Order> allOrders() {
        List<Order> orders = orderService.getAllOrders();
        return orders;
    }
}
