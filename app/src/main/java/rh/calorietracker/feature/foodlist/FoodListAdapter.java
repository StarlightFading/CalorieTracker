package rh.calorietracker.feature.foodlist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rh.calorietracker.R;
import rh.calorietracker.entity.Food;

public class FoodListAdapter extends RecyclerView.Adapter<FoodListAdapter.ViewHolder> {

    private final List<Food> foodList = new ArrayList<>();

    private OnClickListener onClickListener;

    private OnLongClickListener onLongClickListener;

    public FoodListAdapter(List<Food> foodList) {
        if (!foodList.isEmpty()) {
            this.foodList.addAll(foodList);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_food, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Food food = foodList.get(position);
        holder.textName.setText(food.getName());
        holder.textCalories.setText(String.valueOf(food.getCalories()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickListener != null) {
                    onClickListener.onItemClicked(food);
                }
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (onLongClickListener != null) {
                    onLongClickListener.onItemLongClicked(food);
                    return true;
                }

                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    public void removeItem(Food food) {
        int position = foodList.indexOf(food);
        foodList.remove(position);
        notifyItemRemoved(position);
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public void setOnLongClickListener(OnLongClickListener onLongClickListener) {
        this.onLongClickListener = onLongClickListener;
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

    public interface OnClickListener {
        void onItemClicked(Food food);
    }

    public interface OnLongClickListener {
        void onItemLongClicked(Food food);
    }
}
