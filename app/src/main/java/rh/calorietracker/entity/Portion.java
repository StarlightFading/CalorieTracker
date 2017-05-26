package rh.calorietracker.entity;

public class Portion {

    private String name;

    private int amount;

    private Food food;

    public Portion(String name, int amount, Food food) {
        this.name = name;
        this.amount = amount;
        this.food = food;
    }

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
