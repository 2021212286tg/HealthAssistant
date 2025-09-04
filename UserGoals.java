package com.example.healthassisstant.entity;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "user_goals")
public class UserGoals {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(name = "daily_calorie_goal")
    private Integer dailyCalorieGoal;

    @Column(name = "daily_protein_goal")
    private Integer dailyProteinGoal;

    @Column(name = "daily_carbs_goal")
    private Integer dailyCarbsGoal;

    @Column(name = "daily_fat_goal")
    private Integer dailyFatGoal;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public UserGoals() {
    }

    public UserGoals(Long id, User user, Integer dailyCalorieGoal, Integer dailyProteinGoal, Integer dailyCarbsGoal, Integer dailyFatGoal, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.user = user;
        this.dailyCalorieGoal = dailyCalorieGoal;
        this.dailyProteinGoal = dailyProteinGoal;
        this.dailyCarbsGoal = dailyCarbsGoal;
        this.dailyFatGoal = dailyFatGoal;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getDailyCalorieGoal() {
        return dailyCalorieGoal;
    }

    public void setDailyCalorieGoal(Integer dailyCalorieGoal) {
        this.dailyCalorieGoal = dailyCalorieGoal;
    }

    public Integer getDailyProteinGoal() {
        return dailyProteinGoal;
    }

    public void setDailyProteinGoal(Integer dailyProteinGoal) {
        this.dailyProteinGoal = dailyProteinGoal;
    }

    public Integer getDailyCarbsGoal() {
        return dailyCarbsGoal;
    }

    public void setDailyCarbsGoal(Integer dailyCarbsGoal) {
        this.dailyCarbsGoal = dailyCarbsGoal;
    }

    public Integer getDailyFatGoal() {
        return dailyFatGoal;
    }

    public void setDailyFatGoal(Integer dailyFatGoal) {
        this.dailyFatGoal = dailyFatGoal;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}