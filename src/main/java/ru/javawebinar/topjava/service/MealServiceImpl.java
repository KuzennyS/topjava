package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MealServiceImpl implements MealService {

    private final MealRepository repositoryMeal;

    @Autowired
    public MealServiceImpl(MealRepository repositoryMeal) {
        this.repositoryMeal = repositoryMeal;
    }

    @Override
    public Meal create(Meal meal, Integer userId) {
        return checkNotFoundWithId(repositoryMeal.save(meal, userId), meal.getId());
    }

    @Override
    public void delete(int id, Integer userId) throws NotFoundException {
        checkNotFoundWithId(repositoryMeal.delete(id, userId), id);
    }

    @Override
    public Meal get(int id, Integer userId) throws NotFoundException {
        return checkNotFoundWithId(repositoryMeal.get(id, userId), id);
    }

    @Override
    public List<MealTo> getAllbyUser(Integer userId) {
        return MealsUtil.getWithExcess(repositoryMeal.getAllbyUser(userId), MealsUtil.DEFAULT_CALORIES_PER_DAY);
    }

    @Override
    public void update(Meal meal, Integer userId) {
        checkNotFoundWithId(repositoryMeal.save(meal, userId), meal.getId());
    }

    @Override
    public List<MealTo> getFilterDT(LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime, Integer userId) {
        return MealsUtil.getWithExcess(
                repositoryMeal.getFilterDT(startDate, startTime, endDate, endTime, userId), MealsUtil.DEFAULT_CALORIES_PER_DAY).stream()
                .filter(mealTo -> DateTimeUtil.isBetweenDate(
                        mealTo.getDateTime().toLocalTime(), startTime, endTime))
                .collect(Collectors.toList());
    }
}