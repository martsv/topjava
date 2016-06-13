package ru.javawebinar.topjava.repository.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;
import ru.javawebinar.topjava.util.TimeUtil;
import ru.javawebinar.topjava.util.UserMealsUtil;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * GKislin
 * 15.09.2015.
 */
@Repository
public class InMemoryUserMealRepositoryImpl implements UserMealRepository {
    private static final Logger LOG = LoggerFactory.getLogger(InMemoryUserMealRepositoryImpl.class);
    private Map<Integer, UserMeal> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        UserMealsUtil.MEAL_LIST.forEach(m -> save(m, 1));
    }

    @Override
    public UserMeal save(UserMeal userMeal, int userId) {
        if (userId != userMeal.getUserId()) {
            return null;
        }
        if (userMeal.isNew()) {
            userMeal.setId(counter.incrementAndGet());
        }
        repository.put(userMeal.getId(), userMeal);
        LOG.info("save " + userMeal);
        return userMeal;
    }

    @Override
    public boolean delete(int id, int userId) {
        UserMeal userMeal = get(id, userId);
        if (userMeal != null)
            repository.remove(id);
        LOG.info("delete " + id);
        return userMeal != null;
    }

    @Override
    public UserMeal get(int id, int userId) {
        UserMeal userMeal = repository.get(id);
        LOG.info("get " + id);
        if (userMeal == null || userMeal.getUserId() != userId)
            return null;
        return userMeal;
    }

    @Override
    public List<UserMeal> getByDates(int userId, LocalDate startDate, LocalDate endDate) {
        List<UserMeal> list = repository.values().stream()
                .filter(um -> um.getUserId() == userId)
                .filter(um -> TimeUtil.isDateBetween(um.getDateTime().toLocalDate(), startDate, endDate))
                .collect(Collectors.toList());
        Collections.sort(list, (o1, o2) -> o2.getDateTime().compareTo(o1.getDateTime()));
        LOG.info("getByDates");
        return list.isEmpty()? Collections.emptyList(): list;
    }

    @Override
    public List<UserMeal> getAll(int userId) {
        List<UserMeal> list = repository.values().stream()
                .filter(um -> um.getUserId() == userId)
                .collect(Collectors.toList());
        Collections.sort(list, (o1, o2) -> o2.getDateTime().compareTo(o1.getDateTime()));
        LOG.info("getAll");
        return list.isEmpty()? Collections.emptyList(): list;
    }
}

