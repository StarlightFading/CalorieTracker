package rh.calorietracker.data.impl;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.List;

import rh.calorietracker.data.FoodRepository;
import rh.calorietracker.data.impl.DatabaseSchema.FoodEntry;
import rh.calorietracker.entity.Food;

public class DatabaseFoodRepository extends DatabaseRepository<Food> implements FoodRepository {

    public static final String TAG = "DatabaseFoodRepository";
    private final DatabaseHelper dbHelper = DatabaseHelper.getInstance();

    @Override
    public List<Food> findAll() {
        return query(null, null, FoodEntry.NAME + " ASC");
    }

    @Override
    protected String getTableName() {
        return FoodEntry._TABLE_NAME;
    }

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    protected ContentValues getContentValues(Food food) {
        ContentValues values = new ContentValues();
        values.put(FoodEntry.NAME, food.getName());
        values.put(FoodEntry.CALORIES, food.getCalories());
        values.put(FoodEntry.PROTEIN, food.getProtein());
        values.put(FoodEntry.CARBS, food.getCarbs());
        values.put(FoodEntry.FAT, food.getFat());
        return values;
    }

    @Override
    protected String[] getColumns() {
        return new String[] {
                FoodEntry._ID,
                FoodEntry.NAME,
                FoodEntry.CALORIES,
                FoodEntry.PROTEIN,
                FoodEntry.CARBS,
                FoodEntry.FAT };
    }

    @Override
    protected Food mapEntityFromCursor(Cursor cursor) {
        Food food = new Food();
        food.setId(getLong(cursor, FoodEntry._ID));
        food.setName(getString(cursor, FoodEntry.NAME));
        food.setCalories(getInt(cursor, FoodEntry.CALORIES));
        food.setProtein(getInt(cursor, FoodEntry.PROTEIN));
        food.setCarbs(getInt(cursor, FoodEntry.CARBS));
        food.setFat(getInt(cursor, FoodEntry.FAT));
        return food;
    }
}
