package rh.calorietracker.feature.fooddetails;

import java.util.List;

import rh.calorietracker.common.Presenter;
import rh.calorietracker.data.PortionRepository;
import rh.calorietracker.data.impl.DatabasePortionRepository;
import rh.calorietracker.entity.Food;
import rh.calorietracker.entity.Portion;

public class FoodDetailsPresenter extends Presenter<FoodDetailsContract.View> implements FoodDetailsContract.ViewActions {

    private final PortionRepository portionRepository = new DatabasePortionRepository();

    private final Food food;

    public FoodDetailsPresenter(FoodDetailsActivity view, Food food) {
        super(view);
        this.food = food;
    }

    @Override
    public void onPortionsListRequested() {
        List<Portion> portions = portionRepository.findForFood(food);
        view.displayPortions(portions);
    }

    @Override
    public void onPortionCreated(Portion portion) {
        portion.setFood(food);
        portionRepository.create(portion);
    }
}
