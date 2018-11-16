package com.demo.service;

import com.demo.domain.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户Service
 *
 * @Author Wude
 * @Create 2017-05-12 21:17
 */
@Repository
public interface IUserService {


    List<User> getAll();

    User getByUsername(String username);

}

