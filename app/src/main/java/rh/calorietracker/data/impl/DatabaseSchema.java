package rh.calorietracker.data.impl;

import android.provider.BaseColumns;

import org.threeten.bp.LocalDate;

import rh.calorietracker.entity.Food;
import rh.calorietracker.entity.Meal;
import rh.calorietracker.entity.Portion;

// TODO: add on delete cascade where appropriate

public class DatabaseSchema {

    public static final class FoodEntry {

        public static final String _TABLE_NAME = "food";

        public static final String ID = "food_id";
        public static final String NAME = "food_name";
        public static final String CALORIES ="food_calories";
        public static final String PROTEIN ="food_protein";
        public static final String CARBS ="food_carbs";
        public static final String FAT ="food_fat";

        public static final String _SQL_CREATE_TABLE =
                "CREATE TABLE " + _TABLE_NAME + " ("
                + ID + " INTEGER PRIMARY KEY,"
                + NAME + " TEXT NOT NULL,"
                + CALORIES + " INTEGER,"
                + PROTEIN + " INTEGER,"
                + CARBS + " INTEGER,"
                + FAT + " INTEGER)";

        public static final String _SQL_DELETE_TABLE =
                "DROP TABLE IF EXISTS " + _TABLE_NAME;
    }

    public static final class PortionEntry {

        public static final String _TABLE_NAME = "portion";

        public static final String ID = "portion_id";
        public static final String NAME = "portion_name";
        public static final String AMOUNT = "portion_amount";
        public static final String FOOD_ID = "portion_food_id";

        public static final String _SQL_CREATE_TABLE =
                "CREATE TABLE " + _TABLE_NAME + " ("
                + ID + " INTEGER PRIMARY KEY,"
                + NAME + " TEXT NOT NULL,"
                + AMOUNT + " INTEGER,"
                + FOOD_ID + " INTEGER NOT NULL REFERENCES " + FoodEntry._TABLE_NAME + ")";

        public static final String _SQL_DELETE_TABLE =
                "DROP TABLE IF EXISTS " + _TABLE_NAME;
    }

    public static final class ConsumedFoodEntry {

        public static final String _TABLE_NAME = "consumed_food";

        public static final String ID = "consumed_food_id";
        public static final String DATE = "consumed_food_date";
        public static final String MEAL = "consumed_food_meal";
        public static final String AMOUNT = "consumed_food_amount";
        public static final String FOOD_ID = "consumed_food_food_id";
        public static final String PORTION_ID = "consumed_food_portion_id";

        public static final String _SQL_CREATE_TABLE =
                "CREATE TABLE " + _TABLE_NAME + " ("
                        + ID + " INTEGER PRIMARY KEY,"
                        + DATE + " TEXT NOT NULL,"
                        + AMOUNT + " REAL NOT NULL,"
                        + MEAL + " INTEGER,"
                        + FOOD_ID + " INTEGER NOT NULL REFERENCES " + FoodEntry._TABLE_NAME + ","
                        + PORTION_ID + " INTEGER REFERENCES " + PortionEntry._TABLE_NAME + ")";

        public static final String _SQL_DELETE_TABLE =
                "DROP TABLE IF EXISTS " + _TABLE_NAME;

        static final String SQL_FIND_FOR_DATE =
                "SELECT "
                        + ID
                        + "," + AMOUNT
                        + "," + DATE
                        + "," + MEAL
                        + "," + FoodEntry.ID
                        + "," + FoodEntry.NAME
                        + "," + FoodEntry.CALORIES
                        + "," + FoodEntry.PROTEIN
                        + "," + FoodEntry.CARBS
                        + "," + FoodEntry.FAT
                        + "," + PortionEntry.ID
                        + "," + PortionEntry.NAME
                        + "," + PortionEntry.AMOUNT
                        + " FROM " + _TABLE_NAME
                        + " LEFT JOIN " + FoodEntry._TABLE_NAME
                        + " ON " + _TABLE_NAME + "." + FOOD_ID + " = " + FoodEntry._TABLE_NAME + "." + FoodEntry.ID
                        + " LEFT JOIN " + PortionEntry._TABLE_NAME
                        + " ON " + _TABLE_NAME + "." + PORTION_ID + " = " + PortionEntry._TABLE_NAME + "." + PortionEntry.ID
                        + " WHERE " + DATE + "=?";
    }
}
