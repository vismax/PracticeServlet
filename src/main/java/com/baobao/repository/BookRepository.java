package com.baobao.repository;

import com.baobao.model.Book;
import com.google.gson.Gson;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static org.omg.CORBA.ORB.init;

public class BookRepository {
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
    public static List<Book> queryBook(Integer id,String bookname ){
        List<Book> books = new ArrayList();
        try {
            Statement statement=connection.createStatement();
             ResultSet set;
        if(id != null){
            set = statement.executeQuery("select * from BookManage where id= "+id);
        }else{
            set= statement.executeQuery("select * from BookManage where bookname="+ bookname);
        }
        while(set.next()){
            Book book = new Book();
            book.setId(set.getInt("id"));
            book.setBookname(set.getString("bookname"));
            book.setPrice(set.getInt("price"));

            books.add(book);

        }
            statement.close();
            set.close();

            return books;
        }catch (Exception e){
            e.printStackTrace();
            return books;
        }
    }


    public static void insertBook(Book book){
        try {
            if(connection == null){
                init();
            }
            Statement statement = connection.createStatement();
            statement.execute("INSERT INTO BookManage(Bookname,Price)Values('"+book.getBookname()+"',"+book.getPrice()+")");
            statement.close();


        }catch(Exception e){
            e.printStackTrace();
        }

    }



}
