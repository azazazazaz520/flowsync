package com.flowsync.service;

import com.flowsync.entity.User;

import java.util.List;

public interface UserService {

    /** 登录：验证用户名密码，成功返回用户信息（密码置空），失败返回 null */
    User login(String username, String password);

    /** 查询全部用户（不含密码） */
    List<User> listAll();

    /** 修改密码，返回是否成功 */
    boolean updatePassword(Long userId, String oldPassword, String newPassword);
}
