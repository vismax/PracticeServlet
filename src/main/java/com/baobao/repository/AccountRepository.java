package com.baobao.repository;

import com.baobao.model.Account;
import com.google.gson.Gson;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountRepository {


    private static final String DB_URL = "jdbc:mysql://localhost/baodata";


    //  Database credentials
    private static final String USER = "root";
    private static final String PASS = "12345678";

    private static Connection connection;

    static {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public static List<Account> queryAccount(Integer id,String time){
        List<Account> accounts =  new ArrayList();
        try {

            Statement statement = connection.createStatement();

            ResultSet set ;

            if(id != null){
                set= statement.executeQuery("select * from AccountBook where id = " + id);
            }else {
                set= statement.executeQuery("select * from AccountBook where Time = " + time);
            }

            while(set.next()) {
                Account account = new Account();
                account.setId(set.getInt("Id"));
                account.setItem(set.getString("Item"));
                account.setTime(set.getString("Time"));
                account.setMoney(set.getInt("Money"));

                accounts.add(account);

            }
            statement.close();
            set.close();

            return accounts;
        }catch (Exception e){
            e.printStackTrace();
            return accounts;
        }
    }

    public static List<Account> queryAccount(String username){
        List<Account> accounts =  new ArrayList();
        try {

            PreparedStatement statement = connection.prepareStatement("select * from AccountBook where Username = ?");
            statement.setString(1,username);
            ResultSet set = statement.executeQuery();



            while(set.next()) {
                Account account = new Account();
                account.setId(set.getInt("Id"));
                account.setItem(set.getString("Item"));
                account.setTime(set.getString("Time"));
                account.setMoney(set.getInt("Money"));

                accounts.add(account);

            }
            statement.close();
            set.close();

            return accounts;
        }catch (Exception e){
            e.printStackTrace();
            return accounts;
        }
    }


    public static void insertAccount(Account account){
        try {


            PreparedStatement statement = connection.prepareStatement("INSERT INTO AccountBook(Item, Time, Money, Username)Values (?,?,?,?);");
            statement.setString(1,account.getItem());
            statement.setString(2,account.getTime());
            statement.setInt(3,account.getMoney());
            statement.setString(4, account.getUserName());

            statement.execute();

            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }




    public static void main(String[] args) {

        System.out.println(String.format("select * from AccountBook where id = %s,%s","gao","yan"));

    }


}
