package rh.calorietracker.feature.dayoverview;

import java.util.List;

import rh.calorietracker.entity.ConsumedFood;

public interface DayOverviewContract {

    interface View {
        void displayConsumedFoodList(List<ConsumedFood> consumedFoods);
    }

    interface ViewActions {
        void onConsumedFoodListRequested();
    }
}
