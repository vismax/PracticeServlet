package com.baobao.filters;


import com.baobao.repository.EnterRepository;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Set;

public class FilterLogin implements Filter {

    private Set<String> excludeSet = new HashSet<String>();



    public void init(FilterConfig filterConfig) throws ServletException {
        String exclude = filterConfig.getInitParameter("exclude");
        if(exclude != null){
            String[] excludes = exclude.split(",");
            for(String temp : excludes){

                excludeSet.add(temp);
            }
        }
        System.out.println();
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {

            if (excludeSet.contains(((HttpServletRequest) servletRequest).getRequestURI())) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                Cookie[] cookies = ((HttpServletRequest) servletRequest).getCookies();
                String username = null;
                String password = null;
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("username")) {
                        username = cookie.getValue();
                    } else if (cookie.getName().equals("password")) {
                        password = cookie.getValue();
                    }
                }
                if (username == null || password == null) {
                    printNoPermission(servletResponse);
                    return;
                } else {
                    ResultSet resultSet = EnterRepository.queryEnter(username);
                    if(resultSet.next()){
                        if (password.equals(resultSet.getString("password"))) {
                            ((HttpServletRequest) servletRequest).setAttribute("username",username);

                            filterChain.doFilter(servletRequest, servletResponse);
                        } else {
                            printNoPermission(servletResponse);

                            return;


                        }

                    }else{
                        printNoPermission(servletResponse);

                        return;

                    }


                }


            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private void printNoPermission(ServletResponse servletResponse) throws Exception{
        servletResponse.getWriter().print("no permission");
        ((HttpServletResponse)servletResponse).sendRedirect("http://localhost:8080/pages/Enter.html");
        servletResponse.getWriter().flush();
        servletResponse.getWriter().close();
    }



    public void destroy(){
            System.out.println("过滤器销毁");
    }
}
