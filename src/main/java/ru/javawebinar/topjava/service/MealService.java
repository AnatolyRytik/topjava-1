package ru.javawebinar.topjava.service;

import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MealService {

    private MealRepository repository;


    public MealService(MealRepository repository) {
        this.repository = repository;
    }

    public Meal create(Meal Meal, int currentUserId) {
        return repository.save(Meal, currentUserId);
    }

    public void delete(int id, int currentUserId) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id, currentUserId), id);
    }

    public Meal get(int id, int currentUserId) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id, currentUserId), id);
    }

    public List<Meal> getAll(int currentUserId) {
        return repository.getAll(currentUserId);
    }

    public void update(Meal Meal, int currentUserId) {
        checkNotFoundWithId(repository.save(Meal, currentUserId), Meal.getId());
    }
}