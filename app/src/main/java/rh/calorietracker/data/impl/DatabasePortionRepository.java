package rh.calorietracker.data.impl;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.List;

import rh.calorietracker.data.PortionRepository;
import rh.calorietracker.data.impl.DatabaseSchema.PortionEntry;
import rh.calorietracker.entity.Food;
import rh.calorietracker.entity.Portion;

public class DatabasePortionRepository extends DatabaseRepository<Portion> implements PortionRepository {

    private static final String TAG = "DatabasePortionRepository";

    @Override
    public List<Portion> findForFood(Food food) {
        List<Portion> portions = query(
                PortionEntry.FOOD_ID + "=?",
                new String[] { String.valueOf(food.getId()) },
                PortionEntry.AMOUNT);

        for (Portion portion : portions) {
            portion.setFood(food);
        }

        return portions;
    }

    @Override
    protected String getTableName() {
        return PortionEntry._TABLE_NAME;
    }

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    protected ContentValues getContentValues(Portion portion) {
        ContentValues values = new ContentValues();
        values.put(PortionEntry.NAME, portion.getName());
        values.put(PortionEntry.AMOUNT, portion.getAmount());
        values.put(PortionEntry.FOOD_ID, portion.getFood().getId());

        return values;
    }

    @Override
    protected String[] getColumns() {
        return new String[] {
                PortionEntry._ID,
                PortionEntry.NAME,
                PortionEntry.AMOUNT,
        };
    }

    @Override
    protected Portion mapEntityFromCursor(Cursor cursor) {
        Portion portion = new Portion();
        portion.setId(getLong(cursor, PortionEntry._ID));
        portion.setName(getString(cursor, PortionEntry.NAME));
        portion.setAmount(getInt(cursor, PortionEntry.AMOUNT));

        return portion;
    }
}
