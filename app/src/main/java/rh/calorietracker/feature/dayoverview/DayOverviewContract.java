package rh.calorietracker.feature.dayoverview;

import org.threeten.bp.LocalDate;

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
        void onConsumedFoodListRequested(LocalDate date);

        void onRequestConsumedFoodDialog(Food food);

        void onConsumedFoodAdded(ConsumedFood consumedFood);

        void onConsumedFoodDeleted(ConsumedFood consumedFood);
    }
}
