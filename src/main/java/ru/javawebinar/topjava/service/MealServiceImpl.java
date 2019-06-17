package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.TO.MealTo;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MealServiceImpl implements MealService {

    @Autowired
    private MealRepository repositoryMeal;

    @Override
    public Meal save(Meal meal) {
        return checkNotFoundWithId(repositoryMeal.save(meal), meal.getId());
    }

    @Override
    public void delete(int id, int userId) throws NotFoundException {
        checkNotFoundWithId(repositoryMeal.delete(id, userId), id);
    }

    @Override
    public Meal get(int id, int userId) throws NotFoundException {
        return checkNotFoundWithId(repositoryMeal.get(id, userId), id);
    }

    @Override
    public List<MealTo> getAll() {
        return repositoryMeal.getAll();
    }

    @Override
    public List<MealTo> getAllbyUser(int userId) {
        return checkNotFoundWithId(repositoryMeal.getAllbyUser(userId), userId);
    }

    @Override
    public List<MealTo> getFilterDT(LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime) {
        return repositoryMeal.getFilterDT(startDate, startTime, endDate, endTime);
    }
}