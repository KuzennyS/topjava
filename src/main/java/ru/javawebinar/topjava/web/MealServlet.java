package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.MealDaoIml;
import ru.javawebinar.topjava.model.Meal;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.slf4j.LoggerFactory.getLogger;
import static ru.javawebinar.topjava.memory.memoryMealDaoIml.*;
import static ru.javawebinar.topjava.util.MealsUtil.getWithExcess;


public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(UserServlet.class);
    private static String INSERT_OR_EDIT = "/meal.jsp";
    private static String LIST_Meal = "/meals.jsp";
    private MealDaoIml dao;

    public MealServlet() {
        super();
        dao = new MealDaoIml();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward = "";
        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("delete")) {
            log.debug("redirect to delete");
            int userId = Integer.parseInt(request.getParameter("userId"));
            dao.delete(userId);
            forward = LIST_Meal;
            request.setAttribute("mealsToList", getWithExcess(mealsG, userCaloriesPerDay));
        } else if (action.equalsIgnoreCase("edit")) {
            log.debug("redirect to edit");
            forward = INSERT_OR_EDIT;
            int userId = Integer.parseInt(request.getParameter("userId"));
            Meal meal = dao.getById(userId);
            request.setAttribute("meal", meal);
        } else if (action.equalsIgnoreCase("listMeal")) {
            log.debug("redirect to meals");
            forward = LIST_Meal;
            request.setAttribute("mealsToList", getWithExcess(mealsG, userCaloriesPerDay));
        } else {
            log.debug("goto to add");
            forward = INSERT_OR_EDIT;
        }
        RequestDispatcher view = request.getRequestDispatcher(forward);
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType ("text/html; charset=UTF-8");
        view.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("input to dopost");
        Meal meal = new Meal();
        LocalDateTime timeMeal = LocalDateTime.parse(request.getParameter("timeMeal"),dateFormatSite);
        meal.setDateTime(timeMeal);
        meal.setDescription(request.getParameter("description"));
        meal.setCalories(Integer.parseInt(request.getParameter("calories")));
        String mealid = request.getParameter("mealId");
        if (mealid == null || mealid.isEmpty()) {
            dao.add(meal);
        } else {
            meal.setId(Integer.parseInt(mealid));
            dao.edit(meal);
        }
        RequestDispatcher view = request.getRequestDispatcher(LIST_Meal);
        request.setAttribute("mealsToList", getWithExcess(mealsG, userCaloriesPerDay));
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType ("text/html; charset=UTF-8");
        view.forward(request, response);
    }
}
