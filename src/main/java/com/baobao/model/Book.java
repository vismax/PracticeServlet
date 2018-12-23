package com.baobao.model;

public class Book {
    private int id;
    private String bookname;
    private Integer price;
    public Book(){
    }
    public Book(int id, String bookname, Integer price){
        this.id=id;
        this.bookname=bookname;
        this.price=price;
    }
    public int getId(){return id;}
    public void setId(int id){this.id=id;}
    public String getBookname(){return bookname;}
    public void setBookname(String bookname){this.bookname= bookname;}
    public Integer getPrice(){return price;}
    public void setPrice(Integer price){this.price= price;}

}
