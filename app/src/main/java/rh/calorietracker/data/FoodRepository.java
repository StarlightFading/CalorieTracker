package rh.calorietracker.data;

import java.util.List;

import rh.calorietracker.entity.Food;

public interface FoodRepository {

    List<Food> findAll();

    void create(Food food);

    void delete(Food food);
}
