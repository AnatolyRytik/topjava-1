package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.MealDaoInternal;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);
    private MealDaoInternal mealDao;

    @Override
    public void init() throws ServletException {
        mealDao = new MealDaoInternal();
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NullPointerException {
        log.debug("redirect to meals");
        String action = request.getParameter("action");

        switch (action == null ? "" : action.toLowerCase()) {
            case "delete": {
                int mealId = Integer.parseInt(request.getParameter("mealsId"));
                log.debug("Delete meal id = {}", mealId);
                mealDao.delete(mealId);
                response.sendRedirect("meals");
                break;
            }
            case "update": {
                int mealId = Integer.parseInt(request.getParameter("mealsId"));
                Meal meal = mealDao.getById(mealId);
                log.debug("Edit meal id = {}", mealId);
                request.setAttribute("mealForCustomizing", meal);
                request.getRequestDispatcher("createMeal.jsp").forward(request, response);
                break;
            }
            case "add": {
                Meal meal = new Meal(LocalDateTime.now(), "", 1);
                request.setAttribute("mealForCustomizing", meal);
                request.getRequestDispatcher("createMeal.jsp").forward(request, response);
                break;
            }
            default: {
                request.setAttribute("meals", MealsUtil.filteredByStreams(mealDao.getAll(), LocalTime.MIN, LocalTime.MAX, 2000));
                request.getRequestDispatcher("meals.jsp").forward(request, response);
            }

        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to meals doPost");

        request.setCharacterEncoding("UTF-8");
        LocalDateTime dateTime = LocalDateTime.parse(request.getParameter("dateTime"));
        String description = request.getParameter("description");
        int calories = Integer.parseInt(request.getParameter("calories"));
        int mealId = Integer.parseInt(request.getParameter("mealsId"));

        if (mealId== 0) {
            mealDao.add(new Meal(dateTime, description, calories));
        } else {
            mealId = Integer.parseInt(request.getParameter("mealsId"));
            mealDao.update(new Meal(mealId, dateTime, description, calories));
        }

        request.setAttribute("meals", MealsUtil.filteredByStreams(mealDao.getAll(), LocalTime.MIN, LocalTime.MAX, 2000));
        request.getRequestDispatcher("meals.jsp").forward(request, response);
    }
}