package rh.calorietracker.data.impl;

import android.provider.BaseColumns;

import org.threeten.bp.LocalDate;

import rh.calorietracker.entity.Food;
import rh.calorietracker.entity.Meal;
import rh.calorietracker.entity.Portion;

// TODO: add on delete cascade where appropriate

public class DatabaseSchema {

    public static final class FoodEntry implements BaseColumns {

        public static final String _TABLE_NAME = "food";

        public static final String NAME = "name";
        public static final String CALORIES ="calories";
        public static final String PROTEIN ="protein";
        public static final String CARBS ="carbs";
        public static final String FAT ="fat";

        public static final String _SQL_CREATE_TABLE =
                "CREATE TABLE " + _TABLE_NAME + " ("
                + _ID + " INTEGER PRIMARY KEY,"
                + NAME + " TEXT NOT NULL,"
                + CALORIES + " INTEGER,"
                + PROTEIN + " INTEGER,"
                + CARBS + " INTEGER,"
                + FAT + " INTEGER)";

        public static final String _SQL_DELETE_TABLE =
                "DROP TABLE IF EXISTS " + _TABLE_NAME;
    }

    public static final class PortionEntry implements BaseColumns {

        public static final String _TABLE_NAME = "portion";

        public static final String NAME = "name";
        public static final String AMOUNT = "amount";
        public static final String FOOD_ID = "food_id";

        public static final String _SQL_CREATE_TABLE =
                "CREATE TABLE " + _TABLE_NAME + " ("
                + _ID + " INTEGER PRIMARY KEY,"
                + NAME + " TEXT NOT NULL,"
                + AMOUNT + " INTEGER,"
                + FOOD_ID + " INTEGER NOT NULL REFERENCES " + FoodEntry._TABLE_NAME + ")";

        public static final String _SQL_DELETE_TABLE =
                "DROP TABLE IF EXISTS " + _TABLE_NAME;
    }

    public static final class ConsumedFoodEntry implements BaseColumns {

        public static final String _TABLE_NAME = "consumed_food";

        public static final String DATE = "date";
        public static final String MEAL = "meal";
        public static final String AMOUNT = "amount";
        public static final String FOOD_ID = "food_id";
        public static final String PORTION_ID = "portion_id";

        public static final String _SQL_CREATE_TABLE =
                "CREATE TABLE " + _TABLE_NAME + " ("
                        + _ID + " INTEGER PRIMARY KEY,"
                        + DATE + " TEXT NOT NULL,"
                        + AMOUNT + " REAL NOT NULL,"
                        + MEAL + " INTEGER,"
                        + FOOD_ID + " INTEGER NOT NULL REFERENCES " + FoodEntry._TABLE_NAME + ","
                        + PORTION_ID + " INTEGER REFERENCES " + PortionEntry._TABLE_NAME + ")";

        public static final String _SQL_DELETE_TABLE =
                "DROP TABLE IF EXISTS " + _TABLE_NAME;
    }
}
