package com.baobao;

import com.baobao.model.Account;
import com.baobao.repository.AccountRepository;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.List;

public class QueryAccountServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            String username = (String)req.getAttribute("username");

            List<Account> list = AccountRepository.queryAccount(username);



            String result = JsonUtil.toJson(list);



            resp.getWriter().print(result);
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
