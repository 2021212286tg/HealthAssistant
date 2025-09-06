package com.example.healthassisstant.mapper;

import com.example.healthassisstant.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    // 根据ID查询用户
    @Select("SELECT * FROM users WHERE id = #{id}")
    User findById(Long id);

    // 根据用户名查询用户（用于登录校验）
    @Select("SELECT * FROM users WHERE username = #{username}")
    User findByUsername(String username);

    // 插入新用户，并返回自增主键
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO users (username, password, email, age, gender, height, weight, target_calories) " +
            "VALUES (#{username}, #{password}, #{email}, #{age}, #{gender}, #{height}, #{weight}, #{targetCalories})")
    int insert(User user);

    // 更新用户信息
    @Update("UPDATE users SET email=#{email}, age=#{age}, gender=#{gender}, height=#{height}, weight=#{weight}, " +
            "target_calories=#{targetCalories}, update_time=NOW() WHERE id=#{id}")
    int update(User user);

    // 根据ID删除用户
    @Delete("DELETE FROM users WHERE id = #{id}")
    int deleteById(Long id);

// 更新用户密码
    @Update("UPDATE users SET password = #{password}, update_time = NOW() WHERE id = #{id}")
    int updatePassword(@Param("id") Long id, @Param("password") String password);

    // 查找所有用户
    @Select("SELECT id, username, email, age, gender, height, weight, target_calories, create_time, update_time FROM users")
    List<User> findAll();
}
