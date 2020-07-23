package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.MealDaoImpl;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.time.LocalTime;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);
    private MealDaoImpl mealDao = new MealDaoImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NullPointerException {
        log.debug("redirect to meals");
        String action = request.getParameter("action");

        switch (action == null ? "" : action.toLowerCase()) {
            case "delete": {
                int mealId = Integer.parseInt(request.getParameter("userId"));
                Meal meal = mealDao.getById(mealId);
                mealDao.delete(meal);
                response.sendRedirect("meals");
                break;
            }
            case "edit": {
                int userId = Integer.parseInt(request.getParameter("userId"));
                Meal meal = mealDao.getById(userId);
                request.setAttribute("meal", meal);
                request.getRequestDispatcher("editMeal.jsp").forward(request, response);
                break;
            }
            case "add": {
                request.getRequestDispatcher("createMeal.jsp").forward(request, response);
                break;
            }
            default: {
                request.setAttribute("meals", MealsUtil.filteredByStreams(mealDao.allMeals(), LocalTime.MIN, LocalTime.MAX, 2000));
                request.getRequestDispatcher("meals.jsp").forward(request, response);
            }

        }
    }


}

