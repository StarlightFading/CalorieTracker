package rh.calorietracker.data.impl;

import android.content.ContentValues;
import android.database.Cursor;

import org.threeten.bp.LocalDate;
import org.threeten.bp.format.DateTimeFormatter;

import java.util.List;

import rh.calorietracker.data.ConsumedFoodRepository;
import rh.calorietracker.entity.ConsumedFood;
import rh.calorietracker.entity.Meal;

public class DatabaseConsumedFoodRepository extends DatabaseRepository<ConsumedFood> implements ConsumedFoodRepository {

    private static final String TAG = "DatabaseConsumedFoodRepository";

    @Override
    public List<ConsumedFood> findAll() {
        return null;
    }

    @Override
    protected String getTableName() {
        return DatabaseSchema.ConsumedFoodEntry._TABLE_NAME;
    }

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    protected ContentValues getContentValues(ConsumedFood consumedFood) {
        ContentValues values = new ContentValues();
        values.put(DatabaseSchema.ConsumedFoodEntry.DATE, formatDate(consumedFood.getDate()));
        values.put(DatabaseSchema.ConsumedFoodEntry.MEAL, formatMeal(consumedFood.getMeal()));
        values.put(DatabaseSchema.ConsumedFoodEntry.AMOUNT, consumedFood.getAmount());
        values.put(DatabaseSchema.ConsumedFoodEntry.FOOD_ID, consumedFood.getFood().getId());
        values.put(DatabaseSchema.ConsumedFoodEntry.PORTION_ID, consumedFood.getPortion().getId());
        
        return values;
    }

    @Override
    protected String[] getColumns() {
        return new String[] {
                DatabaseSchema.ConsumedFoodEntry.DATE,
                DatabaseSchema.ConsumedFoodEntry.MEAL,
                DatabaseSchema.ConsumedFoodEntry.AMOUNT,
                DatabaseSchema.ConsumedFoodEntry.FOOD_ID,
                DatabaseSchema.ConsumedFoodEntry.PORTION_ID,
        };
    }

    @Override
    protected ConsumedFood mapEntityFromCursor(Cursor cursor) {
        return null;
    }
    
    private String formatDate(LocalDate date) {
        return date.format(DateTimeFormatter.ISO_DATE);
    }
    
    private Integer formatMeal(Meal meal) {
        switch (meal) {
            case BREAKFAST:
                return 1;
            case LUNCH:
                return 2;
            case DINNER:
                return 3;
            case SNACK1:
                return 4;
            case SNACK2:
                return 5;
            default:
                return null;
        }
    }
}
