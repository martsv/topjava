package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.UserMealDao;
import ru.javawebinar.topjava.dao.UserMealDaoMemory;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;
import ru.javawebinar.topjava.util.DateUtil;
import ru.javawebinar.topjava.util.UserMealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by mart on 05.06.16.
 */
public class MealServlet extends HttpServlet {
    private static final Logger LOG = getLogger(MealServlet.class);
    private static UserMealDao userMealDao = new UserMealDaoMemory();
    private static String ADD_OR_EDIT = "/meal.jsp";
    private static String MEAL_LIST = "/mealList.jsp";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward = "";
        String action = request.getParameter("action");

        if (action == null || "".equalsIgnoreCase(action)) {
            forward = MEAL_LIST;
            List<UserMealWithExceed> mealWithExceedList =
                    UserMealsUtil.getWithExceeded(userMealDao.findAll(), 2000);
            request.setAttribute("mealList", mealWithExceedList);
            LOG.debug("forward to mealList");
        } else if ("delete".equalsIgnoreCase(action)) {
            long id = Long.parseLong(request.getParameter("id"));
            userMealDao.delete(id);
            forward = MEAL_LIST;
            List<UserMealWithExceed> mealWithExceedList =
                    UserMealsUtil.getWithExceeded(userMealDao.findAll(), 2000);
            request.setAttribute("mealList", mealWithExceedList);
            LOG.debug("delete meal with id " + id);
        } else if ("edit".equalsIgnoreCase(action)) {
            forward = ADD_OR_EDIT;
            long id = Long.parseLong(request.getParameter("id"));
            UserMeal meal = userMealDao.get(id);
            request.setAttribute("meal", meal);
        } else if ("add".equalsIgnoreCase(action)) {
            forward = ADD_OR_EDIT;
        }

        request.getRequestDispatcher(forward).forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        UserMeal meal = new UserMeal();

        meal.setDescription(request.getParameter("description"));
        try {
            int calories = Integer.parseInt(request.getParameter("calories"));
            meal.setCalories(calories);
        } catch (NumberFormatException e) {
            meal.setCalories(0);
        }
        try {
            LocalDateTime dateTime = DateUtil.parseDateTime(request.getParameter("dateTime"));
            meal.setDateTime(dateTime);
        } catch (DateTimeParseException e) {
            meal.setDateTime(LocalDateTime.now());
        }

        String id = request.getParameter("id");
        if (id == null || id.isEmpty()) {
            userMealDao.add(meal);
        } else {
            meal.setId(Long.parseLong(id));
            userMealDao.update(meal);
        }

        List<UserMealWithExceed> mealWithExceedList =
                UserMealsUtil.getWithExceeded(userMealDao.findAll(), 2000);
        request.setAttribute("mealList", mealWithExceedList);
        request.getRequestDispatcher(MEAL_LIST).forward(request, response);
    }
}
