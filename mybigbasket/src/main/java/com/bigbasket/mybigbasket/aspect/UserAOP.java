package com.bigbasket.mybigbasket.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class UserAOP {
    
    @Before(value="execution(* com.bigbasket.mybigbasket.services.UserService.addUser(..))")
    public void addedNewUser(){
        System.out.println("A NEW USER HAS BEEN ADDED TO THE DATABASE");
    }

    @Before(value="execution(* com.bigbasket.mybigbasket.services.UserService.getAll(..))")
    public void getAllUsers(){
        System.out.println("Getting all users from the database");
    }

    @Before(value="execution(* com.bigbasket.mybigbasket.services.UserService.getUserWithId(..))")
    public void getSingleUser(){
        System.out.println("Finding current user");
    }

    @Before(value="execution(* com.bigbasket.mybigbasket.services.UserService.deleteUserWithId(..))")
    public void deleteUser(){
        System.out.println("A user has been deleted from the database");
    }

    @Before(value="execution(* com.bigbasket.mybigbasket.services.UserService.checkUserWithUsernameExistence(..))")
    public void userAuth(){
        System.out.println("User Authenticating .... ");
    }

}
