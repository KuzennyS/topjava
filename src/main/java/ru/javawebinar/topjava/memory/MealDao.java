package ru.javawebinar.topjava.memory;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MealDao implements memoryMealDAO {
    private final AtomicInteger AUTO_ID = new AtomicInteger(0);
    private ConcurrentMap<Integer, Meal> mealsG = new ConcurrentHashMap<>();

    {
        mealsG.put(AUTO_ID.getAndIncrement(), new Meal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500));
        mealsG.put(AUTO_ID.getAndIncrement(),new Meal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000));
        mealsG.put(AUTO_ID.getAndIncrement(),new Meal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500));
        mealsG.put(AUTO_ID.getAndIncrement(),new Meal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000));
        mealsG.put(AUTO_ID.getAndIncrement(),new Meal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500));
        mealsG.put(AUTO_ID.getAndIncrement(),new Meal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510));
    }

    @Override
    public void add(Meal meal) {
        mealsG.put(AUTO_ID.getAndIncrement(), meal);
    }

    @Override
    public void set(int id, Meal meal) {
        mealsG.put(id, meal);
    }

    @Override
    public void delete(int id) {
        mealsG.remove(id);
    }

    @Override
    public void edit(int id, Meal meal) {
        mealsG.remove(id);
        mealsG.put(id, meal);
    }

    @Override
    public Meal getById(int id) {
        return mealsG.get(id);
    }

    @Override
    public Map<Integer, Meal> getAll() {
        return mealsG;
    }

}
