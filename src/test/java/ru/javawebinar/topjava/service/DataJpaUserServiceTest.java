package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.model.User;

import static ru.javawebinar.topjava.UserTestData.USER_ID;
import static ru.javawebinar.topjava.MealTestData.USER_MEALS;
import static ru.javawebinar.topjava.MealTestData.MATCHER;

/**
 * Created by mart on 05.07.16.
 */
@ActiveProfiles({Profiles.ACTIVE_DB,Profiles.DATAJPA})
public class DataJpaUserServiceTest extends UserServiceTest {
    @Test
    public void testGetWithMeals() throws Exception {
        User user = service.getWithMeals(USER_ID);
        MATCHER.assertCollectionEquals(USER_MEALS, user.getMeals());
    }
}
