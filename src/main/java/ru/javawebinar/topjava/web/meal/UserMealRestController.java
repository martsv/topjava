package ru.javawebinar.topjava.web.meal;

import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.to.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * GKislin
 * 06.03.2015.
 */
@Controller
public class UserMealRestController extends AbstractUserMealController {

    public UserMeal get(int id) { return super.get(id); }

    public void delete(int id) { super.delete(id); }

    public List<UserMealWithExceed> getAll() { return super.getAll(); }

    public void update(UserMeal meal, int id) { super.update(meal, id); }

    public UserMeal create(UserMeal meal) { return super.create(meal); }

    public List<UserMealWithExceed> getBetween(LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime) {
        return super.getBetween(startDate, startTime, endDate, endTime);
    }
}