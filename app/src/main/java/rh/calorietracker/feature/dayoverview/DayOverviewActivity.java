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

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rh.calorietracker.R;
import rh.calorietracker.data.impl.DatabaseHelper;
import rh.calorietracker.entity.ConsumedFood;
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
        ConsumedFoodAdapter adapter = new ConsumedFoodAdapter(consumedFoods);
        recyclerConsumedFoods.setAdapter(adapter);
    }
}
