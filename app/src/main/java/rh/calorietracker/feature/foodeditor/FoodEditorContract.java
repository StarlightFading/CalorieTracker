package rh.calorietracker.feature.foodeditor;

import rh.calorietracker.entity.Food;

public interface FoodEditorContract {

    interface View {
        void closeEditor();
    }

    interface ViewActions {
        void onFoodSaved(Food food);
    }
}
