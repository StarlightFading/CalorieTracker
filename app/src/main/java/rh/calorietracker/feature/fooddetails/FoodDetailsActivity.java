package rh.calorietracker.feature.fooddetails;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rh.calorietracker.R;
import rh.calorietracker.entity.Food;
import rh.calorietracker.entity.Portion;

public class FoodDetailsActivity extends AppCompatActivity implements FoodDetailsContract.View {

    public static final String EXTRA_FOOD = "food";

    @BindView(R.id.text_food_name)
    TextView textName;

    @BindView(R.id.text_food_calories)
    TextView textCalories;

    @BindView(R.id.text_food_protein)
    TextView textProtein;

    @BindView(R.id.text_food_carbs)
    TextView textCarbs;

    @BindView(R.id.text_food_fat)
    TextView textFat;

    @BindView(R.id.recycler_portions)
    RecyclerView recyclerPortions;

    private final FoodDetailsPresenter presenter = new FoodDetailsPresenter(this);

    public static Intent createIntent(Context context, Food food) {
        Intent intent = new Intent(context, FoodDetailsActivity.class);
        intent.putExtra(EXTRA_FOOD, food);

        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_details);
        ButterKnife.bind(this);

        Food food = (Food) getIntent().getSerializableExtra(EXTRA_FOOD);
        setupView(food);
        presenter.onPortionsListRequested(food);
    }

    private void setupView(Food food) {
        recyclerPortions.setLayoutManager(new LinearLayoutManager(this));
        recyclerPortions.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        textName.setText(food.getName());
        textCalories.setText(String.valueOf(food.getCalories()));
        textProtein.setText(String.valueOf(food.getProtein()));
        textCarbs.setText(String.valueOf(food.getCarbs()));
        textFat.setText(String.valueOf(food.getFat()));

        setTitle(food.getName());
    }

    @Override
    public void displayPortions(List<Portion> portions) {
        PortionAdapter adapter = new PortionAdapter(portions);
        recyclerPortions.setAdapter(adapter);
    }
}
