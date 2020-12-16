/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobiquity.test.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.mobiquity.test.model.User;


@RepositoryRestResource(exported = false)
public interface UserRepo extends PagingAndSortingRepository<User, Integer> {

    @Query("select case when count(u) > 0 then true else false end from User u where u.userName= ?1")
    public boolean userNameExists(String userName);

    public User getByUserName(String userName);
}
