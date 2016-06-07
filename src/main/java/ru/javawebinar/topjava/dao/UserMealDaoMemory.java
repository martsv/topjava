package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.UserMeal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by mart on 07.06.16.
 */
public class UserMealDaoMemory implements UserMealDao {
    private Map<Long, UserMeal> userMealMap;
    private AtomicLong id = new AtomicLong(0);

    public UserMealDaoMemory() {
        this.userMealMap = new ConcurrentHashMap<>();

        add(new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500));
        add(new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000));
        add(new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500));
        add(new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000));
        add(new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500));
        add(new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510));
    }

    @Override
    public void add(UserMeal meal) {
        meal.setId(id.incrementAndGet());
        userMealMap.put(meal.getId(), meal);
    }

    @Override
    public void update(UserMeal meal) {
        userMealMap.put(meal.getId(), meal);
    }

    @Override
    public List<UserMeal> findAll() {
        return new ArrayList<>(userMealMap.values());
    }

    @Override
    public void delete(long id) {
        if (userMealMap.containsKey(id))
            userMealMap.remove(id);
    }

    @Override
    public UserMeal get(long id) {
        return userMealMap.get(id);
    }
}
