package com.baobao.model;

public class Account {

    private int id;

    private String time;
    private String item;
    private Integer money;
    private String userName;


    public Account() {
    }

    public Account(int id, String time, String item, Integer money, String userName) {
        this.id = id;
        this.time = time;
        this.item = item;
        this.money = money;
        this.userName = userName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
