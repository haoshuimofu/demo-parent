package com.demo.service.impl;

import com.demo.dao.UserDao;
import com.demo.domain.model.User;
import com.demo.service.IUserService;
import com.demo.service.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户Service实现类
 *
 * @Author Wude
 * @Create 2017-05-12 21:18
 */
@Service("userService")
public class UserServiceImpl extends BaseService implements IUserService {

    @Autowired
    private UserDao userDao;

    @Override
    public List<User> getAll() {
        return userDao.selectAll();
    }

    @Override
    public User getByUsername(String username) {
        User queryUser = new User();
        queryUser.setUsername(username);
        return userDao.selectOne(queryUser);
    }

}