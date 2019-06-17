package ru.javawebinar.topjava.repository.inmemory;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.TO.MealTo;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {
    private Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.MEALS.forEach(this::save);
        save(new Meal(null, LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Поздний ужин", 510, 2));
    }

    @Override
    public Meal save(Meal meal) {
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            repository.put(meal.getId(), meal);
            return meal;
        }
        // treat case: update, but absent in storage
        return repository.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
    }

    @Override
    public boolean delete(int id, int userId) {
        if (repository.get(id) == null) return false;
        if (repository.get(id).getUserId()==userId) return repository.remove(id) != null;
        else return false;
    }

    @Override
    public Meal get(int id, int useId) {
        if (repository.get(id) == null) return null;
        if (repository.get(id).getUserId().equals(useId)) return repository.get(id);
        return null;
    }

    @Override
    public List<MealTo> getAllbyUser(int userId) {
        List<Meal> mealList = repository.values().stream()
                .filter(meal -> meal.getUserId() == userId)
                .collect(Collectors.toList());
        return MealsUtil.getWithExcess(mealList, MealsUtil.DEFAULT_CALORIES_PER_DAY).stream()
                .sorted(Comparator.comparing(MealTo::getDateTime).reversed()).collect(Collectors.toList());
    }

    @Override
    public List<MealTo> getFilterDT(LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime) {
        return MealsUtil.getWithExcess(repository.values(), MealsUtil.DEFAULT_CALORIES_PER_DAY).stream()
                .filter(meal -> DateTimeUtil.isBetweenDate(
                        meal.getDateTime().toLocalDate(), startDate, endDate))
                .filter(meal -> DateTimeUtil.isBetweenDate(
                        meal.getDateTime().toLocalTime(), startTime, endTime))
                .sorted(Comparator.comparing(MealTo::getDateTime).reversed()).collect(Collectors.toList());
    }

    @Override
    public List<MealTo> getAll() {
        return MealsUtil.getWithExcess(repository.values(), MealsUtil.DEFAULT_CALORIES_PER_DAY).stream()
                .sorted(Comparator.comparing(MealTo::getDateTime).reversed()).collect(Collectors.toList());
    }
}

