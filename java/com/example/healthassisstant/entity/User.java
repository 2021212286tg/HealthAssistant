//package com.example.healthassisstant.entity;
//
//import jakarta.persistence.*;
//import lombok.Data;
//import org.hibernate.annotations.CreationTimestamp;
//import org.hibernate.annotations.UpdateTimestamp;
//
//import java.math.BigDecimal;
//import java.time.LocalDateTime;
//import java.util.List;
//
//@Entity
//@Table(name = "users")
//public class User {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(nullable = false, unique = true)
//    private String username;
//
//    @Column(nullable = false)
//    private String password;
//
//    @Column(unique = true)
//    private String email;
//
//    private String avatar;
//
//    @Enumerated(EnumType.STRING)
//    private Gender gender;
//
//    private Integer age;
//
//    @Column(precision = 5, scale = 2)
//    private BigDecimal height;
//
//    @Column(precision = 5, scale = 2)
//    private BigDecimal weight;
//
//    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private UserGoals goals;
//
//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<FoodRecord> foodRecords;
//
//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<DailyNutrition> dailyNutrition;
//
//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<HealthReport> healthReports;
//
//    @CreationTimestamp
//    private LocalDateTime createTime;
//
//    @UpdateTimestamp
//    private LocalDateTime updateTime;
//
//    // 枚举类型
//    public enum Gender {
//        male, female, other
//    }
//
//    // 构造方法、getter和setter
//
//    public User() {
//    }
//
//    public User(Long id, String username, String password, String email, String avatar, Gender gender, Integer age, BigDecimal height, BigDecimal weight, UserGoals goals, List<FoodRecord> foodRecords, List<DailyNutrition> dailyNutrition, List<HealthReport> healthReports, LocalDateTime createTime, LocalDateTime updateTime) {
//        this.id = id;
//        this.username = username;
//        this.password = password;
//        this.email = email;
//        this.avatar = avatar;
//        this.gender = gender;
//        this.age = age;
//        this.height = height;
//        this.weight = weight;
//        this.goals = goals;
//        this.foodRecords = foodRecords;
//        this.dailyNutrition = dailyNutrition;
//        this.healthReports = healthReports;
//        this.createTime = createTime;
//        this.updateTime = updateTime;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getAvatar() {
//        return avatar;
//    }
//
//    public void setAvatar(String avatar) {
//        this.avatar = avatar;
//    }
//
//    public Gender getGender() {
//        return gender;
//    }
//
//    public void setGender(Gender gender) {
//        this.gender = gender;
//    }
//
//    public Integer getAge() {
//        return age;
//    }
//
//    public void setAge(Integer age) {
//        this.age = age;
//    }
//
//    public BigDecimal getHeight() {
//        return height;
//    }
//
//    public void setHeight(BigDecimal height) {
//        this.height = height;
//    }
//
//    public BigDecimal getWeight() {
//        return weight;
//    }
//
//    public void setWeight(BigDecimal weight) {
//        this.weight = weight;
//    }
//
//    public UserGoals getGoals() {
//        return goals;
//    }
//
//    public void setGoals(UserGoals goals) {
//        this.goals = goals;
//    }
//
//    public List<FoodRecord> getFoodRecords() {
//        return foodRecords;
//    }
//
//    public void setFoodRecords(List<FoodRecord> foodRecords) {
//        this.foodRecords = foodRecords;
//    }
//
//    public List<DailyNutrition> getDailyNutrition() {
//        return dailyNutrition;
//    }
//
//    public void setDailyNutrition(List<DailyNutrition> dailyNutrition) {
//        this.dailyNutrition = dailyNutrition;
//    }
//
//    public List<HealthReport> getHealthReports() {
//        return healthReports;
//    }
//
//    public void setHealthReports(List<HealthReport> healthReports) {
//        this.healthReports = healthReports;
//    }
//
//    public LocalDateTime getCreateTime() {
//        return createTime;
//    }
//
//    public void setCreateTime(LocalDateTime createTime) {
//        this.createTime = createTime;
//    }
//
//    public LocalDateTime getUpdateTime() {
//        return updateTime;
//    }
//
//    public void setUpdateTime(LocalDateTime updateTime) {
//        this.updateTime = updateTime;
//    }
//
//    @Override
//    public String toString() {
//        return "User{" +
//                "id=" + id +
//                ", username='" + username + '\'' +
//                ", email='" + email + '\'' +
//                ", gender=" + gender +
//                ", age=" + age +
//                ", height=" + height +
//                ", weight=" + weight +
//                ", createTime=" + createTime +
//                ", updateTime=" + updateTime +
//                '}';
//    }
//
//    // 计算BMI
//    public BigDecimal calculateBMI() {
//        if (height == null || weight == null || height.compareTo(BigDecimal.ZERO) == 0) {
//            return null;
//        }
//        // BMI = 体重(kg) / (身高(m) * 身高(m))
//        BigDecimal heightInMeters = height.divide(BigDecimal.valueOf(100), 2, BigDecimal.ROUND_HALF_UP);
//        return weight.divide(heightInMeters.multiply(heightInMeters), 2, BigDecimal.ROUND_HALF_UP);
//    }
//}
package com.example.healthassisstant.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(unique = true)
    private String email;

    private String avatar;

    private String gender;

    private Integer age;

    @Column(precision = 5, scale = 2)
    private BigDecimal height;

    @Column(precision = 5, scale = 2)
    private BigDecimal weight;

    @CreationTimestamp
    @Column(name = "create_time")
    private LocalDateTime createTime;

    @UpdateTimestamp
    @Column(name = "update_time")
    private LocalDateTime updateTime;

    // 默认构造方法
    public User() {
    }

    // Getter 和 Setter 方法
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public BigDecimal getHeight() {
        return height;
    }

    public void setHeight(BigDecimal height) {
        this.height = height;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }
}