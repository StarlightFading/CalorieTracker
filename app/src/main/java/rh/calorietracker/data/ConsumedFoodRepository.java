package rh.calorietracker.data;

import org.threeten.bp.LocalDate;

import java.util.List;

import rh.calorietracker.entity.ConsumedFood;

public interface ConsumedFoodRepository {

    List<ConsumedFood> findAll(); // TODO: remove

    List<ConsumedFood> findForDate(LocalDate date);

    void create(ConsumedFood consumedFood);

    void delete(ConsumedFood consumedFood);
}
