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

import static org.slf4j.LoggerFactory.getLogger;
import static ru.javawebinar.topjava.memory.memoryMealDaoIml.mealsG;


public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(UserServlet.class);
    private static String INSERT_OR_EDIT = "/user.jsp";
    private static String LIST_Meal = "/meals.jsp";
    private MealDaoIml dao;

    public MealServlet() {
        super();
        dao = new MealDaoIml();
    }

//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        log.debug("redirect to meals");
//        request.setAttribute("mealsToList", MealsUtil.getWithExcess(mealsG, userCaloriesPerDay));
//        request.getRequestDispatcher("/meals.jsp").forward(request, response);
//    }

        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String forward = "";
            String action = request.getParameter("action");

            if (action.equalsIgnoreCase("delete")) {
                int userId = Integer.parseInt(request.getParameter("id"));
                dao.delete(userId);
                forward = LIST_Meal;
                request.setAttribute("mealsToList", mealsG);
            } else if (action.equalsIgnoreCase("edit")) {
                forward = INSERT_OR_EDIT;
                int userId = Integer.parseInt(request.getParameter("userId"));
                Meal meal = dao.getById(userId);
                request.setAttribute("meal", meal);
            } else if (action.equalsIgnoreCase("listMeal")) {
                forward = LIST_Meal;
                request.setAttribute("mealsToList", mealsG);
            } else {
                forward = INSERT_OR_EDIT;
            }

                RequestDispatcher view = request.getRequestDispatcher(forward);
                view.forward(request, response);
            }
        }


//    }
