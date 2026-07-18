package com.flowsync.service.impl;

import com.flowsync.entity.User;
import com.flowsync.mapper.UserMapper;
import com.flowsync.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(String username, String password) {
        User user = userMapper.selectByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            user.setPassword(null);   // 不返回密码
            return user;
        }
        return null;
    }

    @Override
    public List<User> listAll() {
        return userMapper.selectAllWithoutPassword();
    }

    @Override
    public boolean updatePassword(Long userId, String oldPassword, String newPassword) {
        User user = userMapper.selectById(userId);
        if (user == null || !user.getPassword().equals(oldPassword)) {
            return false;
        }
        return userMapper.updatePassword(userId, newPassword) > 0;
    }
}
