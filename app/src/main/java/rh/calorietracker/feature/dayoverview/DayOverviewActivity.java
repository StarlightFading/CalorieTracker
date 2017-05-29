package rh.calorietracker.feature.dayoverview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.jakewharton.threetenabp.AndroidThreeTen;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rh.calorietracker.R;
import rh.calorietracker.data.impl.DatabaseHelper;
import rh.calorietracker.entity.ConsumedFood;
import rh.calorietracker.entity.Meal;
import rh.calorietracker.feature.foodlist.FoodListActivity;

public class DayOverviewActivity extends AppCompatActivity implements DayOverviewContract.View {

    @BindView(R.id.recycler_consumed_foods)
    RecyclerView recyclerConsumedFoods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_overview);
        ButterKnife.bind(this);

        AndroidThreeTen.init(this); // TODO: move this to an application class
        DatabaseHelper.init(this); // TODO: move this to an application class

        recyclerConsumedFoods.setLayoutManager(new LinearLayoutManager(this));
        recyclerConsumedFoods.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        DayOverviewPresenter presenter = new DayOverviewPresenter(this);
        presenter.onConsumedFoodListRequested();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.day_overview, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_show_food_list) {
            showFoodList();
            return true;
        }

        return false;
    }

    private void showFoodList() {
        startActivity(new Intent(this, FoodListActivity.class));
    }

    @Override
    public void displayConsumedFoodList(List<ConsumedFood> consumedFoods) {
        ConsumedFoodAdapter adapter = new ConsumedFoodAdapter();

        for (Meal meal : Meal.values()) {
            adapter.addItems(getMealString(meal), getConsumedFoodsByMeal(consumedFoods, meal));
        }

        adapter.addItems(getMealString(null), getConsumedFoodsByMeal(consumedFoods, null));

        recyclerConsumedFoods.setAdapter(adapter);
    }

    private List<ConsumedFood> getConsumedFoodsByMeal(List<ConsumedFood> consumedFoods, Meal meal) {
        List<ConsumedFood> results = new ArrayList<>();

        for (ConsumedFood consumedFood : consumedFoods) {
            if (consumedFood.getMeal() == meal) {
                results.add(consumedFood);
            }
        }

        return results;
    }

    private String getMealString(Meal meal) {
        if (meal == null) {
            return getString(R.string.other);
        }

        switch (meal) {
            case BREAKFAST:
                return getString(R.string.breakfast);
            case LUNCH:
                return getString(R.string.lunch);
            case DINNER:
                return getString(R.string.dinner);
            case SNACK1:
                return getString(R.string.snack1);
            case SNACK2:
                return getString(R.string.snack2);
            default:
                return getString(R.string.other);
        }
    }
}
