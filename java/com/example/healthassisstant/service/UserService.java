package com.example.healthassisstant.service;

import com.example.healthassisstant.Repository.UserGoalsRepository;
import com.example.healthassisstant.entity.User;
import com.example.healthassisstant.entity.UserGoals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private com.example.healthassisstant.repository.UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserGoalsRepository userGoalsRepository;

    public User register(User user) {
        // 检查用户名是否已存在
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new RuntimeException("用户名已存在");
        }

        // 检查邮箱是否已存在
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("邮箱已存在");
        }

        // 加密密码
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // 设置默认值
        if (user.getAvatar() == null) {
            user.setAvatar("");
        }

        // 保存用户
        User savedUser = userRepository.save(user);

        try {
            // 创建默认用户目标
            UserGoals defaultGoals = new UserGoals();
            defaultGoals.setUser(savedUser);
            defaultGoals.setDailyCalorieGoal(2000);
            defaultGoals.setDailyProteinGoal(50);
            defaultGoals.setDailyCarbsGoal(250);
            defaultGoals.setDailyFatGoal(70);
            userGoalsRepository.save(defaultGoals);
        } catch (Exception e) {
            // 如果创建目标失败，记录错误但不影响用户注册
            System.err.println("创建用户目标失败: " + e.getMessage());
        }

        return savedUser;
    }

    public User login(String username, String password) {
        // 查找用户
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("用户不存在"));

        // 验证密码
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("密码错误");
        }

        return user;
    }

    public User updateUser(Long userId, User userDetails) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("用户不存在"));

        if (userDetails.getEmail() != null &&
                !userDetails.getEmail().equals(user.getEmail()) &&
                userRepository.existsByEmail(userDetails.getEmail())) {
            throw new RuntimeException("邮箱已存在");
        }

        if (userDetails.getGender() != null) {
            user.setGender(userDetails.getGender());
        }

        if (userDetails.getAge() != null) {
            user.setAge(userDetails.getAge());
        }

        if (userDetails.getHeight() != null) {
            user.setHeight(userDetails.getHeight());
        }

        if (userDetails.getWeight() != null) {
            user.setWeight(userDetails.getWeight());
        }

        if (userDetails.getEmail() != null) {
            user.setEmail(userDetails.getEmail());
        }

        if (userDetails.getAvatar() != null) {
            user.setAvatar(userDetails.getAvatar());
        }

        return userRepository.save(user);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("用户不存在"));
    }

    public boolean changePassword(Long userId, String oldPassword, String newPassword) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("用户不存在"));

        // 验证原密码
        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            return false;
        }

        // 加密新密码
        String encodedNewPassword = passwordEncoder.encode(newPassword);
        user.setPassword(encodedNewPassword);

        // 保存用户
        userRepository.save(user);

        return true;
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public Map<String, Object> getUserGoals(Long userId) {
        Map<String, Object> goals = new HashMap<>();

        // 从 UserGoalsRepository 获取用户目标，而不是从 User 实体
        Optional<UserGoals> userGoalsOptional = userGoalsRepository.findByUserId(userId);

        if (userGoalsOptional.isPresent()) {
            UserGoals userGoals = userGoalsOptional.get();
            goals.put("dailyCalorieGoal", userGoals.getDailyCalorieGoal() != null ? userGoals.getDailyCalorieGoal() : 2000);
            goals.put("dailyProteinGoal", userGoals.getDailyProteinGoal() != null ? userGoals.getDailyProteinGoal() : 50);
            goals.put("dailyCarbsGoal", userGoals.getDailyCarbsGoal() != null ? userGoals.getDailyCarbsGoal() : 250);
            goals.put("dailyFatGoal", userGoals.getDailyFatGoal() != null ? userGoals.getDailyFatGoal() : 70);
        } else {
            // 默认值
            goals.put("dailyCalorieGoal", 2000);
            goals.put("dailyProteinGoal", 50);
            goals.put("dailyCarbsGoal", 250);
            goals.put("dailyFatGoal", 70);
        }

        return goals;
    }

    // 添加设置用户目标的方法
    public boolean setUserGoals(Long userId, Map<String, Object> goals) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (!userOptional.isPresent()) {
            return false;
        }

        User user = userOptional.get();
        Optional<UserGoals> userGoalsOptional = userGoalsRepository.findByUserId(userId);

        UserGoals userGoals;
        if (userGoalsOptional.isPresent()) {
            userGoals = userGoalsOptional.get();
        } else {
            userGoals = new UserGoals();
            userGoals.setUser(user);
        }

        if (goals.containsKey("dailyCalorieGoal")) {
            userGoals.setDailyCalorieGoal((Integer) goals.get("dailyCalorieGoal"));
        }

        if (goals.containsKey("dailyProteinGoal")) {
            userGoals.setDailyProteinGoal((Integer) goals.get("dailyProteinGoal"));
        }

        if (goals.containsKey("dailyCarbsGoal")) {
            userGoals.setDailyCarbsGoal((Integer) goals.get("dailyCarbsGoal"));
        }

        if (goals.containsKey("dailyFatGoal")) {
            userGoals.setDailyFatGoal((Integer) goals.get("dailyFatGoal"));
        }

        userGoalsRepository.save(userGoals);
        return true;
    }
}