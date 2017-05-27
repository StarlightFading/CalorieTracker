package rh.calorietracker.feature.fooddetails;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
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

    private FoodDetailsPresenter presenter;

    private PortionAdapter portionAdapter;

    private final ActionMode.Callback actionModeCallback = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            mode.getMenuInflater().inflate(R.menu.food_details_action_mode, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()) {
                case R.id.action_edit_food:
                    showEditPortionDialog();
                    break;
                case R.id.action_delete_food:
                    showDeletePortionConfirmationDialog();
                    break;
                default:
                    return false;
            }

            mode.finish();
            return true;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            actionMode = null;
        }
    };

    private void showDeletePortionConfirmationDialog() {
        // TODO: implement
    }

    private void showEditPortionDialog() {
        // TODO: implement
    }

    private ActionMode actionMode;

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

        presenter = new FoodDetailsPresenter(this, food);
        presenter.onPortionsListRequested();
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
        portionAdapter = new PortionAdapter(portions);
        recyclerPortions.setAdapter(portionAdapter);
    }

    @OnClick(R.id.button_add_portion)
    public void onAddPortionButtonClicked() {
        PortionDialogFragment dialog = new PortionDialogFragment();

        dialog.setOnDialogAcceptedListener(new PortionDialogFragment.OnDialogAcceptedListener() {
            @Override
            public void onDialogAccepted(Portion portion) {
                presenter.onPortionCreated(portion);
                portionAdapter.addItem(portion);
            }
        });

        dialog.show(getFragmentManager(), "PortionDialogFragment");
    }
}
