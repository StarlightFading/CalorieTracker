package rh.calorietracker.feature.foodpicker;

import rh.calorietracker.common.Presenter;
import rh.calorietracker.data.FoodRepository;
import rh.calorietracker.data.impl.DatabaseFoodRepository;

public class FoodPickerPresenter extends Presenter<FoodPickerContract.View> implements FoodPickerContract.ViewActions {

    private final FoodRepository foodRepository = new DatabaseFoodRepository();

    public FoodPickerPresenter(FoodPickerContract.View view) {
        super(view);
    }

    @Override
    public void onFoodListRequested() {
        view.displayFoods(foodRepository.findAll());
    }
}
