package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MealDaoInternal implements MealDAO {
    private final AtomicInteger autoId = new AtomicInteger(0);
    private Map<Integer, Meal> mealMap = new ConcurrentHashMap<>();

    {
        add(new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500));
        add(new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000));
        add(new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500));
        add(new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100));
        add(new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000));
        add(new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500));
        add(new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410));
    }

    @Override
    public List<Meal> getAll() {
        return new ArrayList<>(mealMap.values());
    }

    @Override
    public void add(Meal meal) {
        if (meal.getId() == 0) {
            meal.setId(autoId.incrementAndGet());
            mealMap.put(meal.getId(), meal);
        }
    }

    @Override
    public void delete(int id) {
        mealMap.remove(id);
    }

    @Override
    public void update(Meal meal) {
        mealMap.computeIfPresent(meal.getId(), (key, value) ->meal);
    }

    @Override
    public Meal getById(int id) {
        return mealMap.get(id);
    }
}
