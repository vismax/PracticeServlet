package com.baobao;

import com.baobao.model.Account;
import com.baobao.repository.AccountRepository;
import org.apache.commons.io.FileUtils;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.URLDecoder;

public class InputServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            BufferedReader reader = req.getReader();
            String str = reader.readLine();
//        System.out.println(json);
            reader.close();

            String time;
            String items;
            String meney;

            String[] result = str.split("&");
            time = result[0].split("=")[1];
            items = result[1].split("=")[1];
            meney = result[2].split("=")[1];


            Account account = new Account(0, URLDecoder.decode(time), items, Integer.parseInt(meney), (String)req.getAttribute("username"));

            AccountRepository.insertAccount(account);



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
