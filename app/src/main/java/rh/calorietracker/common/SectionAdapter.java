package rh.calorietracker.common;

import android.support.annotation.NonNull;
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

public abstract class SectionAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter {

    private static final int VIEW_TYPE_HEADER = 0;
    private static final int VIEW_TYPE_ITEM = 1;

    private final List<ItemWrapper> items = new ArrayList<>();

    public void addItems(@NonNull String section, @NonNull List<T> items) {
        this.items.add(new ItemWrapper(section));

        for (T item : items) {
            this.items.add(new ItemWrapper(item));
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;

        if (viewType == VIEW_TYPE_ITEM) {
            return createItemViewHolder(parent);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_header, parent, false);
            return new HeaderViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ItemWrapper itemWrapper = items.get(position);

        if (itemWrapper.isHeader()) {
            bindHeaderViewHolder((HeaderViewHolder) holder, itemWrapper);
        } else {
            bindItemViewHolder((VH) holder, itemWrapper.item);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (items.get(position).isHeader()) {
            return VIEW_TYPE_HEADER;
        } else {
            return VIEW_TYPE_ITEM;
        }
    }

    private void bindHeaderViewHolder(HeaderViewHolder viewHolder, ItemWrapper wrapper) {
        viewHolder.textSectionName.setText(wrapper.name);
    }

    protected abstract void bindItemViewHolder(VH viewHolder, T item);

    protected abstract VH createItemViewHolder(ViewGroup parent);

    public static class HeaderViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text_section_name)
        TextView textSectionName;

        public HeaderViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    private class ItemWrapper {
        private T item;

        private String name;

        public ItemWrapper(T item) {
            this.item = item;
        }

        public ItemWrapper(String name) {
            this.name = name;
        }

        public boolean isHeader() {
            return item == null;
        }
    }
}
