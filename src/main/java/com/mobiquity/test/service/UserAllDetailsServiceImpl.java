/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobiquity.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mobiquity.test.dao.UserRepo;

import java.util.Arrays;


@Service
public class UserAllDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepo repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.mobiquity.test.model.User user = repository.getByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException("username not found");
        }
        return new User(username, user.getPassword(), user.isActive(), true, true, true, Arrays.asList(user));
    }
}
