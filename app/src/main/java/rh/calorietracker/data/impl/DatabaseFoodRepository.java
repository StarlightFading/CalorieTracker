package rh.calorietracker.data.impl;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import rh.calorietracker.data.FoodRepository;
import rh.calorietracker.data.impl.DatabaseSchema.FoodEntry;
import rh.calorietracker.entity.Food;

public class DatabaseFoodRepository implements FoodRepository {

    public static final String TAG = "DatabaseFoodRepository";
    private final DatabaseHelper dbHelper = DatabaseHelper.getInstance();

    @Override
    public List<Food> findAll() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        List<Food> foods = new ArrayList<>();

        try {
            Cursor cursor = db.query(
                    FoodEntry._TABLE_NAME,
                    new String[] {FoodEntry._ID, FoodEntry.NAME, FoodEntry.CALORIES, FoodEntry.PROTEIN, FoodEntry.CARBS, FoodEntry.FAT },
                    null, null, null, null, null);

            while (cursor.moveToNext()) {
                Food food = new Food();
                food.setId(getLong(cursor, FoodEntry._ID));
                food.setName(getString(cursor, FoodEntry.NAME));
                food.setCalories(getInt(cursor, FoodEntry.CALORIES));
                food.setProtein(getInt(cursor, FoodEntry.PROTEIN));
                food.setCarbs(getInt(cursor, FoodEntry.CARBS));
                food.setFat(getInt(cursor, FoodEntry.FAT));

                foods.add(food);
            }

            cursor.close();
        } catch (SQLException e) {
            Log.e(TAG, "Unable to get row: " + e.toString());
        }

        return foods;
    }

    @Override
    public void create(Food food) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(FoodEntry.NAME, food.getName());
        values.put(FoodEntry.CALORIES, food.getCalories());
        values.put(FoodEntry.PROTEIN, food.getProtein());
        values.put(FoodEntry.CARBS, food.getCarbs());
        values.put(FoodEntry.FAT, food.getFat());

        db.beginTransaction();
        try {
            Long id = db.insert(FoodEntry._TABLE_NAME, null, values);
            food.setId(id);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.e(TAG, "Unable to insert row: " + e.getMessage());
        } finally {
            db.endTransaction();
        }
    }

    @Override
    public void update(Food food) {
        // TODO: implement
    }

    @Override
    public void delete(Food food) {
        // TODO: implement
    }

    private Long getLong(Cursor cursor, String column) {
        return cursor.getLong(cursor.getColumnIndexOrThrow(column));
    }

    private String getString(Cursor cursor, String column) {
        return cursor.getString(cursor.getColumnIndexOrThrow(column));
    }

    private int getInt(Cursor cursor, String column) {
        return cursor.getInt(cursor.getColumnIndexOrThrow(column));
    }
}
