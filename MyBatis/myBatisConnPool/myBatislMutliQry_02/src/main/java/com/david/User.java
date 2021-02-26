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

    //一个用户可以有多个角色
    private List<Role> roles;

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
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
