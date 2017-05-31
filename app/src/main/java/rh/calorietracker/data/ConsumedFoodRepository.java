package rh.calorietracker.data;

import java.util.List;

import rh.calorietracker.entity.ConsumedFood;

public interface ConsumedFoodRepository {

    List<ConsumedFood> findAll();

    void create(ConsumedFood consumedFood);

    void delete(ConsumedFood consumedFood);
}
