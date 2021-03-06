package big.basket.controller;

import java.io.IOException;
import java.net.http.HttpHeaders;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import big.basket.config.entity.User;
import big.basket.config.entity.Item;
import big.basket.config.entity.Order;


@Controller
public class HomeController {

	private int CURRENT_USER = 1; 
	private boolean ADMIN_STATUS = false;
	private boolean AUTHENTICATED = false;
	
	private String currList = "";
	
	private String userURL = "http://localhost:8085/User";
	private String orderURL = "http://localhost:8085/Order";
	private String itemURL = "http://localhost:8085/Item";
	
	RestTemplate template = new RestTemplate();
	@RequestMapping(value="/")
	public ModelAndView test(HttpServletResponse response) throws IOException{
		if (AUTHENTICATED) {
			return new ModelAndView("loginsuccess");
		}
		CURRENT_USER = 1;
		ADMIN_STATUS = false;
		AUTHENTICATED = false;
		return new ModelAndView("home");
	}
	
	@RequestMapping(value="/about")
	public String about() {
		return "about";
	}
	
	@RequestMapping(value="/contact")
	public String contact() {
		return "contact";
	}
	
	@RequestMapping(value="/home")
	public String home() {
		if (AUTHENTICATED) {
			return "loginsuccess";
		}
		return "login";
	}
	
	@RequestMapping(value = "/signup")
	public ModelAndView signup(HttpServletResponse response) throws IOException {
		return new ModelAndView("signup");
	}
	
	
	@PostMapping(value="/saveUser")
	public ModelAndView saveUser(@RequestParam String name, @RequestParam String username, @RequestParam String password) {
		
		String message = template.getForObject( userURL +"/add?name="+name+"&username="+username+"&password="+password, String.class);
		System.out.println(message);
		return new ModelAndView("signupsuccess");
	}
	
	@PostMapping(value="/save")
	public ModelAndView save(@RequestParam String name, @RequestParam String username, @RequestParam String password) {
		
		String message = template.getForObject( userURL + "/add?name="+name+"&username="+username+"&password="+password, String.class);
		System.out.println(message);
		return new ModelAndView("redirect:/allUsers");
	}
	
	@RequestMapping(value = "/login")
	public ModelAndView login(HttpServletResponse response) throws IOException {
		return new ModelAndView("login");
	}
	
	@RequestMapping(value="/changeStatus/{id}")
	public ModelAndView changeUserStatus(@PathVariable int id) {
		boolean currStatus = template.getForObject(userURL + "/getIdPermission?id="+id, boolean.class);
		if (currStatus) {
			template.getForObject(userURL + "/revokeAdmin/"+id, String.class);
		}
		else {
			template.getForObject(userURL + "/setAdmin/"+id, String.class);
		}
		ADMIN_STATUS = template.getForObject(userURL + "/getIdPermission?id="+CURRENT_USER, boolean.class);
		return new ModelAndView("redirect:/allUsers");
	}
	
	
	@RequestMapping("/addUser")
	public String addUser() {
		return "addUser";
	}
	
	@RequestMapping("/addItem")
	public String addItem() {
		System.out.println(ADMIN_STATUS);
		if (ADMIN_STATUS) {
			return "addItem";			
		}
		return "forbidden";
	}

	@RequestMapping("/deleteUser/{id}")
	public ModelAndView deleteUser(@PathVariable int id) {
		template.delete(userURL + "/all/"+id, String.class);
		return new ModelAndView("redirect:/allUsers");
	}
	
	@RequestMapping("/logout")
	public ModelAndView logout() {
		CURRENT_USER = 1;
		ADMIN_STATUS = false;
		AUTHENTICATED = false;
		return new ModelAndView("logout");
	}
	
	@PostMapping("/saveItem")
	public String saveItem(@RequestParam int productID, @RequestParam String productName, @RequestParam int productPrice, Model m) {
		String message = template.getForObject(itemURL + "/addItem?productID="+productID+"&productName="+productName+"&productPrice="+productPrice, String.class);
		m.addAttribute("message", message);
		return "addedItem";
	}
	
