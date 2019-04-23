package com.recruit.mapper;

import com.recruit.entity.User;
import org.apache.ibatis.annotations.*;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

//@Mapper //这里可以使用@Mapper注解，但是每个mapper都加注解比较麻烦，所以统一配置@MapperScan在扫描路径在application类中
public interface UserMapper {

    @Select("SELECT * FROM users WHERE email = #{email}")
    public User getUserByEmail(String email);

    @Select("SELECT * FROM users")
    public List<User> getUserList();

    @Select("SELECT * FROM users WHERE email=#{email}")
    public List<User> getUserListByEmail(String email);

    @Insert("insert into users(email, password) values(#{email}, #{password})")
    public int add(User user);

    @Update("UPDATE users SET password = #{user.password}  WHERE email = #{email}")
    public int update(@Param("email") String email, @Param("user") User user);

    @Delete("DELETE from users where email = #{email} ")
    public int delete(String email);

    @Select("select id from users where users.email=#{email}")
    public Object getPara(@Param("para") String para,@Param("email") String email);
//    @Param("email") String email, @Param("user") User user


    @Update("update users set password=#{password} where id=#{id}")
    public boolean rePassword(@Param("id") int id,@Param("password") String password);
}