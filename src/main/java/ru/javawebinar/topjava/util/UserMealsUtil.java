package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
        );
        System.out.println(getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000).toString());
//        .toLocalDate();
//        .toLocalTime();
    }

//    public static List<UserMealWithExceed> getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
////
////        Map<LocalDate, Integer> caloriesList = mealList.stream()
////                .collect(Collectors.toMap(
////                        c -> c.getDateTime().toLocalDate(),
////                        UserMeal::getCalories,
////                        Integer::sum));
////
////        return mealList.stream()
////                .filter(x -> TimeUtil.isBetween(x.getDateTime().toLocalTime(), startTime, endTime))
////                .map(c -> new UserMealWithExceed(c.getDateTime(), c.getDescription(), caloriesPerDay,
////                                caloriesPerDay >= caloriesList.get(c.getDateTime().toLocalDate())))
////                .collect(Collectors.toCollection(ArrayList::new));
////    }

    public static List<UserMealWithExceed> getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {

        Map<LocalDate, Integer> caloriesList = new HashMap<>();
        for (UserMeal userMeal : mealList) {
            caloriesList.merge(userMeal.getDateTime().toLocalDate(), userMeal.getCalories(), Integer::sum);
        }


        List<UserMealWithExceed> meelListExceed = new ArrayList<>();
        for (UserMeal userMeal : mealList) {
            if (TimeUtil.isBetween(userMeal.getDateTime().toLocalTime(), startTime, endTime)) {
                boolean exceed = caloriesPerDay >= caloriesList.get(userMeal.getDateTime().toLocalDate());
                meelListExceed.add(new UserMealWithExceed(userMeal.getDateTime(),
                        userMeal.getDescription(), caloriesPerDay, exceed));
            }
        }

        return meelListExceed;
    }

}

