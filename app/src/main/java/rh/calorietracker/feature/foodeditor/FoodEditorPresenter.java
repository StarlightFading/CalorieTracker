package rh.calorietracker.feature.foodeditor;

import rh.calorietracker.Presenter;
import rh.calorietracker.data.FoodRepository;
import rh.calorietracker.data.impl.MockFoodRepository;
import rh.calorietracker.entity.Food;
import rh.calorietracker.feature.foodeditor.FoodEditorContract.ViewActions;

public class FoodEditorPresenter extends Presenter<FoodEditorActivity> implements ViewActions {

    private final FoodRepository foodRepository = new MockFoodRepository();

    public FoodEditorPresenter(FoodEditorActivity view) {
        super(view);
    }

    @Override
    public void onFoodSaved(Food food) {
        foodRepository.create(food);
    }
}
