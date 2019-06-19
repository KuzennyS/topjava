package ru.javawebinar.topjava.repository.inmemory;

import org.springframework.stereotype.Repository;
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
    private Map<Integer, Map<Integer, Meal>> repositoryG = new ConcurrentHashMap<>();

    private AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.MEALS.forEach(meal -> save(meal,1));
        save(new Meal(null, LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Поздний ужин", 510),2);
    }

    @Override
    public Meal save(Meal meal, Integer userId) {
        Map<Integer, Meal> repository;
        repository = repositoryG.get(userId) != null ? repositoryG.get(userId) : new ConcurrentHashMap<>();
//        repositoryG.computeIfAbsent(userId, k -> new ConcurrentHashMap<>()).put(meal.getId(), meal);
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            repository.put(meal.getId(), meal);
            repositoryG.put(userId,repository);
            return meal;
        }
        // treat case: update, but absent in storage
        repository.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
        repositoryG.put(userId,repository);
        return meal;
    }

    @Override
    public boolean delete(int id, Integer userId) {
        Map<Integer, Meal> repository = repositoryG.get(userId);
        if (repository == null) return false;
        return repository.remove(id) != null;
    }

    @Override
    public Meal get(int id, Integer userId) {
        Map<Integer, Meal> repository = repositoryG.get(userId);
        if (repository == null) return null;
        return repository.get(id);
    }

    @Override
    public List<Meal> getAllbyUser(Integer userId) {
        Map<Integer, Meal> repository = repositoryG.get(userId);
        if (repository == null) return new ArrayList<>();
        return repository.values().stream()
                .sorted(Comparator.comparing(Meal::getDateTime).reversed()).collect(Collectors.toList());
    }

    @Override
    public List<Meal> getFilterDT(LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime, Integer userId) {
        return repositoryG.get(userId).values().stream()
                .filter(meal -> DateTimeUtil.isBetweenDate(
                        meal.getDateTime().toLocalDate(), startDate, endDate))
                .sorted(Comparator.comparing(Meal::getDateTime).reversed()).collect(Collectors.toList());
    }
}

