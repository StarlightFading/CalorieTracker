package rh.calorietracker.entity;

import org.threeten.bp.LocalDate;

import rh.calorietracker.data.impl.DatabaseEntity;

public class ConsumedFood extends DatabaseEntity {

    private LocalDate date;

    private Food food;

    private Portion portion;

    private double amount;

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
}
