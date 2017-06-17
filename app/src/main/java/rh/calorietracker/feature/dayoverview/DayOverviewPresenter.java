package rh.calorietracker.feature.dayoverview;

import org.threeten.bp.LocalDate;

import java.util.ArrayList;
import java.util.List;

import rh.calorietracker.common.Presenter;
import rh.calorietracker.data.ConsumedFoodRepository;
import rh.calorietracker.data.FoodRepository;
import rh.calorietracker.data.PortionRepository;
import rh.calorietracker.data.impl.DatabaseConsumedFoodRepository;
import rh.calorietracker.data.impl.DatabaseFoodRepository;
import rh.calorietracker.data.impl.DatabasePortionRepository;
import rh.calorietracker.entity.ConsumedFood;
import rh.calorietracker.entity.Food;
import rh.calorietracker.entity.Meal;
import rh.calorietracker.entity.Portion;

public class DayOverviewPresenter extends Presenter<DayOverviewContract.View> implements DayOverviewContract.ViewActions {

    private ConsumedFoodRepository consumedFoodRepository = new DatabaseConsumedFoodRepository();
    private PortionRepository portionRepository = new DatabasePortionRepository();

    public DayOverviewPresenter(DayOverviewContract.View view) {
        super(view);
    }

    @Override
    public void onConsumedFoodListRequested() {
        view.displayConsumedFoodList(consumedFoodRepository.findAll());
    }

    @Override
    public void onRequestConsumedFoodDialog(Food food) {
        view.displayConsumedFoodDialog(food, portionRepository.findForFood(food));
    }

    @Override
    public void onConsumedFoodAdded(ConsumedFood consumedFood) {
        consumedFoodRepository.create(consumedFood);
    }

    @Override
    public void onConsumedFoodDeleted(ConsumedFood consumedFood) {
        consumedFoodRepository.delete(consumedFood);
    }
}
