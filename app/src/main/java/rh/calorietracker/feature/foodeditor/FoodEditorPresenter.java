package rh.calorietracker.feature.foodeditor;

import rh.calorietracker.Presenter;
import rh.calorietracker.data.FoodRepository;
import rh.calorietracker.data.impl.MockFoodRepository;
import rh.calorietracker.entity.Food;
import rh.calorietracker.feature.foodeditor.FoodEditorContract.ViewActions;

public class FoodEditorPresenter extends Presenter<FoodEditorActivity> implements ViewActions {

    private final FoodRepository foodRepository = new MockFoodRepository();

    private final Food food;

    public FoodEditorPresenter(FoodEditorActivity view, Food food) {
        super(view);
        this.food = food;
    }

    @Override
    public void onFoodSaved(Food food) {
        if (this.food == null) {
            foodRepository.create(food);
        } else {
            this.food.setName(food.getName());
            this.food.setCalories(food.getCalories());
            foodRepository.update(this.food);
        }
    }
}
