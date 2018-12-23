package com.baobao;

import com.baobao.model.Enter;
import com.baobao.repository.EnterRepository;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class LoginServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        doPost(req,res);
    }
    protected void doPost(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException {
        ResultSet resultSet =null;
        try {
                BufferedReader reader = req.getReader();
                String str = reader.readLine();
                reader.close();
                String username1;
                String password1;
                String[] result = str.split("&");
                username1 = result[0].split("=")[1];
                password1 = result[1].split("=")[1];
                resultSet = EnterRepository.queryEnter(username1);

                if (resultSet.next()) {
                    if (password1.equals(resultSet.getString("password"))) {
                        Cookie nameCookie = new Cookie("username", username1);
                        nameCookie.setMaxAge(60*60*24);
                        nameCookie.setDomain("");
                        nameCookie.setPath("/");
                        res.addCookie(nameCookie);

                        Cookie passwordCookie = new Cookie("password", password1);
                        passwordCookie.setMaxAge(60*60*24);
                        passwordCookie.setDomain("");
                        passwordCookie.setPath("/");
                        res.addCookie(passwordCookie);



                        res.getWriter().print("登录成功！！！");
                        res.getWriter().flush();
                        res.getWriter().close();
                    } else {
                        res.getWriter().print("登录失败,密码错误");
                        res.getWriter().flush();
                        res.getWriter().close();
                    }
                }else{
                    res.getWriter().print("此用户名不存在");
                    res.getWriter().flush();
                    res.getWriter().close();
                }

            }catch (SQLException e){
                e.printStackTrace();
            }finally {
                if(resultSet != null){
                    try {
                        resultSet.close();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }

    }



}
