package com.jsp_servlet_base.controller.web;

import com.jsp_servlet_base_model.UserModel;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HomeController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        UserModel userModel = new UserModel();
        userModel.setFullName("Hello World");
        request.setAttribute("model",userModel);

        RequestDispatcher dp = request.getRequestDispatcher("/views/web/home.jsp");
        try {
            dp.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }
}
