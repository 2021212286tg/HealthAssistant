package com.example.healthassisstant.controller;

import com.example.healthassisstant.common.Result;
import com.example.healthassisstant.dto.PasswordChangeRequest;
import com.example.healthassisstant.entity.User;
import com.example.healthassisstant.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:8081")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id) {
        try {
            User user = userService.getUserById(id);
            return ResponseEntity.ok(user);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        try {
            User updatedUser = userService.updateUser(id, userDetails);
            return ResponseEntity.ok(updatedUser);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/{userId}/password")
    public ResponseEntity<Map<String, Object>> changePassword(
            @PathVariable Long userId,
            @RequestBody Map<String, String> passwordData) {

        Map<String, Object> response = new HashMap<>();
        try {
            String oldPassword = passwordData.get("oldPassword");
            String newPassword = passwordData.get("newPassword");

            if (oldPassword == null || newPassword == null) {
                response.put("code", 400);
                response.put("message", "原密码和新密码不能为空");
                response.put("data", null);
                return ResponseEntity.badRequest().body(response);
            }

            boolean success = userService.changePassword(userId, oldPassword, newPassword);

            if (success) {
                response.put("code", 200);
                response.put("message", "密码修改成功");
                response.put("data", null);
                return ResponseEntity.ok(response);
            } else {
                response.put("code", 400);
                response.put("message", "密码修改失败，请检查原密码是否正确");
                response.put("data", null);
                return ResponseEntity.badRequest().body(response);
            }
        } catch (Exception e) {
            response.put("code", 500);
            response.put("message", "密码修改失败: " + e.getMessage());
            response.put("data", null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}