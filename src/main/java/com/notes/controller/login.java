package com.notes.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.notes.model.DAO;
import com.notes.model.User;

@WebServlet("/login")
public class login extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public login() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
        String name = request.getParameter("name");
        String password = request.getParameter("passwd");
        DAO dao = new DAO();
        List<User> users = dao.getUsers();
        boolean isUser = false;
        User newUser = null;
        for (User user : users) {
            if (user.getName().contentEquals(name) && user.getPasswd().contentEquals(password)) {
                isUser = true;
                newUser = user;
                break;
            }
        }
        if (isUser) {
            request.setAttribute("user", newUser);
            request.setAttribute("imp", 0);
            request.setAttribute("action", "orders");
            request.getRequestDispatcher("view/main.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("view/index.jsp").forward(request, response);
        }
    }
}
