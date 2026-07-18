package com.flowsync.controller;

import com.flowsync.common.Result;
import com.flowsync.dto.LoginRequest;
import com.flowsync.dto.PasswordUpdateRequest;
import com.flowsync.entity.User;
import com.flowsync.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AuthController {

    @Autowired
    private UserService userService;

    // ==================== 登录 ====================

    @PostMapping("/api/auth/login")
    @Operation(summary = "用户登录")
    public Result login(@RequestBody LoginRequest req) {
        User user = userService.login(req.getUsername(), req.getPassword());
        if (user == null) {
            return Result.error().msg("用户名或密码错误");
        }
        return Result.success().msg("登录成功").data("user", user).data("token", "token-" + user.getId());
    }

    // ==================== 成员列表 ====================

    @GetMapping("/api/users")
    @Operation(summary = "获取全部用户列表")
    public Result listUsers() {
        List<User> list = userService.listAll();
        return Result.success().data("data", list);
    }

    // ==================== 修改密码 ====================

    @PostMapping("/api/users/update-password")
    @Operation(summary = "修改密码")
    public Result updatePassword(@RequestBody PasswordUpdateRequest req) {
        boolean ok = userService.updatePassword(req.getUserId(), req.getOldPassword(), req.getNewPassword());
        return ok ? Result.success().msg("密码修改成功")
                  : Result.error().msg("旧密码不正确");
    }
}
