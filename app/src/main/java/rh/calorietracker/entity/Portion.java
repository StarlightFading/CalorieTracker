package rh.calorietracker.entity;

import rh.calorietracker.data.impl.DatabaseEntity;

public class Portion extends DatabaseEntity {

    private String name;

    private int amount;

    private Food food;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }
}
