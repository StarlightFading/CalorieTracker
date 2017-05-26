package rh.calorietracker.data.impl;

import java.util.ArrayList;
import java.util.List;

import rh.calorietracker.data.FoodRepository;
import rh.calorietracker.entity.Food;

public class MockFoodRepository implements FoodRepository {

    private static final List<Food> foods = new ArrayList<>();

    static {
        foods.add(new Food("test", 5));
        foods.add(new Food("another one", 10));
    }

    @Override
    public List<Food> findAll() {
        return new ArrayList<>(foods);
    }

    @Override
    public void create(Food food) {
        foods.add(food);
    }

    @Override
    public void delete(Food food) {
        foods.remove(food);
    }
}
