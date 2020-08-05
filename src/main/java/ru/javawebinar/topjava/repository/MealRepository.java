package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.util.Collection;
import java.util.Comparator;

public interface MealRepository {
    // null if not found, when updated
    Meal save(Meal meal, Integer currentUserId);

    // false if not found
    boolean delete(int mealId, Integer currentUserId);

    // null if not found
    Meal get(int mealId, Integer currentUserId);

    Collection<Meal> getAll(Integer currentUserId);
}
