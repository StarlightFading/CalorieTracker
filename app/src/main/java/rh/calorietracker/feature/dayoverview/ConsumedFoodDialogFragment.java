package rh.calorietracker.feature.dayoverview;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemSelected;
import rh.calorietracker.R;
import rh.calorietracker.entity.ConsumedFood;
import rh.calorietracker.entity.Food;
import rh.calorietracker.entity.Meal;
import rh.calorietracker.entity.Portion;

public class ConsumedFoodDialogFragment extends DialogFragment {

    public static final String ARG_PORTIONS = "portions";
    public static final String ARG_FOOD = "food";

    @BindView(R.id.text_food_name)
    TextView textFoodName;

    @BindView(R.id.spinner_portions)
    Spinner spinnerPortions;

    @BindView(R.id.spinner_meal)
    Spinner spinnerMeal;

    @BindView(R.id.edit_consumed_food_amount)
    EditText editAmount;

    private OnDialogAcceptedListener onDialogAcceptedListener;

    private Food food;
    private ConsumedFood consumedFood;

    public static Bundle createArguments(Food food, ArrayList<Portion> portions) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_FOOD, food);
        args.putSerializable(ARG_PORTIONS, portions);

        return args;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        @SuppressLint("InflateParams")
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_consumed_food, null);

        ButterKnife.bind(this, view);

        builder.setView(view)
                .setPositiveButton(R.string.save, null)
                .setNegativeButton(android.R.string.cancel, null);

        setupView();

        return builder.create();
    }

    private void setupView() {
        Bundle args = getArguments();

        food = (Food) args.getSerializable(ARG_FOOD);
        textFoodName.setText(food.getName());

        consumedFood = new ConsumedFood();
        consumedFood.setFood(food);

        ArrayList<Portion> portions = (ArrayList<Portion>) args.getSerializable(ARG_PORTIONS);
        List<String> portionStrings = new ArrayList<>();

        for (Portion portion : portions) {
            portionStrings.add(String.format("%s (%s g)", portion.getName(), String.valueOf(portion.getAmount())));
        }

        portionStrings.add(getString(R.string.custom));

        spinnerPortions.setAdapter(new ArrayAdapter<>(
                getContext(),
                android.R.layout.simple_spinner_item,
                portionStrings));

        spinnerMeal.setAdapter(new ArrayAdapter<>(
                getContext(),
                android.R.layout.simple_spinner_item,
                getResources().getStringArray(R.array.meals)));
    }

    @Override
    public void onStart() {
        super.onStart();

        Button positiveButton = ((AlertDialog) getDialog()).getButton(DialogInterface.BUTTON_POSITIVE);
        positiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateInput()) {
                    if (onDialogAcceptedListener != null) {
                        // todo build consumed food

                        consumedFood.setAmount(Double.parseDouble(editAmount.getText().toString()));

                        onDialogAcceptedListener.onDialogAccepted(consumedFood);
                    }

                    dismiss();
                }
            }
        });

        //noinspection ConstantConditions
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
    }

    private boolean validateInput() {
        if (editAmount.getText().toString().trim().isEmpty()) {
            editAmount.setError(getString(R.string.error_input_empty));
            return false;
        }

        return true;
    }

    @OnItemSelected(R.id.spinner_meal)
    public void mealSelected(Spinner spinner, int position) {
        switch (position) {
            case 0:
                consumedFood.setMeal(Meal.BREAKFAST);
                break;
            case 1:
                consumedFood.setMeal(Meal.LUNCH);
                break;
            case 2:
                consumedFood.setMeal(Meal.DINNER);
                break;
            case 3:
                consumedFood.setMeal(Meal.SNACK1);
                break;
            case 4:
                consumedFood.setMeal(Meal.SNACK2);
                break;
            case 5:
                consumedFood.setMeal(Meal.OTHER);
                break;
        }
    }

    public void setOnDialogAcceptedListener(OnDialogAcceptedListener onDialogAcceptedListener) {
        this.onDialogAcceptedListener = onDialogAcceptedListener;
    }

    public interface OnDialogAcceptedListener {
        void onDialogAccepted(ConsumedFood consumedFood);
    }
}
