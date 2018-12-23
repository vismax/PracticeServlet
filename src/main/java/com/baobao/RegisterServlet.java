package com.baobao;

import com.baobao.model.Enter;
import com.baobao.repository.EnterRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.URLDecoder;

public class RegisterServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            BufferedReader reader = req.getReader();
            String str = reader.readLine();
            reader.close();

            String username;
            String password;

            String[] result = str.split("&");
            username = result[0].split("=")[1];
            password = result[1].split("=")[1];

            Enter enter = new Enter(URLDecoder.decode(username), password);
            EnterRepository.insertEnter(enter);



            resp.getWriter().print("success");
            resp.getWriter().print("success");
            resp.getWriter().print("success");

            resp.getWriter().flush();
            resp.getWriter().close();
        }catch (Exception e){
            e.printStackTrace();
            resp.getWriter().print("failed");
            resp.getWriter().flush();
            resp.getWriter().close();
        }




        }



}


