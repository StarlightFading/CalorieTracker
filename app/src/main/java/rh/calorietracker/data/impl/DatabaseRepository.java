package rh.calorietracker.data.impl;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public abstract class DatabaseRepository<T extends DatabaseEntity> {

    protected final DatabaseHelper dbHelper = DatabaseHelper.getInstance();

    public void create(T entity) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        db.beginTransaction();
        try {
            Long id = db.insert(getTableName(), null, getContentValues(entity));
            entity.setId(id);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.e(getTag(), "Unable to insert row: " + e.getMessage());
        } finally {
            db.endTransaction();
        }
    }

    public void update(T entity) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        db.beginTransaction();
        try {
            db.update(
                    getTableName(),
                    getContentValues(entity),
                    getIdColumnName() + "=?",
                    new String[] { String.valueOf(entity.getId()) });

            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.e(getTag(), "Unable to update row: " + e.getMessage());
        } finally {
            db.endTransaction();
        }
    }

    public void delete(T entity) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        db.beginTransaction();
        try {
            db.delete(
                    getTableName(),
                    getIdColumnName() + "=?",
                    new String[] { String.valueOf(entity.getId()) });

            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.e(getTag(), "Unable to delete row: " + e.getMessage());
        } finally {
            db.endTransaction();
        }
    }

    protected abstract String getTableName();

    protected abstract String getIdColumnName();

    protected abstract String getTag();

    protected abstract ContentValues getContentValues(T entity);

    protected abstract String[] getColumns();

    protected abstract T mapEntityFromCursor(Cursor cursor);

    protected List<T> query(String where, String[] whereArgs, String orderBy) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        List<T> results = new ArrayList<>();

        try {
            Cursor cursor = db.query(
                    getTableName(),
                    getColumns(),
                    where,
                    whereArgs,
                    null,
                    null,
                    orderBy);

            while (cursor.moveToNext()) {
                results.add(mapEntityFromCursor(cursor));
            }

            cursor.close();
        } catch (SQLException e) {
            Log.e(getTag(), "Unable to get row: " + e.toString());
        }

        return results;
    }

    protected static Long getLong(Cursor cursor, String column) {
        return cursor.getLong(cursor.getColumnIndexOrThrow(column));
    }

    protected static String getString(Cursor cursor, String column) {
        return cursor.getString(cursor.getColumnIndexOrThrow(column));
    }

    protected static int getInt(Cursor cursor, String column) {
        return cursor.getInt(cursor.getColumnIndexOrThrow(column));
    }

    protected static double getDouble(Cursor cursor, String column) {
        return cursor.getDouble(cursor.getColumnIndexOrThrow(column));
    }
}
