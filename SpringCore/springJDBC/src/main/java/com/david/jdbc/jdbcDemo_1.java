package com.david.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
* 程序的耦合
* */
public class jdbcDemo_1 {
    public static void main(String[] args) throws SQLException, ClassNotFoundException{
        //注册驱动
        //DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        Class.forName("com.mysql.jdbc.Driver");
        //获取连接
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db3",
        "root", "david");
        //获取操作数据库的预处理对象
        PreparedStatement preparedStatement = connection.prepareStatement("select * from account");
        //执行SQL，得到结果集
        ResultSet resultSet = preparedStatement.executeQuery();
        //遍历结果集
        while (resultSet.next()) {
            System.out.println(resultSet.getInt("uid"));
        }
        //释放资源
        resultSet.close();
        preparedStatement.close();
        connection.close();
    }


}
