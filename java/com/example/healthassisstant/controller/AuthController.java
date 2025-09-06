package com.example.healthassisstant.controller;

import com.example.healthassisstant.dto.LoginRequest;
import com.example.healthassisstant.entity.User;
import com.example.healthassisstant.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:8081")
@Tag(name = "认证管理", description = "用户登录、注册和认证相关的API接口")
public class AuthController {
    @Autowired
    private UserService userService;
    @Operation(
            summary = "用户注册",
            description = "注册新用户账号，用户名必须唯一"
    )
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        try {
            User registeredUser = userService.register(user);
            // 返回统一的响应格式
            Map<String, Object> response = new HashMap<>();
            response.put("code", 200);
            response.put("message", "注册成功");
            response.put("data", registeredUser);

            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            // 返回错误响应
            Map<String, Object> response = new HashMap<>();
            response.put("code", 400);
            response.put("message", e.getMessage());
            response.put("data", null);

            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            User user = userService.login(loginRequest.getUsername(), loginRequest.getPassword());
            // 返回统一的响应格式
            Map<String, Object> response = new HashMap<>();
            response.put("code", 200);
            response.put("message", "登录成功");
            response.put("data", user);

            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            // 返回错误响应
            Map<String, Object> response = new HashMap<>();
            response.put("code", 400);
            response.put("message", e.getMessage());
            response.put("data", null);

            return ResponseEntity.badRequest().body(response);
        }
    }
}
