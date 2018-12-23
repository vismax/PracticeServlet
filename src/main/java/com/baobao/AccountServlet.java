package com.baobao;
import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.Buffer;

@Deprecated
public class AccountServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    super.doGet(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    super.doPost(req, resp);



    BufferedReader reader = req.getReader();
    String json= reader.readLine();
    System.out.println(json);
    reader.close();

    resp.getWriter().print("hello world");
    resp.getWriter().flush();
    resp.getWriter().close();

  }
}
