package rh.calorietracker.feature.foodlist;

import java.util.List;

import rh.calorietracker.entity.Food;

public interface FoodListContract {

    interface View {
        void displayFoods(List<Food> foods);
    }

    interface ViewActions {
        void onFoodListRequested();
    }
}
