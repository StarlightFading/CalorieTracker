package rh.calorietracker.feature.foodpicker;

import java.util.List;

import rh.calorietracker.entity.Food;

public interface FoodPickerContract {

    interface View {
        void displayFoods(List<Food> foods);
    }

    interface ViewActions {
        void onFoodListRequested();
    }
}
