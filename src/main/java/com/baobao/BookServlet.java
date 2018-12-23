package com.baobao;
import com.baobao.model.Account;
import com.baobao.model.Book;
import com.baobao.repository.AccountRepository;
import com.baobao.repository.BookRepository;
import org.apache.commons.io.FileUtils;
import sun.awt.AWTCharset;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.URLDecoder;
public class BookServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            BufferedReader reader = req.getReader();
            String str = reader.readLine();
//            System.out.println(json);
            reader.close();
            String bookname;
            String price;
            String[] result = str.split("&");
            bookname = result[0].split("=")[1];
            price = result[1].split("=")[1];

            Book book = new Book(0, URLDecoder.decode(bookname),Integer.parseInt(price));
            BookRepository.insertBook(book);
            resp.getWriter().print("success");
            resp.getWriter().flush();
            resp.getWriter().close();

    }catch(Exception e){
            e.printStackTrace();
            resp.getWriter().print("failed");
            resp.getWriter().flush();
            resp.getWriter().close();
        }


        }

}
