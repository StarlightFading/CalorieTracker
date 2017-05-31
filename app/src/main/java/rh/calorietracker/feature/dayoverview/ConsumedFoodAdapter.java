package rh.calorietracker.feature.dayoverview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import rh.calorietracker.R;
import rh.calorietracker.common.SectionAdapter;
import rh.calorietracker.entity.ConsumedFood;

public class ConsumedFoodAdapter extends SectionAdapter<ConsumedFood, ConsumedFoodAdapter.ViewHolder> {

    @Override
    protected void bindItemViewHolder(ViewHolder holder, ConsumedFood consumedFood) {
        holder.textName.setText(consumedFood.getFood().getName());

        int portionSize = consumedFood.getPortion() != null ? consumedFood.getPortion().getAmount() : 100;
        double calories = portionSize * consumedFood.getAmount() * consumedFood.getFood().getCalories() / 100;

        holder.textCalories.setText(formatDouble(calories));

        if (consumedFood.getPortion() != null) {
            String portionText = String.format(
                    Locale.getDefault(),
                    "%s (%s g) x %s",
                    consumedFood.getPortion().getName(),
                    consumedFood.getPortion().getAmount(),
                    formatDouble(consumedFood.getAmount()));

            holder.textPortionName.setText(portionText);
        } else {
            String portionText = formatDouble(consumedFood.getAmount() * 100) + " g";
            holder.textPortionName.setText(portionText);
        }
    }

    @Override
    protected ViewHolder createItemViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_consumed_food, parent, false);
        return new ViewHolder(view);
    }

    private String formatDouble(double number) {
        if (number == (long) number) {
            return Long.toString((long) number);
        } else {
            return Double.toString(number);
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text_consumed_food_name)
        TextView textName;

        @BindView(R.id.text_consumed_food_calories)
        TextView textCalories;

        @BindView(R.id.text_consumed_food_portion_name)
        TextView textPortionName;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
