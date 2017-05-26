package rh.calorietracker.feature.foodlist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rh.calorietracker.R;
import rh.calorietracker.entity.Food;
import rh.calorietracker.feature.foodeditor.FoodEditorActivity;

public class FoodListActivity extends AppCompatActivity implements FoodListContract.View {

    public static final int REQUEST_FOOD_EDITOR = 1;
    @BindView(R.id.recycler_foods)
    RecyclerView recyclerFoods;

    private final FoodListPresenter presenter = new FoodListPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);
        ButterKnife.bind(this);
        setupView();

        presenter.onFoodListRequested();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.food_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_add_food) {
            startActivityForResult(new Intent(this, FoodEditorActivity.class), REQUEST_FOOD_EDITOR);
            return true;
        }

        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_FOOD_EDITOR && resultCode == FoodEditorActivity.RESULT_DATA_CHANGED) {
            presenter.onFoodListRequested();
        }
    }

    private void setupView() {
        recyclerFoods.setLayoutManager(new LinearLayoutManager(this));
        recyclerFoods.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    @Override
    public void displayFoods(List<Food> foods) {
        recyclerFoods.setAdapter(new FoodListAdapter(foods));
    }
}
