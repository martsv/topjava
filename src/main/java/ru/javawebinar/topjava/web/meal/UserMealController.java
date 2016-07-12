package ru.javawebinar.topjava.web.meal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.util.TimeUtil;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Created by mart on 12.07.16.
 */
@Controller
public class UserMealController extends AbstractUserMealController {

    @RequestMapping(value = "/meals", method = RequestMethod.GET)
    public String mealList(Model model) {
        model.addAttribute("mealList", super.getAll());
        return "mealList";
    }

    @RequestMapping(value = "/meals/delete/{id}", method = RequestMethod.GET)
    public String deleteMeal(@PathVariable("id") int id) {
        super.delete(id);
        return "redirect:/meals";
    }

    @RequestMapping(value = "/meals/update/{id}", method = RequestMethod.GET)
    public String updateMeal(@PathVariable("id") int id, Model model) {
        final UserMeal meal = super.get(id);
        model.addAttribute("meal", meal);
        return "mealEdit";
    }

    @RequestMapping(value = "/meals/create", method = RequestMethod.GET)
    public String createMeal(Model model) {
        final UserMeal meal = new UserMeal(LocalDateTime.now().withNano(0).withSecond(0), "", 1000);
        model.addAttribute("meal", meal);
        return "mealEdit";
    }

    @RequestMapping(value = "/meals/filter", method = RequestMethod.POST)
    public String filterMeal(Model model, HttpServletRequest request) {
        LocalDate startDate = TimeUtil.parseLocalDate(resetParam("startDate", request));
        LocalDate endDate = TimeUtil.parseLocalDate(resetParam("endDate", request));
        LocalTime startTime = TimeUtil.parseLocalTime(resetParam("startTime", request));
        LocalTime endTime = TimeUtil.parseLocalTime(resetParam("endTime", request));
        model.addAttribute("mealList", super.getBetween(startDate, startTime, endDate, endTime));
        return "mealList";
    }

    @RequestMapping(value = "/meals", method = RequestMethod.POST)
    public String mealSave(HttpServletRequest request) {
        final UserMeal userMeal = new UserMeal(
                LocalDateTime.parse(request.getParameter("dateTime")),
                request.getParameter("description"),
                Integer.valueOf(request.getParameter("calories")));

        if (request.getParameter("id").isEmpty()) {
            super.create(userMeal);
        } else {
            super.update(userMeal, Integer.valueOf(request.getParameter("id")));
        }

        return "redirect:/meals";
    }

    private String resetParam(String param, HttpServletRequest request) {
        String value = request.getParameter(param);
        request.setAttribute(param, value);
        return value;
    }
}
