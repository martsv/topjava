package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.UserMeal;

import java.util.List;

/**
 * Created by mart on 07.06.16.
 */
public interface UserMealDao {
    void add(UserMeal meal);
    void update(UserMeal meal);
    List<UserMeal> findAll();
    void delete(long id);
    UserMeal get(long id);
}
