package ru.javawebinar.topjava.memory;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;
import java.util.Map;

public interface memoryMealDAO {

    void add(Meal meal);

    void set(int id, Meal meal);

    void delete(int id);

    void edit(int id, Meal meal);

    Meal getById(int id);

    Map<Integer, Meal> getAll();
}
