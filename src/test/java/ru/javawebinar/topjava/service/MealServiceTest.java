package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import static ru.javawebinar.topjava.MealTestData.*;

import static org.junit.Assert.*;
import static ru.javawebinar.topjava.MealTestData.assertMatch;
import static ru.javawebinar.topjava.UserTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {

    static {
        // Only for postgres driver logging
        // It uses java.util.logging and logged via jul-to-slf4j bridge
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private MealService service;

    @Test
    public void get() {
        Meal meal = service.get(MEAL_ID, USER_ID);
        assertMatch(meal, USER_MEAL1);
    }

    @Test(expected = NotFoundException.class)
    public void getNotFound() {
        service.get(1, USER_ID);
    }

    @Test(expected = NotFoundException.class)
    public void getAnotherIdMeal() {
        service.get(USER_MEAL1.getId(), ADMIN_ID);
    }

    @Test
    public void delete() {
        service.delete(MEAL_ID, USER_ID);
        assertMatch(service.getAll(USER_ID), USER_MEAL7, USER_MEAL6, USER_MEAL5, USER_MEAL4, USER_MEAL3, USER_MEAL2);
    }

    @Test(expected = NotFoundException.class)
    public void deleteNotFound() {
        service.delete(MEAL_ID, USER_ID);
        service.get(MEAL_ID, USER_ID);
    }

    @Test(expected = NotFoundException.class)
    public void deleteAnotherIdMeal() {
        service.delete(USER_MEAL1.getId(), ADMIN_ID);
    }

    @Test
    public void getBetweenInclusive() {
        List<Meal> mealList = service.getBetweenInclusive(LocalDate.of(2020, Month.AUGUST, 24), LocalDate.of(2020, Month.AUGUST, 24), ADMIN_ID);
        assertMatch(mealList, ADMIN_MEAL2, ADMIN_MEAL1);
    }

    @Test
    public void getAll() {
        List<Meal> mealList = service.getAll(ADMIN_ID);
        assertMatch(mealList, ADMIN_MEAL2, ADMIN_MEAL1);
    }

    @Test
    public void update() {
        Meal updated = new Meal(USER_MEAL1);
        updated.setCalories(800);
        service.update(updated, USER_ID);
        assertMatch(service.get(USER_MEAL1.getId(), USER_ID), updated);
    }

    @Test(expected = NotFoundException.class)
    public void updateAnotherIdMeal() {
        Meal updated = new Meal(USER_MEAL1);
        updated.setDescription("Updated meal");
        service.update(updated, ADMIN_ID);
    }

    @Test
    public void create() {
        Meal newMeal = new Meal(null, LocalDateTime.of(2018, Month.AUGUST, 27, 14, 0), "New meal", 1000);
        Meal created = service.create(newMeal, ADMIN_ID);
        newMeal.setId(created.getId());
        assertMatch(service.getAll(ADMIN_ID), ADMIN_MEAL2, ADMIN_MEAL1, newMeal);
    }
}