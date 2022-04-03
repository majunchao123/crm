package com.bjpowernode.settings.service.impl;

import com.bjpowernode.settings.beans.User;
import com.bjpowernode.settings.mapper.UserMapper;
import com.bjpowernode.settings.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * ClassName:UserServiceImpl
 * Package:com.bjpowernode.settings.service.impl
 * Description: 描述信息
 *
 * @date:2022/3/12 13:35
 * @author:白白白
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    public User queryUserByLoginActAndLoginPwd(Map hashMap) {
        User user = userMapper.selectUserByLoginActAndPwd(hashMap);
        return user;
    }

    public List<User> queryAllUsers() {
       return userMapper.selectAllUsers();
    }
}
