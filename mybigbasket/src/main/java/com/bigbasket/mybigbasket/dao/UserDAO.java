package com.bigbasket.mybigbasket.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bigbasket.mybigbasket.entity.User;

public interface UserDAO extends JpaRepository<User, Integer> {
    
    @Query("SELECT u from User u where u.username = ?1")
    public User findByUsername(String username);
}
