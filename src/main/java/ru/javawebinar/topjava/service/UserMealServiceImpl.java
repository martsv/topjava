package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;
import ru.javawebinar.topjava.util.exception.ExceptionUtil;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.List;

/**
 * GKislin
 * 06.03.2015.
 */
@Service
public class UserMealServiceImpl implements UserMealService {

    @Autowired
    private UserMealRepository repository;

    @Override
    public UserMeal save(UserMeal meal, int userId) throws NotFoundException {
        return ExceptionUtil.checkNotFoundWithId(repository.save(meal, userId), meal.getId());
    }

    @Override
    public void delete(int id, int userId) throws NotFoundException {
        ExceptionUtil.checkNotFoundWithId(repository.delete(id, userId), id);
    }

    @Override
    public UserMeal get(int id, int userId) throws NotFoundException {
        return ExceptionUtil.checkNotFoundWithId(repository.get(id, userId), id);
    }

    @Override
    public List<UserMeal> getByDates(int userId, LocalDate startDate, LocalDate endDate) {
        return repository.getByDates(userId, startDate, endDate);
    }

    @Override
    public List<UserMeal> getAll(int userId) {
        return repository.getAll(userId);
    }
}
