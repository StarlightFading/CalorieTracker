package rh.calorietracker.data.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rh.calorietracker.data.FoodRepository;
import rh.calorietracker.entity.Food;

public class MockFoodRepository implements FoodRepository {

    private static final Map<Long, Food> foods = new HashMap<>();

    private static Long idCounter = 2L;

    static {
        foods.put(1L, new Food(1L, "test", 5, 4, 3, 2));
        foods.put(2L, new Food(2L, "another one", 10, 15, 20, 25));
    }

    @Override
    public List<Food> findAll() {
        return new ArrayList<>(foods.values());
    }

    @Override
    public void create(Food food) {
        idCounter++;
        food.setId(idCounter);
        foods.put(idCounter, food);
    }

    @Override
    public void update(Food food) {
        if (foods.containsKey(food.getId())) {
            foods.put(food.getId(), food);
        }
    }

    @Override
    public void delete(Food food) {
        foods.remove(food);
    }
}
