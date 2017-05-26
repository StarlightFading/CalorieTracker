package rh.calorietracker.entity;

import java.io.Serializable;

public class Food implements Serializable {

    private Long id;

    private String name;

    private int calories;

    public Food(String name, int calories) {
        this.name = name;
        this.calories = calories;
    }

    public Food(Long id, String name, int calories) {
        this.id = id;
        this.name = name;
        this.calories = calories;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }
}
