package com.baobao.model;

public class Enter {
    private String username;
    private String password;
    public Enter(){
    }
    public Enter(String username,String password){
        this.password= password;
        this.username= username;
    }

    public String getUsername(){return username;}
    public void setUsername(String username){this.username=username;}
    public String getPassword(){return password;}
    public void setPassword(String password){this.password=password;}

}
