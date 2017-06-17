package rh.calorietracker.entity;

import org.threeten.bp.LocalDate;

import java.util.Locale;

import rh.calorietracker.data.impl.DatabaseEntity;

public class ConsumedFood extends DatabaseEntity {

    private LocalDate date;

    private Food food;

    private Portion portion;

    private double amount;

    private Meal meal;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public Portion getPortion() {
        return portion;
    }

    public void setPortion(Portion portion) {
        this.portion = portion;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Meal getMeal() {
        return meal;
    }

    public void setMeal(Meal meal) {
        this.meal = meal;
    }

    public double getCalories() {
        int portionSize = getPortion() != null ? getPortion().getAmount() : 100;
        return portionSize * getAmount() * getFood().getCalories() / 100;
    }

    public double getProtein() {
        int portionSize = getPortion() != null ? getPortion().getAmount() : 100;
        return portionSize * getAmount() * getFood().getProtein() / 100;
    }

    public double getCarbs() {
        int portionSize = getPortion() != null ? getPortion().getAmount() : 100;
        return portionSize * getAmount() * getFood().getCarbs() / 100;
    }

    public double getFat() {
        int portionSize = getPortion() != null ? getPortion().getAmount() : 100;
        return portionSize * getAmount() * getFood().getFat() / 100;
    }
}
