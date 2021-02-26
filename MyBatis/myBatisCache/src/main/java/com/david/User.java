package com.david;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/*这个字段和数据库中不一样，如果要映射上可以直接在SQL语句中通过取别名的方式实现，
* 另外也可以通过在xml中配置resultMap来实现实体类和数据库中列名的映射*/
public class User implements Serializable {
    private Integer id;
    private String address;
    private String name;
    private String sex;
    private Date birthday;

    //一对多关系映射，主表实体应该包含从表实体的集合引用
    private List<Account> accounts;

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
