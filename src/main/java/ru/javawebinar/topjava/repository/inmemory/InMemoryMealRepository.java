package ru.javawebinar.topjava.repository.inmemory;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.ValidationUtil;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepository implements MealRepository {
    private Map<Integer, Map<Integer, Meal>> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.MEALS.forEach(m -> save(m, 1));
    }

    @Override
    public Meal save(Meal meal, Integer currentUserId) {
        Map<Integer, Meal> mealMap = repository.computeIfAbsent(currentUserId, k -> new ConcurrentHashMap<>());
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            mealMap.put(meal.getId(), meal);
            return meal;
        }
        // handle case: update, but not present in storage
        return mealMap.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
    }

    @Override
    public boolean delete(int mealId, Integer currentUserId) {
        Map<Integer, Meal> mealMap = repository.get(currentUserId);
        return mealMap != null && mealMap.remove(mealId) != null;
    }

    @Override
    public Meal get(int mealId, Integer currentUserId) {
        Map<Integer, Meal> mealMap = repository.get(currentUserId);
        return mealMap != null ? mealMap.get(mealId) : null;
    }

    @Override
    public List<Meal> getAll(Integer currentUserId){
        return  repository.get(currentUserId) != null ? repository.get(currentUserId).values()
                .stream()
                .sorted((m1, m2) -> m2.getDateTime().compareTo(m1.getDateTime()))
                .collect(Collectors.toList())
                : Collections.emptyList();
    }
}

