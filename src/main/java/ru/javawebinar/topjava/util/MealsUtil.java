package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toList;

public class MealsUtil {
    private static int userCaloriesPerDay = 2000;

    public static List<MealTo> getWithExcess(Map<Integer, Meal> mealM, int caloriesPerDay){

        class Service {
            private LocalDateTime dateTime;
            private String description;
            private int calories;
            int id;

            public Service(){}

            public Service(int id, Meal meal) {
                this.id = id;
                this.dateTime = meal.getDateTime();
                this.description = meal.getDescription();
                this.calories = meal.getCalories();
            }

            public LocalDateTime getDateTimeS() {
                return dateTime;
            }
            public int getCaloriesS() {
                return calories;
            }
            public LocalDate getDateS() {
                return dateTime.toLocalDate();
            }
            public String getDescriptionS() {
                return description;
            }
            public int getIdS() {return id;}
            public MealTo createWithExcess(Service meal, boolean excess) {
                return new MealTo(meal.getIdS(), meal.getDateTimeS(), meal.getDescriptionS(), meal.getCaloriesS(), excess);
            }
        }

        final List<Service> meals = new ArrayList<>();
        mealM.forEach((k, v) -> meals.add(new Service(k,v)));

        Map<LocalDate, Integer> caloriesSumByDate = new HashMap<>();
        for (Service userMeal : meals) {
            caloriesSumByDate.merge(userMeal.getDateTimeS().toLocalDate(), userMeal.getCaloriesS(), Integer::sum);
        }

        return meals.stream()
                .map(meal -> new Service().createWithExcess(meal, caloriesSumByDate.get(meal.getDateS()) > caloriesPerDay))
                .collect(toList());
    }

    public static int getUserCalories() {
        return userCaloriesPerDay;
    }
}