package rh.calorietracker.feature.foodlist;

import rh.calorietracker.common.Presenter;
import rh.calorietracker.data.FoodRepository;
import rh.calorietracker.data.impl.MockFoodRepository;
import rh.calorietracker.entity.Food;

public class FoodListPresenter extends Presenter<FoodListContract.View> implements FoodListContract.ViewActions {

    private final FoodRepository foodRepository = new MockFoodRepository();

    public FoodListPresenter(FoodListContract.View view) {
        super(view);
    }

    @Override
    public void onFoodListRequested() {
        view.displayFoods(foodRepository.findAll());
    }

    @Override
    public void onFoodDeleted(Food food) {
        foodRepository.delete(food);
    }
}
