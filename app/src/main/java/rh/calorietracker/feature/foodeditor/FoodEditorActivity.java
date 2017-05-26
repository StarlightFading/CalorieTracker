package rh.calorietracker.feature.foodeditor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnTextChanged;
import rh.calorietracker.R;
import rh.calorietracker.entity.Food;

public class FoodEditorActivity extends AppCompatActivity {

    public static final int RESULT_DATA_CHANGED = 1;

    @BindView(R.id.edit_food_name)
    EditText editName;

    @BindView(R.id.edit_food_calories)
    EditText editCalories;

    private Menu menu;

    private final FoodEditorPresenter presenter = new FoodEditorPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_editor);
        ButterKnife.bind(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.food_editor, menu);
        this.menu = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_save_food) {
            presenter.onFoodSaved(
                    new Food(editName.getEditableText().toString().trim(),
                            Integer.parseInt(editCalories.getEditableText().toString())));

            setResult(RESULT_DATA_CHANGED);
            finish();
            return true;
        }

        return false;
    }

    @OnTextChanged(R.id.edit_food_name)
    public void onFoodNameChanged(CharSequence name) {
        MenuItem saveItem = menu.findItem(R.id.action_save_food);
        saveItem.setEnabled(!editName.getText().toString().trim().isEmpty());
    }
}
