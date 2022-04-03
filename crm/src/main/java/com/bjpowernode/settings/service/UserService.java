package com.bjpowernode.settings.service;

import com.bjpowernode.settings.beans.User;

import java.util.List;
import java.util.Map;

/**
 * ClassName:UserService
 * Package:com.bjpowernode.settings.service
 * Description: 描述信息
 *
 * @date:2022/3/12 13:32
 * @author:白白白
 */
public interface UserService {
    User queryUserByLoginActAndLoginPwd(Map hashMap);

    List<User> queryAllUsers();
}
