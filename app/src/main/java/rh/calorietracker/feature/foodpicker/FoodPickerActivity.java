package rh.calorietracker.feature.foodpicker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rh.calorietracker.R;
import rh.calorietracker.common.RecyclerViewAdapter;
import rh.calorietracker.entity.Food;
import rh.calorietracker.feature.common.FoodListAdapter;

public class FoodPickerActivity extends AppCompatActivity implements FoodPickerContract.View {

    public static final String EXTRA_FOOD = "food";
    public static final int RESULT_FOOD_PICKED = 1;

    @BindView(R.id.recycler_foods)
    RecyclerView recyclerFoods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_picker);
        ButterKnife.bind(this);

        recyclerFoods.setLayoutManager(new LinearLayoutManager(this));
        recyclerFoods.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        FoodPickerPresenter presenter = new FoodPickerPresenter(this);
        presenter.onFoodListRequested();
    }

    @Override
    public void displayFoods(List<Food> foods) {
        FoodListAdapter adapter = new FoodListAdapter(foods);

        adapter.setOnClickListener(new RecyclerViewAdapter.OnClickListener<Food>() {
            @Override
            public void onItemClicked(Food food) {
                Intent data = new Intent();
                data.putExtra(EXTRA_FOOD, food);
                setResult(RESULT_FOOD_PICKED, data);
                finish();
            }
        });

        recyclerFoods.setAdapter(adapter);
    }
}
