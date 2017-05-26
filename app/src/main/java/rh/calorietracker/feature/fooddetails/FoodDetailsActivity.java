package rh.calorietracker.feature.fooddetails;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.Serializable;

import butterknife.BindView;
import butterknife.ButterKnife;
import rh.calorietracker.R;
import rh.calorietracker.entity.Food;

public class FoodDetailsActivity extends AppCompatActivity {

    public static final String EXTRA_FOOD = "food";

    @BindView(R.id.text_food_name)
    TextView textName;

    @BindView(R.id.text_food_calories)
    TextView textCalories;

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

        setupView();
    }

    private void setupView() {
        Food food = (Food) getIntent().getSerializableExtra(EXTRA_FOOD);

        textName.setText(food.getName());
        textCalories.setText(String.valueOf(food.getCalories()));

        setTitle(food.getName());
    }
}
