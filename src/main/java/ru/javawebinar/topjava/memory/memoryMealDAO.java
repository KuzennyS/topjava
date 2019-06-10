package ru.javawebinar.topjava.memory;

import ru.javawebinar.topjava.model.Meal;

public interface memoryMealDAO {

    void add(Meal meal);

    void delete(int id);

    void edit(Meal meal);

    Meal getById(int id);
}
