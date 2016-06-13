package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.List;

/**
 * GKislin
 * 15.06.2015.
 */
public interface UserMealService {
    UserMeal save(UserMeal meal, int userId) throws NotFoundException;

    void delete(int id, int userId) throws NotFoundException;

    UserMeal get(int id, int userId) throws NotFoundException;

    List<UserMeal> getByDates(int userId, LocalDate startDate, LocalDate endDate);

    List<UserMeal> getAll(int userId);
}
