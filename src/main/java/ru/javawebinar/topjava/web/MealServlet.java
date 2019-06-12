package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.memory.MealDao;
import ru.javawebinar.topjava.model.Meal;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.slf4j.LoggerFactory.getLogger;
import static ru.javawebinar.topjava.util.MealsUtil.getUserCalories;
import static ru.javawebinar.topjava.util.MealsUtil.getWithExcess;


public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(UserServlet.class);
    private static String INSERT_OR_EDIT = "/meal.jsp";
    private static String LIST_Meal = "/meals.jsp";
    private MealDao dao;
    public static DateTimeFormatter dateFormatSite = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public MealServlet() {
        super();
        dao = new MealDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        String forward = "";
        String action = request.getParameter("action");
        int mealId = 0;

        switch (action) {
            case "delete":
                log.debug("redirect to delete");
                mealId = Integer.parseInt(request.getParameter("mealId"));
                dao.delete(mealId);
                forward = LIST_Meal;
                request.setAttribute("mealsToList", getWithExcess(dao.getAll(), getUserCalories()));
                break;
            case "edit":
                log.debug("redirect to edit");
                forward = INSERT_OR_EDIT;
                mealId = Integer.parseInt(request.getParameter("mealId"));
                Meal meal = dao.getById(mealId);
                request.setAttribute("meal", meal);
                request.setAttribute("mealId", mealId);
                break;
            case "listMeal":
                log.debug("redirect to meals");
                forward = LIST_Meal;
                request.setAttribute("mealsToList", getWithExcess(dao.getAll(), getUserCalories()));
                break;
            default:
                log.debug("goto to add");
                forward = INSERT_OR_EDIT;
                break;
        }
        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        log.debug("input to dopost");
        Meal meal = new Meal();
        LocalDateTime timeMeal = LocalDateTime.parse(request.getParameter("timeMeal"), dateFormatSite);
        meal.setDateTime(timeMeal);
        meal.setDescription(request.getParameter("description"));
        meal.setCalories(Integer.parseInt(request.getParameter("calories")));
        String idMealS = request.getParameter("idMeal").trim();
        String idMeal = idMealS.replaceAll("[^\\d+]", "");
        if (idMeal.isEmpty() || idMeal == null)
            dao.add(meal);
        else
            dao.edit(Integer.parseInt(idMeal), meal);
        RequestDispatcher view = request.getRequestDispatcher(LIST_Meal);
        request.setAttribute("mealsToList", getWithExcess(dao.getAll(), getUserCalories()));
        view.forward(request, response);
    }
}
