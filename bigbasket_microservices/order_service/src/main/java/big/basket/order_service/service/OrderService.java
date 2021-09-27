package big.basket.order_service.service;

import big.basket.order_service.dao.OrderDAO;
import big.basket.order_service.entity.Order;
import big.basket.order_service.pojo.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Bean
    public Order order(){
        return new Order();
    }
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private OrderDAO orderDAO;

    private static String ITEM_SERVICE_URL = "http://localhost:8082/";

    public List<Order> getAllOrders(){
        List<Order> orders = orderDAO.findAll();
        return orders;
    }

    public List<Item> getItemsforOrder(int orderID){
        String productlist = getOrder(orderID).getProductlist();
        int n=productlist.length();
        List<Integer> idlist=new ArrayList<Integer>();
        String s="";
        for(int i=0;i<n;i++){
            char ch=productlist.charAt(i);
            if(ch==','){
                int x=Integer.parseInt(s);
                idlist.add(x);
                s="";
            }
            else{
                s+=ch;
            }
        }
        idlist.add(Integer.parseInt(s));
        System.out.println(idlist.size());
        List<Item> ItemList = new ArrayList<Item>();
        n=idlist.size();
        for(int i=0;i<n;i++){

            Item item = restTemplate.getForObject(ITEM_SERVICE_URL + "showCatalog/" + idlist.get(i),Item.class);
            ItemList.add(item);
        }
        return ItemList;
    }
    public Order getOrder(int orderID){
        Order order=orderDAO.getById(orderID);
        return order;
    }
    public List<Order> getUserOrders(int userID){
        List<Order> orders =orderDAO.getUserOrders(userID);
        return orders;
    }
    public String deleteItemfromOrder(int orderID,int productID){
        String productlist = getOrder(orderID).getProductlist();
        int n=productlist.length();
        List<Integer> idlist=new ArrayList<Integer>();
        String s="";
        for(int i=0;i<n;i++){
            char ch=productlist.charAt(i);
            if(ch==','){
                int x=Integer.parseInt(s);
                idlist.add(x);
                s="";
            }
            else{
                s+=ch;
            }
        }
        idlist.add(Integer.parseInt(s));
        int idx=idlist.indexOf(productID);
        idlist.remove(idx);

        String editedList="";
        n=idlist.size();
        for(int i=0;i<n-1;i++){
            editedList+=(idlist.get(i).toString());
            editedList+=',';
        }
        editedList+=(idlist.get(n-1).toString());
        Order o = getOrder(orderID);
        o.setProductlist(editedList);
        orderDAO.save(o);
        return editedList;
    }
    public String addItemtoOrder(int orderID,int productID){
        String productlist = getOrder(orderID).getProductlist();
        int n=productlist.length();
        List<Integer> idlist=new ArrayList<Integer>();
        String s="";
        for(int i=0;i<n;i++){
            char ch=productlist.charAt(i);
            if(ch==','){
                int x=Integer.parseInt(s);
                idlist.add(x);
                s="";
            }
            else{
                s+=ch;
            }
        }
        idlist.add(Integer.parseInt(s));
        idlist.add(productID);
        String editedList="";
        n=idlist.size();
        for(int i=0;i<n-1;i++){
            editedList+=(idlist.get(i).toString());
            editedList+=',';
        }
        editedList+=(idlist.get(n-1).toString());
        Order o = getOrder(orderID);
        o.setProductlist(editedList);
        orderDAO.save(o);
        return editedList;
    }
    public Order addOrder(Order order){
        orderDAO.save(order);
        return order;
    }
    public int totalBill(int orderID){
        int sum=0;
        List<Item> itemList = getItemsforOrder(orderID);
        int n=itemList.size();
        for(int i=0;i<n;i++){
            sum+=(itemList.get(i).getProductPrice());
        }
        return sum;
    }
}
