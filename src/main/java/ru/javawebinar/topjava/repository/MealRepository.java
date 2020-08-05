package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.util.Collection;
import java.util.Comparator;

public interface MealRepository {
    // null if not found, when updated
    Meal save(Meal meal, int currentUserId);

    // false if not found
    boolean delete(int mealId, int currentUserId);

    // null if not found
    Meal get(int mealId, int currentUserId);

    Collection<Meal> getAll(int currentUserId);
}
