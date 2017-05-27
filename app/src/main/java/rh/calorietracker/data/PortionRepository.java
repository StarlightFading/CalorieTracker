package rh.calorietracker.data;

import java.util.List;

import rh.calorietracker.entity.Food;
import rh.calorietracker.entity.Portion;

public interface PortionRepository {

    List<Portion> findForFood(Food food);

    void create(Portion portion);
}
