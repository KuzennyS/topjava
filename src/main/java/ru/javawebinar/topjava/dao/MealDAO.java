package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

public interface MealDAO {

    void add(Meal meal);

    void delete(int id);

    void edit(Meal meal);

    Meal getById(int id);
}
