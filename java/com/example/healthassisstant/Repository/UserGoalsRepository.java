package com.example.healthassisstant.Repository;

import com.example.healthassisstant.entity.UserGoals;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserGoalsRepository extends JpaRepository<UserGoals, Long> {
    Optional<UserGoals> findByUserId(Long userId);
}
