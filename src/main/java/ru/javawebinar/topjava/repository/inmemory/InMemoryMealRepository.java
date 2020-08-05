package ru.javawebinar.topjava.repository.inmemory;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class InMemoryMealRepository implements MealRepository {
    private Map<Integer, Map<Integer, Meal>> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.MEALS.forEach(m -> save(m, SecurityUtil.authUserId()));
    }

    @Override
    public Meal save(Meal meal, int currentUserId) {
        repository.putIfAbsent(currentUserId, new ConcurrentHashMap<>());
        Map<Integer, Meal> mealMap = repository.get(currentUserId);
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            mealMap.put(meal.getId(), meal);
            return meal;
        }
        // handle case: update, but not present in storage
        return mealMap.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
    }

    @Override
    public boolean delete(int mealId, int currentUserId) {
        return repository.get(currentUserId) != null && repository.get(currentUserId).remove(mealId) != null;
    }

    @Override
    public Meal get(int mealId, int currentUserId) {
        return repository.get(currentUserId) != null ? repository.get(currentUserId).get(mealId) : null;
    }

    @Override
    public Collection<Meal> getAll(int currentUserId) {
        if (currentUserId != 0) {
            return repository.get(currentUserId).values()
                    .stream()
                    .sorted((m1, m2) -> m2.getDateTime().compareTo(m1.getDateTime()))
                    .collect(Collectors.toList());
        } else return Collections.emptyList();
    }
}

