package david.dao;

import david.Account;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface IAccountDao {

    /*
    * 支持@One, @Many注解实现一对多和一对一
    * fecchType用于指定加载模式，一对一通常采用eager,也就是立即加载
    * */
    @Select("select * from account")
    @Results(id="accountMap", value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "uid", property = "uid"),
            @Result(column = "money", property = "money"),
            @Result(property = "user", column = "uid",
                    one = @One (select = "david.dao.IUserDao.findById",
                    fetchType = FetchType.EAGER))
    })
    public List<Account> findAll();
}
