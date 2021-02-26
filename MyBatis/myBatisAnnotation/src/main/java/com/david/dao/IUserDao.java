package com.david.dao;

import com.david.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

import static com.sun.corba.se.spi.activation.IIOP_CLEAR_TEXT.value;

public interface IUserDao {
    /*
    * MyBatis中针对CRUD有
    * @Select @Insert @Update @Delete
    * 四个注解*/
    //@Select(value="select * from user")
    //当注解只有一个value要传的时候可以不写value
    @Select("select * from user")
    List<User> findAll();

    /*
    * user后面接的是数据库对应的字段名，在windows环境中mysql是不区分大小写的
    * values后用的是user 类中定义的字段名称*/
    @Insert("insert into user(username, address, sex, birthday) " +
            "values (#{userName}, #{address}, #{sex}, #{birthday})")
    void addUser(User user);

    @Update("update user set username=#{userName}, address=#{address}," +
            "sex=#{sex}, birthday=#{birthday} where id=49")
    void updateUser(User user);

    @Delete("delete from user where id = #{id}")
    void deleteUser(Integer id);

    @Select("select * from user where id = #{id}")
    User findById(Integer id);


    /*
    两种模糊匹配的写法，如果在注解的语句中写了测试类中就可以不写模糊匹配符了
    注解中用的是参数占位符，测试类中直接用字符串拼接
    @Select("select * from user where username like #{userName}")
    */
    @Select("select * from user where username like '%${value}%'")
    List<User> findByName(String name);
}
