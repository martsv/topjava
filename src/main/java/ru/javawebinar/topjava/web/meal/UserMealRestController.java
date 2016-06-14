package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.LoggedUser;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.service.UserMealService;
import ru.javawebinar.topjava.to.UserMealWithExceed;
import ru.javawebinar.topjava.util.UserMealsUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * GKislin
 * 06.03.2015.
 */
@Controller
public class UserMealRestController {
    protected final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserMealService service;

    public UserMeal get(int id) {
        LOG.info("get " + id);
        return service.get(id, LoggedUser.id());
    }

    public void delete(int id) {
        LOG.info("delete " + id);
        service.delete(id, LoggedUser.id());
    }

    public UserMeal create(UserMeal meal) {
        meal.setId(null);
        LOG.info("create " + meal);
        return service.save(meal, LoggedUser.id());
    }

    public void update(UserMeal meal, int id) {
        meal.setId(id);
        LOG.info("update " + meal);
        service.save(meal, LoggedUser.id());
    }

    public List<UserMealWithExceed> getAll() {
        LOG.info("getAll");
        return UserMealsUtil.getWithExceeded(service.getAll(LoggedUser.id()), UserMealsUtil.DEFAULT_CALORIES_PER_DAY);
    }

    public List<UserMealWithExceed> getByDateTime(LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime) {
        if (startDate == null)
            startDate = LocalDate.MIN;
        if (endDate == null)
            endDate = LocalDate.MAX;
        if (startTime == null)
            startTime = LocalTime.MIN;
        if (endTime == null)
            endTime = LocalTime.MAX;
        LOG.info("getByDateTime " + startDate + " " + endDate + " " + startTime + " " + endTime);
        return UserMealsUtil.getFilteredWithExceeded(
                service.getByDates(LoggedUser.id(), startDate, endDate),
                startTime,
                endTime,
                UserMealsUtil.DEFAULT_CALORIES_PER_DAY);
    }
}
