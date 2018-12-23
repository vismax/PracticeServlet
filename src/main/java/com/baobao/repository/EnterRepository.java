package com.baobao.repository;

import com.baobao.model.Enter;

import javax.swing.text.DefaultEditorKit;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnterRepository {
    private final static String DB_URL="jdbc:mysql://localhost/baodata";
    private final static String USER="root";
    private final static String PASS="12345678";
    private static Connection connection;
    static{
        try{
//            注册驱动
            Class.forName("com.mysql.jdbc.Driver");
//            连接数据库
            connection= DriverManager.getConnection(DB_URL,USER,PASS);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static ResultSet queryEnter(String x){
        try{
//            创建执行sql的statement
            PreparedStatement statement= connection.prepareStatement("select*from Enter where Username = ?");
            statement.setString(1, x);
            ResultSet set= statement.executeQuery();


//                运行select语句，返回ResultSet结果集

//            while (set.next()){
//                Enter enter = new Enter();
//                enter.setUsername(set.getString("username"));
//                enter.setPassword(set.getString("password"));
//                enters.add(enter);
//            }
//            statement.close();
            return set;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    public static void insertEnter(Enter enter){
        try{

            PreparedStatement statement=connection.prepareStatement("INSERT INTO Enter(username,password)values(?,?)");
            statement.setString(1,enter.getUsername());
            statement.setString(2,enter.getPassword());
            statement.execute();

            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }








}
