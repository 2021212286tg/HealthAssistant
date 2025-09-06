package com.example.healthassisstant.controller;
import com.example.healthassisstant.common.Result;
import com.example.healthassisstant.dto.PasswordChangeRequest;
import com.example.healthassisstant.entity.User;
import com.example.healthassisstant.entity.UserGoals;
import com.example.healthassisstant.service.UserGoalsService;
import com.example.healthassisstant.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users/{userId}/goals")
@CrossOrigin(origins = "http://localhost:8081")
public class UserGoalsController {
    @Autowired
    private UserGoalsService userGoalsService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getUserGoals(@PathVariable Long userId) {
        Map<String, Object> response = new HashMap<>();
        try {
            UserGoals goals = userGoalsService.getUserGoals(userId);

            response.put("code", 200);
            response.put("message", "获取用户目标成功");
            response.put("data", goals);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("code", 500);
            response.put("message", "获取用户目标失败: " + e.getMessage());
            response.put("data", null);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PutMapping
    public ResponseEntity<Map<String, Object>> updateUserGoals(
            @PathVariable Long userId,
            @RequestBody UserGoals goals) {
        Map<String, Object> response = new HashMap<>();
        try {
            UserGoals updatedGoals = userGoalsService.updateUserGoals(userId, goals);

            response.put("code", 200);
            response.put("message", "用户目标更新成功");
            response.put("data", updatedGoals);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("code", 500);
            response.put("message", "更新用户目标失败: " + e.getMessage());
            response.put("data", null);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
