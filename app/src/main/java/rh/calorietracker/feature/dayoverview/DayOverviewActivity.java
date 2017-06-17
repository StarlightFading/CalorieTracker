package rh.calorietracker.feature.dayoverview;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.jakewharton.threetenabp.AndroidThreeTen;

import org.threeten.bp.LocalDate;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rh.calorietracker.R;
import rh.calorietracker.common.SectionAdapter;
import rh.calorietracker.data.impl.DatabaseHelper;
import rh.calorietracker.entity.ConsumedFood;
import rh.calorietracker.entity.Food;
import rh.calorietracker.entity.Meal;
import rh.calorietracker.entity.Portion;
import rh.calorietracker.feature.foodlist.FoodListActivity;
import rh.calorietracker.feature.foodpicker.FoodPickerActivity;

public class DayOverviewActivity extends AppCompatActivity implements DayOverviewContract.View {

    private static final int REQUEST_FOOD_PICKER = 1;

    @BindView(R.id.recycler_consumed_foods)
    RecyclerView recyclerConsumedFoods;

    private Result result;

    private DayOverviewPresenter presenter;

    private ConsumedFoodAdapter consumedFoodAdapter;

    private final ActionMode.Callback actionModeCallback = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            mode.getMenuInflater().inflate(R.menu.day_overview_action_mode, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()) {
                  case R.id.action_delete_consumed_food:
                    showDeleteConsumedFoodConfirmationDialog();
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

    private ActionMode actionMode;
    private ConsumedFood selectedConsumedFood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_overview);
        ButterKnife.bind(this);

        AndroidThreeTen.init(this); // TODO: move this to an application class
        DatabaseHelper.init(this); // TODO: move this to an application class

        recyclerConsumedFoods.setLayoutManager(new LinearLayoutManager(this));
        recyclerConsumedFoods.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        presenter = new DayOverviewPresenter(this);
        presenter.onConsumedFoodListRequested();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.day_overview, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_show_food_list:
                showFoodList();
                break;
            case R.id.action_add_consumed_food:
                showFoodPicker();
                break;
            default:
                return false;
        }

        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        result = new Result(requestCode, resultCode, data);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();

        if (result == null) {
            return;
        }

        if (result.getRequestCode() == REQUEST_FOOD_PICKER
                && result.getResultCode() == FoodPickerActivity.RESULT_FOOD_PICKED) {
            Food food = (Food) result.getData().getSerializableExtra(FoodPickerActivity.EXTRA_FOOD);
            presenter.onRequestConsumedFoodDialog(food);
        }

        result = null;
    }

    private void showFoodPicker() {
        startActivityForResult(new Intent(this, FoodPickerActivity.class), REQUEST_FOOD_PICKER);
    }

    private void showFoodList() {
        startActivity(new Intent(this, FoodListActivity.class));
    }

    @Override
    public void displayConsumedFoodList(List<ConsumedFood> consumedFoods) {
        consumedFoodAdapter = new ConsumedFoodAdapter();

        for (Meal meal : Meal.values()) {
            consumedFoodAdapter.addItems(getMealString(meal), getConsumedFoodsByMeal(consumedFoods, meal));
        }

        consumedFoodAdapter.setOnLongClickListener(new SectionAdapter.OnLongClickListener() {
            @Override
            public void onItemLongClicked(Object item) {
                selectedConsumedFood = (ConsumedFood) item;
                startSupportActionMode(actionModeCallback);
            }
        });

        recyclerConsumedFoods.setAdapter(consumedFoodAdapter);
    }

    @Override
    public void displayConsumedFoodDialog(Food food, List<Portion> portions) {
        ConsumedFoodDialogFragment dialogFragment = new ConsumedFoodDialogFragment();
        dialogFragment.setArguments(ConsumedFoodDialogFragment.createArguments(food, new ArrayList<>(portions)));

        dialogFragment.setOnDialogAcceptedListener(new ConsumedFoodDialogFragment.OnDialogAcceptedListener() {
            @Override
            public void onDialogAccepted(ConsumedFood consumedFood) {
                consumedFood.setDate(LocalDate.now());

                presenter.onConsumedFoodAdded(consumedFood);
                presenter.onConsumedFoodListRequested();
            }
        });

        dialogFragment.show(getSupportFragmentManager(), "ConsumedFoodDialogFragment");
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
            case OTHER:
                return getString(R.string.other);
        }

        return "";
    }

    private void showDeleteConsumedFoodConfirmationDialog() {
        new AlertDialog.Builder(this)
                .setMessage(R.string.message_delete_item_confirmation)
                .setPositiveButton(R.string.delete, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteSelectedConsumedFood();
                        selectedConsumedFood = null;
                    }
                })
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        selectedConsumedFood = null;
                    }
                })
                .create()
                .show();
    }

    private void deleteSelectedConsumedFood() {
        presenter.onConsumedFoodDeleted(selectedConsumedFood);
        consumedFoodAdapter.removeItem(selectedConsumedFood);
    }

    private static class Result {
        private final int requestCode;
        private final int resultCode;
        private final Intent data;

        public Result(int requestCode, int resultCode, Intent data) {
            this.requestCode = requestCode;
            this.resultCode = resultCode;
            this.data = data;
        }

        public int getRequestCode() {
            return requestCode;
        }

        public int getResultCode() {
            return resultCode;
        }

        public Intent getData() {
            return data;
        }
    }
}
