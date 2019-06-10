package ru.javawebinar.topjava.memory;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class memoryMealDaoIml implements memoryMealDAO {
    private static final AtomicInteger AUTO_ID = new AtomicInteger(0);
    public static List<Meal> mealsG = new ArrayList<>();
    public static int userCaloriesPerDay;

    static {
        mealsG = Arrays.asList(
                new Meal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500, 1),
                new Meal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000, 0),
                new Meal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500, 2),
                new Meal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000, 3),
                new Meal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500, 4),
                new Meal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510, 4)
        );
        userCaloriesPerDay = 2000;
    }

    @Override
    public void add(Meal meal) {
        meal.setId(AUTO_ID.getAndIncrement());
        mealsG.add(meal);
    }

    @Override
    public void delete(int id) {
        mealsG.removeIf(n->n.getId()==id);
    }

    @Override
    public void edit(Meal meal) {
        mealsG.add(meal.getId(),meal);
    }

    @Override
    public Meal getById(int id) {
        return mealsG.get(id);
    }
}
