package rh.calorietracker.entity;

import java.io.Serializable;

public class Food implements Serializable {

    private Long id;

    private String name;

    private int calories;

    private int protein;

    private int carbs;

    private int fat;

    public Food() {
    }

    public Food(String name, int calories) {
        this.name = name;
        this.calories = calories;
    }

    public Food(Long id, String name, int calories) {
        this.id = id;
        this.name = name;
        this.calories = calories;
    }

    public Food(Long id, String name, int calories, int protein, int carbs, int fat) {
        this.id = id;
        this.name = name;
        this.calories = calories;
        this.protein = protein;
        this.carbs = carbs;
        this.fat = fat;
    }

    public Food(String name, int calories, int protein, int carbs, int fat) {
        this.name = name;
        this.calories = calories;
        this.protein = protein;
        this.carbs = carbs;
        this.fat = fat;
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

    public int getProtein() {
        return protein;
    }

    public void setProtein(int protein) {
        this.protein = protein;
    }

    public int getCarbs() {
        return carbs;
    }

    public void setCarbs(int carbs) {
        this.carbs = carbs;
    }

    public int getFat() {
        return fat;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }
}
