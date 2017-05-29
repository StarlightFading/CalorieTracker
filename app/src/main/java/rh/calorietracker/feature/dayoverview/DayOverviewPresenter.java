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
import rh.calorietracker.entity.Meal;
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
        cf1.setMeal(Meal.DINNER);
        consumedFoods.add(cf1);

        ConsumedFood cf2 = new ConsumedFood();
        cf2.setFood(foods.get(0));
        cf2.setDate(LocalDate.now());
        cf2.setAmount(1);
        cf2.setPortion(portions.get(0));
        cf2.setMeal(Meal.BREAKFAST);
        consumedFoods.add(cf2);

        ConsumedFood cf4 = new ConsumedFood();
        cf4.setFood(foods.get(0));
        cf4.setDate(LocalDate.now());
        cf4.setAmount(1);
        cf4.setPortion(portions.get(0));
        cf4.setMeal(Meal.BREAKFAST);
        consumedFoods.add(cf4);

        ConsumedFood cf3 = new ConsumedFood();
        cf3.setFood(foods.get(0));
        cf3.setDate(LocalDate.now());
        cf3.setAmount(0.5);
        cf3.setPortion(portions.get(0));
        consumedFoods.add(cf3);

        ConsumedFood cf5 = new ConsumedFood();
        cf5.setFood(foods.get(0));
        cf5.setDate(LocalDate.now());
        cf5.setAmount(1);
        cf5.setPortion(portions.get(0));
        cf5.setMeal(Meal.BREAKFAST);
        consumedFoods.add(cf5);

        ConsumedFood cf6 = new ConsumedFood();
        cf6.setFood(foods.get(0));
        cf6.setDate(LocalDate.now());
        cf6.setAmount(1);
        cf6.setPortion(portions.get(0));
        cf6.setMeal(Meal.DINNER);
        consumedFoods.add(cf6);

        view.displayConsumedFoodList(consumedFoods);
    }
}
