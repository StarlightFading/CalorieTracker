package rh.calorietracker.feature.dayoverview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rh.calorietracker.R;
import rh.calorietracker.common.RecyclerViewAdapter;
import rh.calorietracker.entity.ConsumedFood;

public class ConsumedFoodAdapter extends RecyclerViewAdapter<ConsumedFood, ConsumedFoodAdapter.ViewHolder> {

    public ConsumedFoodAdapter(List<ConsumedFood> items) {
        super(items);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_consumed_food, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);

        ConsumedFood consumedFood = getItem(position);

        holder.textName.setText(consumedFood.getFood().getName());

        int portionSize = consumedFood.getPortion() != null ? consumedFood.getPortion().getAmount() : 100;
        double calories = portionSize * consumedFood.getAmount() * consumedFood.getFood().getCalories() / 100;

        holder.textCalories.setText(String.valueOf(calories));
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text_consumed_food_name)
        TextView textName;

        @BindView(R.id.text_consumed_food_calories)
        TextView textCalories;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
