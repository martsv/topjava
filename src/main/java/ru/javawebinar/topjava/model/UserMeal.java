package ru.javawebinar.topjava.model;

import java.time.LocalDateTime;

/**
 * GKislin
 * 11.01.2015.
 */
public class UserMeal extends BaseEntity {
    private final LocalDateTime dateTime;

    private final String description;

    private final int calories;

    private final int userId;

    public UserMeal(LocalDateTime dateTime, String description, int calories, int userId) {
        this(null, dateTime, description, calories, userId);
    }

    public UserMeal(Integer id, LocalDateTime dateTime, String description, int calories, int userId) {
        super(id);
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        this.userId = userId;
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

    public int getUserId() { return userId; }

    @Override
    public String toString() {
        return "UserMeal{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                ", userId=" + userId +
                '}';
    }
}