	@PostMapping(value = "/authenticate")
	public ModelAndView authenticate(@RequestParam String username, @RequestParam String password) throws IOException{
		
		boolean success=template.getForObject(userURL + "/authenticate?username="+username+"&password="+password, boolean.class); 
		
		if(success==true){
			CURRENT_USER = template.getForObject(userURL + "/getUserId?username="+username, int.class);
			AUTHENTICATED = true;
			ADMIN_STATUS = template.getForObject(userURL + "/getUserPermission?username="+username, boolean.class);
			System.out.println(ADMIN_STATUS);
//			template.postForObject("http://localhost:8084/addLog", user.getId(), Integer.class);
			ModelAndView view = new ModelAndView("loginsuccess");
			view.addObject("admin", ADMIN_STATUS);
			return view;
		}
		return new ModelAndView("login");	
	}
	
	@RequestMapping("/showCatalog")  
	public String viewemp(Model m){  
		if (AUTHENTICATED) {
			List<Item> list=template.getForObject(itemURL + "/showCatalog", List.class);
	//      System.out.println(list.get(0).getProductID());
			m.addAttribute("list",list);
			return "showCatalog";  
		}
		return "login";
	} 
	
	@RequestMapping("/order")
	public String order(@PathVariable int id) {
		if (AUTHENTICATED) {
			template.postForObject("http://localhost:8084/addOrder", id, Integer.class);		
			return "redirect:showCatalog";			
		}
		return "login";
	}
	
	@RequestMapping("addProduct/{productID}")
	public ModelAndView addProduct(@PathVariable int productID) {
		currList = currList + productID + ",";
		
		return new ModelAndView("redirect:/showCatalog");
	}
	
	@RequestMapping("/allUsers")
	public String allUsers(Model m) {
		if (AUTHENTICATED) {
			if (ADMIN_STATUS) {			
				List<User> users = Arrays.asList(template.getForObject(userURL + "/all", User[].class));
				m.addAttribute("list", users);
				return "showUsers";
			}
			return "forbidden";			
		}
		return "login";
	}
	
