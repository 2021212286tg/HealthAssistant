package com.example.healthassisstant.service;

import com.example.healthassisstant.Repository.UserGoalsRepository;
import com.example.healthassisstant.entity.User;
import com.example.healthassisstant.entity.UserGoals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserGoalsService {
    @Autowired
    private UserGoalsRepository userGoalsRepository;

    public UserGoals getUserGoals(Long userId) {
        return userGoalsRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("用户目标不存在"));
    }

    public UserGoals updateUserGoals(Long userId, UserGoals goals) {
        UserGoals existingGoals = userGoalsRepository.findByUserId(userId)
                .orElse(new UserGoals());

        User user = new User();
        user.setId(userId);
        existingGoals.setUser(user);

        if (goals.getDailyCalorieGoal() != null) {
            existingGoals.setDailyCalorieGoal(goals.getDailyCalorieGoal());
        }

        if (goals.getDailyProteinGoal() != null) {
            existingGoals.setDailyProteinGoal(goals.getDailyProteinGoal());
        }

        if (goals.getDailyCarbsGoal() != null) {
            existingGoals.setDailyCarbsGoal(goals.getDailyCarbsGoal());
        }

        if (goals.getDailyFatGoal() != null) {
            existingGoals.setDailyFatGoal(goals.getDailyFatGoal());
        }

        return userGoalsRepository.save(existingGoals);
    }
}
