package rh.calorietracker.feature.common;

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
import rh.calorietracker.entity.Food;

public class FoodListAdapter extends RecyclerViewAdapter<Food, FoodListAdapter.ViewHolder> {

    public FoodListAdapter(List<Food> items) {
        super(items);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_food, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);

        final Food food = getItem(position);
        holder.textName.setText(food.getName());
        holder.textCalories.setText(String.valueOf(food.getCalories()));
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text_food_name)
        TextView textName;

        @BindView(R.id.text_food_calories)
        TextView textCalories;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
