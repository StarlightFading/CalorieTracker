package rh.calorietracker.feature.fooddetails;

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
import rh.calorietracker.entity.Portion;

public class PortionAdapter extends RecyclerViewAdapter<Portion, PortionAdapter.ViewHolder> {

    public PortionAdapter(List<Portion> items) {
        super(items);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_portion, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);

        Portion portion = getItem(position);
        holder.name.setText(portion.getName());
        holder.amount.setText(String.valueOf(portion.getAmount()));

        int calories = portion.getFood().getCalories() * portion.getAmount() / 100;
        holder.calories.setText(String.valueOf(calories));
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text_portion_name)
        TextView name;

        @BindView(R.id.text_portion_amount)
        TextView amount;

        @BindView(R.id.text_portion_calories)
        TextView calories;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
