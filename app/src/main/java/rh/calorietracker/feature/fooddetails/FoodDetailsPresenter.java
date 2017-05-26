package rh.calorietracker.feature.fooddetails;

import java.util.List;

import rh.calorietracker.common.Presenter;
import rh.calorietracker.data.PortionRepository;
import rh.calorietracker.data.impl.MockPortionRepository;
import rh.calorietracker.entity.Food;
import rh.calorietracker.entity.Portion;

public class FoodDetailsPresenter extends Presenter<FoodDetailsContract.View> implements FoodDetailsContract.ViewActions {

    PortionRepository portionRepository = new MockPortionRepository();

    public FoodDetailsPresenter(FoodDetailsActivity view) {
        super(view);
    }

    @Override
    public void onPortionsListRequested(Food food) {
        List<Portion> portions = portionRepository.findForFood(food);
        view.displayPortions(portions);
    }
}
