package rh.calorietracker.feature.dayoverview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import rh.calorietracker.R;
import rh.calorietracker.feature.foodlist.FoodListActivity;

public class DayOverviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_overview);
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
}
