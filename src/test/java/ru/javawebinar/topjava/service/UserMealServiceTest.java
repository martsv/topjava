package ru.javawebinar.topjava.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.util.DbPopulator;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static ru.javawebinar.topjava.MealTestData.*;

/**
 * Created by mart on 20.06.16.
 */
@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class UserMealServiceTest {

    @Autowired
    protected UserMealService service;

    @Autowired
    private DbPopulator dbPopulator;

    @Before
    public void setUp() throws Exception {
        dbPopulator.execute();
    }

    @Test
    public void testGet() throws Exception {
        UserMeal userMeal = service.get(USER_MEAL_6_ID, USER_ID);
        MATCHER.assertEquals(USER_MEAL_6, userMeal);
    }

    @Test(expected = NotFoundException.class)
    public void testGetNotFound() throws Exception {
        service.get(ADMIN_MEAL_1_ID, USER_ID);
    }

    @Test
    public void testDelete() throws Exception {
        service.delete(ADMIN_MEAL_1_ID, ADMIN_ID);
        MATCHER.assertCollectionEquals(Collections.singletonList(ADMIN_MEAL_2), service.getAll(ADMIN_ID));
    }

    @Test(expected = NotFoundException.class)
    public void testDeleteNotFound() throws Exception {
        service.delete(USER_MEAL_1_ID, ADMIN_ID);
    }

    @Test
    public void testGetBetweenDates() throws Exception {
        Collection<UserMeal> betweenDates = service.getBetweenDates(LocalDate.of(2015,5,31), LocalDate.of(2015,5,31), USER_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(USER_MEAL_6, USER_MEAL_5, USER_MEAL_4), betweenDates);
    }

    @Test
    public void testGetBetweenDateTimes() throws Exception {
        Collection<UserMeal> betweenDateTimes = service.getBetweenDateTimes(LocalDateTime.of(2015,5,30,13,0,0), LocalDateTime.of(2015,5,30,20,0,0), USER_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(USER_MEAL_3, USER_MEAL_2), betweenDateTimes);
    }

    @Test
    public void testGetAll() throws Exception {
        Collection<UserMeal> all = service.getAll(USER_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(USER_MEAL_6, USER_MEAL_5, USER_MEAL_4, USER_MEAL_3, USER_MEAL_2, USER_MEAL_1), all);
    }

    @Test
    public void testUpdate() throws Exception {
        UserMeal updated = new UserMeal(ADMIN_MEAL_1_ID, LocalDateTime.of(2015,6,1,14,0,0), "Админ ланч", 510);
        updated.setDateTime(LocalDateTime.of(2000,10,10,11,0,0));
        updated.setDescription("UpdatedDescription");
        updated.setCalories(600);
        service.update(updated, ADMIN_ID);
        MATCHER.assertEquals(updated, service.get(ADMIN_MEAL_1_ID, ADMIN_ID));
    }

    @Test(expected = NotFoundException.class)
    public void testUpdateNotFound() throws Exception {
        UserMeal updated = new UserMeal(ADMIN_MEAL_1_ID, LocalDateTime.of(2015,6,1,14,0,0), "Админ ланч", 510);
        updated.setDescription("UpdatedDescription");
        service.update(updated, USER_ID);
    }

    @Test
    public void testSave() throws Exception {
        UserMeal newMeal = new UserMeal(null, LocalDateTime.of(2015,6,1,17,0,0), "Админ полдник", 500);
        UserMeal created = service.save(newMeal, ADMIN_ID);
        newMeal.setId(created.getId());
        MATCHER.assertCollectionEquals(Arrays.asList(ADMIN_MEAL_2, newMeal, ADMIN_MEAL_1), service.getAll(ADMIN_ID));
    }

}