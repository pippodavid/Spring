package david.dao;

import david.Account;
import david.User;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface IUserDao {
    /*
    * MyBatis中针对CRUD有
    * @Select @Insert @Update @Delete
    * 四个注解*/
    //@Select(value="select * from user")
    //当注解只有一个value要传的时候可以不写value
    @Select("select * from user")
    @Results(id="userMapper", value={
            @Result(id = true, column = "id", property = "userId"),
            @Result(column = "username", property = "userName"),
            @Result(column = "address", property = "userAddress"),
            @Result(column = "sex", property = "userSex"),
            @Result(column = "birthday", property = "userBirthday"),
            @Result(property = "accounts", column = "id",
                    many = @Many(select = "david.dao.IUserDao.findAccountByUid",
                            fetchType = FetchType.LAZY))
    })
    List<User> findAll();

    @Select("select * from account where uid = #{userId}")
    List<Account> findAccountByUid(Integer userId);

    @Select("select * from user where id = #{id}")
    @ResultMap(value = {"userMapper"})
    User findById(Integer id);

    /*
    两种模糊匹配的写法，如果在注解的语句中写了测试类中就可以不写模糊匹配符了
    注解中用的是参数占位符，测试类中直接用字符串拼接
    @Select("select * from user where username like #{userName}")
    */
    @Select("select * from user where username like '%${value}%'")
    List<User> findByName(String name);
}
