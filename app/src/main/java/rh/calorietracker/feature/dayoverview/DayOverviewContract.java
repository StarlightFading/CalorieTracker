package rh.calorietracker.feature.dayoverview;

import java.util.List;

import rh.calorietracker.entity.ConsumedFood;
import rh.calorietracker.entity.Food;
import rh.calorietracker.entity.Portion;

public interface DayOverviewContract {

    interface View {
        void displayConsumedFoodList(List<ConsumedFood> consumedFoods);

        void displayConsumedFoodDialog(Food food, List<Portion> portions);
    }

    interface ViewActions {
        void onConsumedFoodListRequested();

        void onRequestConsumedFoodDialog(Food food);
    }
}
