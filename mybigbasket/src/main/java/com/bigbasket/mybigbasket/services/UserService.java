package com.bigbasket.mybigbasket.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import com.bigbasket.mybigbasket.dao.UserDAO;
import com.bigbasket.mybigbasket.entity.User;

@Service
public class UserService {
 
    @Autowired
    private UserDAO dao;

    public List<User> getAll(){
        return dao.findAll();
    }

    public User getUserWithId(int id){
        return dao.getById(id);
    }

    public String addUser(User user){
        dao.save(user);
        return "A NEW USER HAS BEEN ADDED TO THE DB";
    }

    public String deleteUserWithId(int id){
        dao.deleteById(id);
        return "USER WITH ID " + id + " HAS BEEN DELETED FROM THE DATABASE";
    }

    public User checkUserWithUsernameExistence(String username){
        User user = dao.findByUsername(username);
        return user;
    }

    public String giveAdminStatusForUserWithId(int id){
        User user = dao.getById(id);
        user.setAdminStatus(true);
        dao.save(user);
        return "Admin status set for user with id " + id; 
    }

    public String revokeAdminStatusForUserWithId(int id){
        User user = dao.getById(id);
        user.setAdminStatus(false);
        dao.save(user);
        return "Admin status revoked for user with id " + id;
    }

}
