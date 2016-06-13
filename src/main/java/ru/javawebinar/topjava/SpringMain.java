package ru.javawebinar.topjava;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.web.meal.UserMealRestController;
import ru.javawebinar.topjava.web.user.AdminRestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;

/**
 * User: gkislin
 * Date: 22.08.2014
 */
public class SpringMain {
    public static void main(String[] args) {
        // java 7 Automatic resource management
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml")) {
            System.out.println(Arrays.toString(appCtx.getBeanDefinitionNames()));
            System.out.println();

            AdminRestController adminUserController = appCtx.getBean(AdminRestController.class);
            System.out.println(adminUserController.create(new User(1, "userName", "email", "password", Role.ROLE_ADMIN)));
            System.out.println();

            UserMealRestController userMealRestController = appCtx.getBean(UserMealRestController.class);
            System.out.println(userMealRestController.create(new UserMeal(LocalDateTime.now(), "Десерт", 500, 1)));
            System.out.println(userMealRestController.getByDateTime(LocalDate.of(2015,5,30), LocalDate.of(2015,5,30), LocalTime.MIN, LocalTime.MAX));
            System.out.println(userMealRestController.getByDateTime(LocalDate.MIN, LocalDate.MAX, LocalTime.of(9,0), LocalTime.of(12,0)));
            System.out.println(userMealRestController.getByDateTime(LocalDate.of(2015,5,31), LocalDate.of(2015,5,31), LocalTime.of(9,0), LocalTime.of(12,0)));
            System.out.println(userMealRestController.getByDateTime(null, null, null, null));
            System.out.println();
        }
    }
}
