package rh.calorietracker.feature.dayoverview;

import org.threeten.bp.LocalDate;

import java.util.ArrayList;
import java.util.List;

import rh.calorietracker.common.Presenter;
import rh.calorietracker.data.FoodRepository;
import rh.calorietracker.data.PortionRepository;
import rh.calorietracker.data.impl.DatabaseFoodRepository;
import rh.calorietracker.data.impl.DatabasePortionRepository;
import rh.calorietracker.entity.ConsumedFood;
import rh.calorietracker.entity.Food;
import rh.calorietracker.entity.Portion;

public class DayOverviewPresenter extends Presenter<DayOverviewContract.View> implements DayOverviewContract.ViewActions {

    public DayOverviewPresenter(DayOverviewContract.View view) {
        super(view);
    }

    @Override
    public void onConsumedFoodListRequested() {
        FoodRepository foodRepository = new DatabaseFoodRepository();
        PortionRepository portionRepository = new DatabasePortionRepository();

        List<Food> foods = foodRepository.findAll();
        List<Portion> portions = portionRepository.findForFood(foods.get(0));

        List<ConsumedFood> consumedFoods = new ArrayList<>();

        ConsumedFood cf1 = new ConsumedFood();
        cf1.setFood(foods.get(0));
        cf1.setDate(LocalDate.now());
        cf1.setAmount(0.5);
        consumedFoods.add(cf1);

        ConsumedFood cf2 = new ConsumedFood();
        cf2.setFood(foods.get(0));
        cf2.setDate(LocalDate.now());
        cf2.setAmount(0.5);
        cf2.setPortion(portions.get(0));
        consumedFoods.add(cf2);

        view.displayConsumedFoodList(consumedFoods);
    }
}
