package rh.calorietracker.feature.foodeditor;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnTextChanged;
import rh.calorietracker.R;
import rh.calorietracker.entity.Food;

public class FoodEditorActivity extends AppCompatActivity {

    public static final int RESULT_DATA_CHANGED = 1;

    public static final String EXTRA_FOOD = "food";

    @BindView(R.id.edit_food_name)
    EditText editName;

    @BindView(R.id.edit_food_calories)
    EditText editCalories;

    @BindView(R.id.edit_food_protein)
    EditText editProtein;

    @BindView(R.id.edit_food_carbs)
    EditText editCarbs;

    @BindView(R.id.edit_food_fat)
    EditText editFat;

    private Menu menu;

    private FoodEditorPresenter presenter;

    private boolean editMode = false;

    public static Intent createIntent(Context context, Food food) {
        Intent intent = new Intent(context, FoodEditorActivity.class);
        intent.putExtra(EXTRA_FOOD, food);

        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_editor);
        ButterKnife.bind(this);

        Food food = null;
        if (getIntent().hasExtra(EXTRA_FOOD)) {
            editMode = true;

            food = (Food) getIntent().getSerializableExtra(EXTRA_FOOD);
            editName.setText(food.getName());
            editCalories.setText(String.valueOf(food.getCalories()));
            editProtein.setText(String.valueOf(food.getProtein()));
            editCarbs.setText(String.valueOf(food.getCarbs()));
            editFat.setText(String.valueOf(food.getFat()));
        }

         presenter = new FoodEditorPresenter(this, food);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.food_editor, menu);
        this.menu = menu;
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (editMode) {
            menu.findItem(R.id.action_save_food).setEnabled(true);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_save_food) {
            Food food = new Food(
                    editName.getEditableText().toString().trim(),
                    Integer.parseInt(editCalories.getEditableText().toString()),
                    Integer.parseInt(editProtein.getEditableText().toString()),
                    Integer.parseInt(editCarbs.getEditableText().toString()),
                    Integer.parseInt(editFat.getEditableText().toString()));

            presenter.onFoodSaved(food);

            setResult(RESULT_DATA_CHANGED);
            finish();
            return true;
        }

        return false;
    }

    @OnTextChanged(R.id.edit_food_name)
    public void onFoodNameChanged(CharSequence name) {
        if (menu != null) {
            MenuItem saveItem = menu.findItem(R.id.action_save_food);
            saveItem.setEnabled(!editName.getText().toString().trim().isEmpty());
        }
    }
}
