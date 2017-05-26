package rh.calorietracker.feature.fooddetails;

import java.util.List;

import rh.calorietracker.entity.Food;
import rh.calorietracker.entity.Portion;

public interface FoodDetailsContract {

    interface View {
        void displayPortions(List<Portion> portions);
    }

    interface ViewActions {
        void onPortionsListRequested(Food food);
    }
}
