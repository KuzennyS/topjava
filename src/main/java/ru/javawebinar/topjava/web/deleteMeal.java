package ru.javawebinar.topjava.web;

import org.slf4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.slf4j.LoggerFactory.getLogger;
import static ru.javawebinar.topjava.memory.memoryMealDaoIml.mealsG;

public class deleteMeal extends HttpServlet {
    private static final Logger log = getLogger(UserServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("delete meal");
        int id = Integer.valueOf(request.getParameter("id"));
        mealsG.removeIf(n -> n.getId() == id);

        response.sendRedirect("meals.jsp");

    }

}
