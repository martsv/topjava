package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.UserMeal;

import java.time.LocalDate;
import java.util.List;

/**
 * GKislin
 * 06.03.2015.
 */
public interface UserMealRepository {
    UserMeal save(UserMeal userMeal, int userId);

    boolean delete(int id, int userId);

    UserMeal get(int id, int userId);

    List<UserMeal> getByDates(int userId, LocalDate startDate, LocalDate endDate);

    List<UserMeal> getAll(int userId);
}
