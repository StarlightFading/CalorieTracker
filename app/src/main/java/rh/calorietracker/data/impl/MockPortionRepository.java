package rh.calorietracker.data.impl;

import java.util.ArrayList;
import java.util.List;

import rh.calorietracker.data.PortionRepository;
import rh.calorietracker.entity.Food;
import rh.calorietracker.entity.Portion;

public class MockPortionRepository implements PortionRepository {

    @Override
    public List<Portion> findForFood(Food food) {
        List<Portion> portions = new ArrayList<>();

        portions.add(new Portion("Small", 40, food));
        portions.add(new Portion("Medium", 80, food));
        portions.add(new Portion("Large", 125, food));

        return portions;
    }
}
