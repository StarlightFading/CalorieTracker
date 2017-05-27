package rh.calorietracker.feature.foodlist;

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

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rh.calorietracker.R;
import rh.calorietracker.common.RecyclerViewAdapter;
import rh.calorietracker.data.impl.DatabaseHelper;
import rh.calorietracker.entity.Food;
import rh.calorietracker.feature.fooddetails.FoodDetailsActivity;
import rh.calorietracker.feature.foodeditor.FoodEditorActivity;

public class FoodListActivity extends AppCompatActivity implements FoodListContract.View {

    public static final int REQUEST_FOOD_EDITOR = 1;
    @BindView(R.id.recycler_foods)
    RecyclerView recyclerFoods;

    private FoodListPresenter presenter;

    private final ActionMode.Callback actionModeCallback = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            mode.getMenuInflater().inflate(R.menu.food_list_action_mode, menu);
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
                    showFoodEditor();
                    break;
                case R.id.action_delete_food:
                    showDeleteFoodConfirmationDialog();
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
    private FoodListAdapter foodListAdapter;

    private void deleteSelectedFood() {
        presenter.onFoodDeleted(selectedFood);
        foodListAdapter.removeItem(selectedFood);
    }

    private ActionMode actionMode;

    private Food selectedFood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);
        ButterKnife.bind(this);

        DatabaseHelper.init(this); // TODO: move this

        setupView();

        presenter = new FoodListPresenter(this);
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
        foodListAdapter = new FoodListAdapter(foods);

        foodListAdapter.setOnClickListener(new RecyclerViewAdapter.OnClickListener() {
            @Override
            public void onItemClicked(Object item) {
                startActivity(FoodDetailsActivity.createIntent(FoodListActivity.this, (Food) item));
            }
        });

        foodListAdapter.setOnLongClickListener(new RecyclerViewAdapter.OnLongClickListener() {
            @Override
            public void onItemLongClicked(Object item) {
                if (actionMode == null) {
                    selectedFood = (Food) item;
                    actionMode = startSupportActionMode(actionModeCallback);
                }
            }
        });

        recyclerFoods.setAdapter(foodListAdapter);
    }

    private void showDeleteFoodConfirmationDialog() {
        new AlertDialog.Builder(this)
                .setMessage(R.string.message_delete_item_confirmation)
                .setPositiveButton(R.string.delete, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteSelectedFood();
                        selectedFood = null;
                    }
                })
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        selectedFood = null;
                    }
                })
                .create()
                .show();
    }

    private void showFoodEditor() {
        startActivityForResult(FoodEditorActivity.createIntent(this, selectedFood), REQUEST_FOOD_EDITOR);
    }
}
