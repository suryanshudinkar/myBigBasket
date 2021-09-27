package com.bigbasket.mybigbasket.controller;

import com.bigbasket.mybigbasket.services.UserService;
import com.bigbasket.mybigbasket.entity.User;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    
    @Autowired
    private UserService userService;

    @RequestMapping("/authenticate")
    public boolean checkUserAuthenticity(@RequestParam String username, @RequestParam String password){
        User user = userService.checkUserWithUsernameExistence(username);
        if (user != null){
            if (password.equals(user.getPassword())){
                return true;
            }
        }
        return false;
    }

    @RequestMapping("/all")
    public List<User> findAll(){
        return userService.getAll();
    }

    @RequestMapping("/getUserId")
    public int getUserId(@RequestParam String username){
        return userService.checkUserWithUsernameExistence(username).getId();
    }

    @RequestMapping("/getUserPermission")
    public boolean getUserPermission(@RequestParam String username){
        return userService.checkUserWithUsernameExistence(username).getAdminStatus();
    }

    @RequestMapping("/getIdPermission")
    public boolean getIdPermission(@RequestParam int id){
        return userService.getUserWithId(id).getAdminStatus();
    }

    @RequestMapping("/all/{id}")
    public String getUserWithId(@PathVariable int id){
        User user = userService.getUserWithId(id);
        return "Hi, " + user.getUsername();
    }

    @RequestMapping("/add")
    public String addUser(@RequestParam String name, @RequestParam String username, @RequestParam String password){
        User user = new User(name, username, password);
        return userService.addUser(user);
    }

    @DeleteMapping("/all/{id}")
    public String deleteUserWithId(@PathVariable int id){
        return userService.deleteUserWithId(id);
    }

    @RequestMapping("/setAdmin/{id}")
    public String setAdminUserWithId(@PathVariable int id){
        return userService.giveAdminStatusForUserWithId(id);
    }

    @RequestMapping("/revokeAdmin/{id}")
    public String revokeAdminUserWithId(@PathVariable int id){
        return userService.revokeAdminStatusForUserWithId(id);
    }

}
