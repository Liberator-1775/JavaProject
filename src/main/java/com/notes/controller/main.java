package com.notes.controller;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.notes.model.DAO;
import com.notes.model.Note;
import com.notes.model.User;

@WebServlet("/main")
public class main extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public main() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
        Integer id = Integer.parseInt(request.getParameter("user_id"));
        String userName = request.getParameter("userName");
        String search = request.getParameter("textSearch");
        Integer imp = Integer.parseInt(request.getParameter("imp"));
        request.setAttribute("imp", imp);
        User user = new User();
        user.setId(id);
        user.setName(userName);
        request.setAttribute("user", user);
        request.setAttribute("search", search);
        request.getRequestDispatcher("view/main.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DAO dao = new DAO();
        String action = request.getParameter("action");
        if (action.contentEquals("add")) {
            String note = request.getParameter("note");
            Integer imp = Integer.parseInt(request.getParameter("imp"));
            Integer user_id = Integer.parseInt(request.getParameter("user_id"));
            String userName = request.getParameter("userName");
            dao.addNote(note, user_id, imp);
            User user = new User();
            user.setId(user_id);
            user.setName(userName);
            request.setAttribute("user", user);
            request.setAttribute("imp", 0);
            RequestDispatcher dispatcher = request.getRequestDispatcher("view/main.jsp");
            dispatcher.forward(request, response);
        } else if (action.contentEquals("del")) {
            Integer noteId = Integer.parseInt(request.getParameter("note_id"));
            Integer user_id = Integer.parseInt(request.getParameter("user_id"));
            String userName = request.getParameter("userName");
            dao.delNote(noteId);
            User user = new User();
            user.setId(user_id);
            user.setName(userName);
            request.setAttribute("user", user);
            request.setAttribute("imp", 0);
            RequestDispatcher dispatcher = request.getRequestDispatcher("view/main.jsp");
            dispatcher.forward(request, response);
        } else if (action.contentEquals("edit")) {
            String status = request.getParameter("status");
            Integer note_id = null;
            if (status.contentEquals("editing")) {
                note_id = Integer.parseInt(request.getParameter("note_id"));
                RequestDispatcher dispathcer = request.getRequestDispatcher("view/edit.jsp");
                dispathcer.forward(request, response);
            } else if (status.contentEquals("editado")) {
                String newText = request.getParameter("newText");
                Integer imp = Integer.parseInt(request.getParameter("imp"));
                note_id = Integer.parseInt(request.getParameter("note_id"));
                Note note = new Note();
                note.setId(note_id);
                note.setNote(newText);
                note.setImportance(imp);
                dao.editNote(note);
                doGet(request, response);
            }
        }
    }
}