	@PostMapping("/calculateBill")	
	public ModelAndView calculateBill(Model m) {
		if (AUTHENTICATED) {
			
			String order = currList.substring(0,currList.length()-1);
			String ord =template.getForObject(orderURL + "/addOrder?uid="+CURRENT_USER+"&order="+order, String.class);
			
			int bill = 0;
			int n=order.length();
			List<Integer> idlist=new ArrayList<Integer>();
			String s="";
			for(int i=0;i<n;i++){
				char ch=order.charAt(i);
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
			List<Item> list = new ArrayList<Item>();
			n=idlist.size();
			for(int i=0;i<n;i++){
				
				Item item = template.getForObject(itemURL + "/showCatalog/" + idlist.get(i), Item.class);
				bill += item.getProductPrice();
				System.out.print(item.getProductName() + " ");
				list.add(item);
			}
			System.out.println("");
			
//		int bill=template.getForObject("http://localhost:8083/totalBill/1", Integer.class);
			m.addAttribute("list", list);
			m.addAttribute("total", bill);
			currList = "";
			return new ModelAndView("showBill");
		}
		return new ModelAndView("login");
	}
	
	@RequestMapping("/viewPrevousOrders")  
	public String viewPrevousOrders(Model m){  
		if (AUTHENTICATED) {			
//			String list = template.getForObject("http://localhost:8083/showOrders/"+CURRENT_USER, String.class);

			
//			
			List<Order> list = Arrays.asList(template.getForObject(orderURL + "/showOrders/"+CURRENT_USER, Order[].class));
////			System.out.println(list);
			List<List<Item>> orders = new ArrayList<List<Item>>();
//			
			List<Integer> ids = new ArrayList<Integer>();
			List<Integer> bills = new ArrayList<Integer>();
			for (int i=0;i<list.size();i++) {
				ids.add(list.get(i).getoID());
				String order = list.get(i).getProductlist();
				
				int bill = 0;
				int n=order.length();
				System.out.println(order + "ORDER");
				List<Integer> idlist=new ArrayList<Integer>();
				String s="";
				for(int j=0;j<n;j++){
					char ch=order.charAt(j);
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
				List<Item> list1 = new ArrayList<Item>();
				n=idlist.size();
				for(int j=0;j<n;j++){
					
					Item item = template.getForObject(itemURL + "/showCatalog/" + idlist.get(j), Item.class);
					bill += item.getProductPrice();
					list1.add(item);
				}
				bills.add(bill);
				orders.add(list1);	
			}
			
//			System.out.println(list);
			m.addAttribute("list",orders);
			m.addAttribute("bills", bills);
			m.addAttribute("ids", ids);
			return "viewPrevousOrders";  
		}
		return "login";
	}
	
	
	@RequestMapping("/showUserOrders/{id}")
	public String viewUserOrders(@PathVariable int id, Model m) {
		if (AUTHENTICATED) {			
//			String list = template.getForObject("http://localhost:8083/showOrders/"+CURRENT_USER, String.class);

			
//			
			List<Order> list = Arrays.asList(template.getForObject(orderURL + "/showOrders/"+id, Order[].class));
////			System.out.println(list);
			List<List<Item>> orders = new ArrayList<List<Item>>();
//			
			List<Integer> ids = new ArrayList<Integer>();
			List<Integer> bills = new ArrayList<Integer>();
			for (int i=0;i<list.size();i++) {
				ids.add(list.get(i).getoID());
				String order = list.get(i).getProductlist();
				
				int bill = 0;
				int n=order.length();
				System.out.println(order + "ORDER");
				List<Integer> idlist=new ArrayList<Integer>();
				String s="";
				for(int j=0;j<n;j++){
					char ch=order.charAt(j);
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
				List<Item> list1 = new ArrayList<Item>();
				n=idlist.size();
				for(int j=0;j<n;j++){
					
					Item item = template.getForObject(itemURL + "/showCatalog/" + idlist.get(j), Item.class);
					bill += item.getProductPrice();
					list1.add(item);
				}
				bills.add(bill);
				orders.add(list1);	
			}
			
//			System.out.println(list);
			m.addAttribute("list",orders);
			m.addAttribute("bills", bills);
			m.addAttribute("ids", ids);
			return "viewPrevousOrders";  
		}
		return "login";
	}
	
	@RequestMapping("/viewAllOrders")  
	public String viewAllOrders(Model m){  
		if (AUTHENTICATED) {			
//			String list = template.getForObject("http://localhost:8083/showOrders/"+CURRENT_USER, String.class);
			
			if (ADMIN_STATUS) {
				
					List<Order> list = Arrays.asList(template.getForObject(orderURL + "/getAllOrders", Order[].class));
	////			System.out.println(list);
					List<List<Item>> orders = new ArrayList<List<Item>>();
	//			
					List<Integer> uids = new ArrayList<Integer>();
					List<Integer> ids = new ArrayList<Integer>();
					List<Integer> bills = new ArrayList<Integer>();
					for (int i=0;i<list.size();i++) {
						ids.add(list.get(i).getoID());
						uids.add(list.get(i).getuID());
						String order = list.get(i).getProductlist();
						
						int bill = 0;
						int n=order.length();
						System.out.println(order + "ORDER");
						List<Integer> idlist=new ArrayList<Integer>();
						String s="";
						for(int j=0;j<n;j++){
							char ch=order.charAt(j);
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
						List<Item> list1 = new ArrayList<Item>();
						n=idlist.size();
						for(int j=0;j<n;j++){
							
							Item item = template.getForObject(itemURL + "/showCatalog/" + idlist.get(j), Item.class);
							bill += item.getProductPrice();
							list1.add(item);
						}
						bills.add(bill);
						orders.add(list1);	
					}
					
	//			System.out.println(list);
					m.addAttribute("list",orders);
					m.addAttribute("bills", bills);
					m.addAttribute("ids", ids);
					m.addAttribute("uids", uids);
					return "viewAllOrders";  
				}
				
			return "forbidden";
		}
//			
		return "login";
	}
}
