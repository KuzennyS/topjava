package ru.javawebinar.topjava.web;

import org.slf4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.slf4j.LoggerFactory.getLogger;

public class addMeal extends HttpServlet {
    private static final Logger log = getLogger(UserServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("add meal");
//        String dateS = request.getParameter("date");
//        String descroption = request.getParameter("description");
//        Integer calories = Integer.valueOf(request.getParameter("calories"));
//
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
//        LocalDateTime dateTime = LocalDateTime.parse(dateS, formatter);
//        mealsG.add(new Meal(dateTime, descroption, calories));
////        doGet(request, response);
//        request.setAttribute("mealsToList", MealsUtil.getWithExcess(mealsG, new MealsUtil().userCaloriesPerDay));
//        request.getRequestDispatcher("/meals.jsp").forward(request, response);
        response.sendRedirect("meals.jsp");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("add meal Get");
    }
}
