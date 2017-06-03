package rh.calorietracker.data.impl;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import org.threeten.bp.LocalDate;
import org.threeten.bp.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.List;

import rh.calorietracker.data.ConsumedFoodRepository;
import rh.calorietracker.data.FoodRepository;
import rh.calorietracker.data.impl.DatabaseSchema.ConsumedFoodEntry;
import rh.calorietracker.entity.ConsumedFood;
import rh.calorietracker.entity.Meal;

public class DatabaseConsumedFoodRepository extends DatabaseRepository<ConsumedFood> implements ConsumedFoodRepository {

    private static final String TAG = "DatabaseConsumedFoodRepository";

    @Override
    public List<ConsumedFood> findAll() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery(ConsumedFoodEntry.SQL_FIND_FOR_DATE, null);

        List<ConsumedFood> consumedFoods = new ArrayList<>();

        while (cursor.moveToNext()) {
            ConsumedFood consumedFood = mapEntityFromCursor(cursor);
            consumedFoods.add(consumedFood);
        }

        cursor.close();

        return consumedFoods;
    }

    @Override
    protected String getTableName() {
        return ConsumedFoodEntry._TABLE_NAME;
    }

    @Override
    protected String getIdColumnName() {
        return ConsumedFoodEntry.ID;
    }

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    protected ContentValues getContentValues(ConsumedFood consumedFood) {
        ContentValues values = new ContentValues();
        values.put(ConsumedFoodEntry.DATE, formatDate(consumedFood.getDate()));
        values.put(ConsumedFoodEntry.MEAL, formatMeal(consumedFood.getMeal()));
        values.put(ConsumedFoodEntry.AMOUNT, consumedFood.getAmount());
        values.put(ConsumedFoodEntry.FOOD_ID, consumedFood.getFood().getId());

        if (consumedFood.getPortion() == null) {
            values.putNull(ConsumedFoodEntry.PORTION_ID);
        } else {
            values.put(ConsumedFoodEntry.PORTION_ID, consumedFood.getPortion().getId());
        }
        
        return values;
    }

    @Override
    protected String[] getColumns() {
        return new String[] {
                ConsumedFoodEntry.DATE,
                ConsumedFoodEntry.MEAL,
                ConsumedFoodEntry.AMOUNT,
                ConsumedFoodEntry.FOOD_ID,
                ConsumedFoodEntry.PORTION_ID,
        };
    }

    @Override
    protected ConsumedFood mapEntityFromCursor(Cursor cursor) {
        ConsumedFood consumedFood = new ConsumedFood();
        consumedFood.setId(getLong(cursor, ConsumedFoodEntry.ID));
        consumedFood.setAmount(getDouble(cursor, ConsumedFoodEntry.AMOUNT));
        consumedFood.setDate(parseDate(getString(cursor, ConsumedFoodEntry.DATE)));
        consumedFood.setMeal(parseMeal(getInt(cursor, ConsumedFoodEntry.MEAL)));

        consumedFood.setFood(DatabaseFoodRepository.mapFood(cursor));
        consumedFood.setPortion(DatabasePortionRepository.mapPortion(cursor));

        return consumedFood;
    }
    
    private String formatDate(LocalDate date) {
        return date.format(DateTimeFormatter.ISO_DATE);
    }

    private LocalDate parseDate(String str) {
        return LocalDate.parse(str, DateTimeFormatter.ISO_DATE);
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
            case OTHER:
                return 6;
            default:
                return null;
        }
    }

    private Meal parseMeal(int id) {
        switch (id) {
            case 1:
                return Meal.BREAKFAST;
            case 2:
                return Meal.LUNCH;
            case 3:
                return Meal.DINNER;
            case 4:
                return Meal.SNACK1;
            case 5:
                return Meal.SNACK2;
            case 6:
                return Meal.OTHER;
            default:
                return null;
        }
    }
}
