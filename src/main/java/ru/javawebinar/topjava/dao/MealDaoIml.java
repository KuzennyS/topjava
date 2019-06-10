package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.memory.memoryMealDAO;
import ru.javawebinar.topjava.memory.memoryMealDaoIml;
import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public class MealDaoIml implements MealDAO {
    private memoryMealDAO mealDAO = new memoryMealDaoIml();

    @Override
    public void add(Meal meal) {
        mealDAO.add(meal);
    }

    @Override
    public void delete(int id) {
        mealDAO.delete(id);
    }

    @Override
    public void edit(Meal meal) {
        mealDAO.edit(meal);
    }

    @Override
    public Meal getById(int id) {
        return mealDAO.getById(id);
    }
}
