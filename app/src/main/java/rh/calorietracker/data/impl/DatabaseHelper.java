package rh.calorietracker.data.impl;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "calorietracker.db";
    private static final int DB_VERSION = 3;

    private static DatabaseHelper instance;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public static void init(Context context) {
        instance = new DatabaseHelper(context.getApplicationContext());
    }

    public static DatabaseHelper getInstance() {
        if (instance == null) {
            throw new IllegalStateException("DatabaseHelper must be initialized first");
        }

        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DatabaseSchema.FoodEntry._SQL_CREATE_TABLE);
        db.execSQL(DatabaseSchema.PortionEntry._SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DatabaseSchema.FoodEntry._SQL_DELETE_TABLE);
        db.execSQL(DatabaseSchema.PortionEntry._SQL_DELETE_TABLE);

        onCreate(db);
    }
}
