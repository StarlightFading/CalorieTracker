package rh.calorietracker.feature.foodlist;

import rh.calorietracker.Presenter;
import rh.calorietracker.data.FoodRepository;
import rh.calorietracker.data.impl.MockFoodRepository;

public class FoodListPresenter extends Presenter<FoodListContract.View> implements FoodListContract.ViewActions {

    private final FoodRepository foodRepository = new MockFoodRepository();

    public FoodListPresenter(FoodListContract.View view) {
        super(view);
    }

    @Override
    public void onFoodListRequested() {
        view.displayFoods(foodRepository.findAll());
    }
}
