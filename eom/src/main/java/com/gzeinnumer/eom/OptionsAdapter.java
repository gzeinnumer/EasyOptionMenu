package com.gzeinnumer.eom;

import android.content.Context;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class OptionsAdapter<T> extends RecyclerView.Adapter<OptionsAdapter.MyHolder<T>> implements Filterable {
    private Context context;
    private List<T> list;
    private List<T> listFilter;
    private OnItemClickListener<T> onItemClickListener;
    private SizeCallBack sizeCallBack;
    private int currentCallBack;
    private int levelSize;

    public OptionsAdapter(List<T> list) {
        this.list = new ArrayList<>(list);
        this.listFilter = new ArrayList<>(list);
    }

    public void setOnItemClickListener(OnItemClickListener<T> onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setSizeCallBack(SizeCallBack sizeCallBack) {
        this.sizeCallBack = sizeCallBack;
    }

    private final Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<T> fildteredList = new ArrayList<>();
            if (constraint != null || constraint.length() != 0) {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (T item : listFilter) {
                    if (item.toString().toLowerCase().contains(filterPattern)) {
                        fildteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = fildteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            list.clear();
            list.addAll((List<T>) results.values);
            sizeCallBack.sizeAfterFilter(list.size());
            notifyDataSetChanged();
        }
    };

    public void setList(List<T> list, int currentCallBack, int levelSize) {
        this.currentCallBack = currentCallBack;
        this.levelSize = levelSize;
        this.list = new ArrayList<>(list);
        this.listFilter = new ArrayList<>(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyHolder<T> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        return new MyHolder<T>(LayoutInflater.from(parent.getContext()).inflate(R.layout.item__dynamicoptionsmenu, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        holder.bind(list.get(position), onItemClickListener, currentCallBack, levelSize);

        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) holder.cardView.getLayoutParams();
        int first_last = 6;
        int left_right = 6;
        int space = 10;
        int center = space / 2;
        if (position == 0) {
            layoutParams.setMargins(intToDp(left_right), intToDp(first_last), intToDp(left_right), intToDp(center));
            holder.cardView.setLayoutParams(layoutParams);
        } else if (position == list.size() - 1) {
            layoutParams.setMargins(intToDp(left_right), intToDp(center), intToDp(left_right), intToDp(first_last));
            holder.cardView.setLayoutParams(layoutParams);
        } else {
            layoutParams.setMargins(intToDp(left_right), intToDp(center), intToDp(left_right), intToDp(center));
            holder.cardView.setLayoutParams(layoutParams);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }

    public interface OnItemClickListener<T> {
        void onItemClick(T data);
    }

    public interface SizeCallBack {
        void sizeAfterFilter(int size);
    }

    public static class MyHolder<T> extends RecyclerView.ViewHolder {
        private View itemView;
        private TextView tvTitle;
        private ImageView img;
        private CardView cardView;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            this.tvTitle = itemView.findViewById(R.id.tv_title);
            this.img = itemView.findViewById(R.id.img);
            cardView = itemView.findViewById(R.id.cv);
        }

        public void bind(T data, OnItemClickListener onItemClickListener, int currentCallBack, int levelSize) {
            tvTitle.setText(data.toString());
            if (currentCallBack==levelSize-1){
                img.setVisibility(View.GONE);
            } else {
                img.setVisibility(View.VISIBLE);
            }
            if (onItemClickListener != null) {
                //performClick
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onItemClickListener.onItemClick(data);
                    }
                });
            }
        }
    }

    public int intToDp(int sizeInDPH){
        return  (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, sizeInDPH, context.getResources().getDisplayMetrics());
    }
}