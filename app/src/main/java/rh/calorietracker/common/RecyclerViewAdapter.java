package rh.calorietracker.common;

import android.support.annotation.CallSuper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import rh.calorietracker.entity.Food;

public abstract class RecyclerViewAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    private final List<T> items = new ArrayList<>();

    private OnClickListener onClickListener;

    private OnLongClickListener onLongClickListener;

    public RecyclerViewAdapter(List<T> items) {
        if (!items.isEmpty()) {
            this.items.addAll(items);
        }
    }

    @Override
    public abstract VH onCreateViewHolder(ViewGroup parent, int viewType);

    @Override
    @CallSuper
    public void onBindViewHolder(VH holder, int position) {
        final T item = items.get(position);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickListener != null) {
                    onClickListener.onItemClicked(item);
                }
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (onLongClickListener != null) {
                    onLongClickListener.onItemLongClicked(item);
                    return true;
                }

                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(T item) {
        int position = items.size();
        items.add(item);
        notifyItemInserted(position);
    }

    public void removeItem(T item) {
        int position = items.indexOf(item);
        items.remove(position);
        notifyItemRemoved(position);
    }

    protected T getItem(int position) {
        return items.get(position);
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public void setOnLongClickListener(OnLongClickListener onLongClickListener) {
        this.onLongClickListener = onLongClickListener;
    }

    public interface OnClickListener<T> {
        void onItemClicked(T item);
    }

    public interface OnLongClickListener<T> {
        void onItemLongClicked(T item);
    }
}
