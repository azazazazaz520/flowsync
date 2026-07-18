package com.flowsync.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.flowsync.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    @Select("SELECT * FROM sys_user WHERE username = #{username}")
    User selectByUsername(@Param("username") String username);

    @Select("SELECT id, username, real_name, role, phone, email, create_time FROM sys_user ORDER BY id")
    List<User> selectAllWithoutPassword();

    @Select("SELECT * FROM sys_user WHERE id = #{id}")
    User selectById(@Param("id") Long id);

    @Insert("INSERT INTO sys_user(username, password, real_name, role, phone, email) " +
            "VALUES(#{username}, #{password}, #{realName}, #{role}, #{phone}, #{email})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insertUser(User user);

    @Update("UPDATE sys_user SET username=#{username}, password=#{password}, real_name=#{realName}, " +
            "role=#{role}, phone=#{phone}, email=#{email} WHERE id=#{id}")
    int updateUser(User user);

    @Update("UPDATE sys_user SET password = #{newPassword} WHERE id = #{id}")
    int updatePassword(@Param("id") Long id, @Param("newPassword") String newPassword);

    @Delete("DELETE FROM sys_user WHERE id = #{id}")
    int deleteById(@Param("id") Long id);
}
