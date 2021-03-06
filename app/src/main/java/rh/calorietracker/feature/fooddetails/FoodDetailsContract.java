package rh.calorietracker.feature.fooddetails;

import java.util.List;

import rh.calorietracker.entity.Food;
import rh.calorietracker.entity.Portion;

public interface FoodDetailsContract {

    interface View {
        void displayPortions(List<Portion> portions);
    }

    interface ViewActions {
        void onPortionsListRequested();

        void onPortionCreated(Portion portion);

        void onPortionUpdated(Portion portion);

        void onPortionDeleted(Portion portion);
    }
}
