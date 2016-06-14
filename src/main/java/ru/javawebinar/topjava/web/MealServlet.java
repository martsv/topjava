package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.LoggedUser;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.web.meal.UserMealRestController;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Objects;

/**
 * User: gkislin
 * Date: 19.08.2014
 */
public class MealServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(MealServlet.class);

    private ConfigurableApplicationContext appCtx = null;
    private UserMealRestController userMealRestController;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml");
        userMealRestController = appCtx.getBean(UserMealRestController.class);
    }

    @Override
    public void destroy() {
        super.destroy();
        if (appCtx != null)
            appCtx.close();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        if (request.getServletPath().equals("/editmeal")) {
            String id = request.getParameter("id");
            UserMeal userMeal = new UserMeal(id.isEmpty() ? null : Integer.valueOf(id),
                    LocalDateTime.parse(request.getParameter("dateTime")),
                    request.getParameter("description"),
                    Integer.valueOf(request.getParameter("calories")),
                    LoggedUser.id());
            LOG.info(userMeal.isNew() ? "Create {}" : "Update {}", userMeal);
            if (userMeal.isNew()) {
                userMealRestController.create(userMeal);
            } else {
                userMealRestController.update(userMeal, userMeal.getId());
            }
            response.sendRedirect("meals");
        } else {
            LocalDate startDate = null;
            LocalDate endDate = null;
            LocalTime startTime = null;
            LocalTime endTime = null;
            try {
                startDate = LocalDate.parse(request.getParameter("startDate"));
            } catch (DateTimeParseException e) {}
            try {
                endDate = LocalDate.parse(request.getParameter("endDate"));
            } catch (DateTimeParseException e) {}
            try {
                startTime = LocalTime.parse(request.getParameter("startTime"));
            } catch (DateTimeParseException e) {}
            try {
                endTime = LocalTime.parse(request.getParameter("endTime"));
            } catch (DateTimeParseException e) {}
            LOG.info("getByDateTime");
            request.setAttribute("mealList",
                    userMealRestController.getByDateTime(startDate, endDate, startTime, endTime));
            request.getRequestDispatcher("/mealList.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            LOG.info("getAll");
            request.setAttribute("mealList",
                    userMealRestController.getAll());
            request.getRequestDispatcher("/mealList.jsp").forward(request, response);
        } else if (action.equals("delete")) {
            int id = getId(request);
            LOG.info("Delete {}", id);
            userMealRestController.delete(id);
            response.sendRedirect("meals");
        } else if (action.equals("create") || action.equals("update")) {
            final UserMeal meal = action.equals("create") ?
                    new UserMeal(LocalDateTime.now().withNano(0).withSecond(0), "", 1000, LoggedUser.id()) :
                    userMealRestController.get(getId(request));
            request.setAttribute("meal", meal);
            request.getRequestDispatcher("mealEdit.jsp").forward(request, response);
        }
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.valueOf(paramId);
    }
}
