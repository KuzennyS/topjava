package ru.javawebinar.topjava.model;

import java.time.LocalDateTime;

public class UserMeal {
    private final LocalDateTime dateTime;

    private String description;

    private int calories;

    private int id;

    public int getId() {
        return id;
    }

    public Meal(LocalDateTime dateTime, String description, int calories, int id) {
        this.id = id;
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public void setId(int id) {
        this.id = id;
    }
}
