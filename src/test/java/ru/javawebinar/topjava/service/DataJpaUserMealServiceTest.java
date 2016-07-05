package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.model.UserMeal;

import static ru.javawebinar.topjava.MealTestData.ADMIN_MEAL_ID;
import static ru.javawebinar.topjava.UserTestData.*;

/**
 * Created by mart on 05.07.16.
 */
@ActiveProfiles({Profiles.ACTIVE_DB,Profiles.DATAJPA})
public class DataJpaUserMealServiceTest extends UserMealServiceTest {
    @Test
    public void testGetWithUser() throws Exception {
        UserMeal userMeal = service.getWithUser(ADMIN_MEAL_ID, ADMIN_ID);
        MATCHER.assertEquals(ADMIN, userMeal.getUser());
    }
}
