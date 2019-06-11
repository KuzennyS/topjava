package ru.javawebinar.topjava.memory;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class memoryMealDaoIml implements memoryMealDAO {
    private static final AtomicInteger AUTO_ID = new AtomicInteger(0);
    public static List<Meal> mealsG = new ArrayList<>();
    public static int userCaloriesPerDay;
    public static DateTimeFormatter dateFormatMeal = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
    public static DateTimeFormatter dateFormatSite = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    static {
        mealsG.add(new Meal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500, AUTO_ID.getAndIncrement()));
        mealsG.add(new Meal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000, AUTO_ID.getAndIncrement()));
        mealsG.add(new Meal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500, AUTO_ID.getAndIncrement()));
        mealsG.add(new Meal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000, AUTO_ID.getAndIncrement()));
        mealsG.add(new Meal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500, AUTO_ID.getAndIncrement()));
        mealsG.add(new Meal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510, AUTO_ID.getAndIncrement()));
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
        int id = meal.getId();
        mealsG.removeIf(n->n.getId()==id);
        mealsG.add(meal);
    }

    @Override
    public Meal getById(int id) {
        return mealsG.get(id);
    }
}
