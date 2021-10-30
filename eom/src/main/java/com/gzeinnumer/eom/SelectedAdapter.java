package com.gzeinnumer.eom;

import android.content.Context;
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

public class SelectedAdapter<T> extends RecyclerView.Adapter<SelectedAdapter.MyHolder<T>> implements Filterable {
    private Context context;
    private List<T> list;
    private List<T> listFilter;
    private OnItemClickListener onItemClickListener;

    public SelectedAdapter(List<T> list) {
        this.list = new ArrayList<>(list);
        this.listFilter = new ArrayList<>(list);
    }

    public void add(T data) {
        list.add(data);
        notifyItemInserted(list.size() - 1);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
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
            list.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

    public void setList(List<T> list) {
        this.list = new ArrayList<>(list);
        this.listFilter = new ArrayList<>(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.context = parent.getContext();

        return new MyHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item__dynamicoptionsmenu_selected, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        holder.bind(list.get(position), onItemClickListener);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public static class MyHolder<T> extends RecyclerView.ViewHolder {
        private View itemView;
        private ImageView img;
        private TextView tvTitle;
        private CardView cv;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            this.img = itemView.findViewById(R.id.img);
            this.tvTitle = itemView.findViewById(R.id.tv_title);
            this.cv = itemView.findViewById(R.id.cv);
        }

        public void bind(T data, OnItemClickListener onItemClickListener) {
            tvTitle.setText(data.toString());
            if (getAdapterPosition()>0){
                img.setVisibility(View.VISIBLE);
            } else {
                img.setVisibility(View.GONE);
            }
            if (onItemClickListener != null) {
                //performClick
                cv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onItemClickListener.onItemClick(getAdapterPosition());
                    }
                });
            }
        }
    }
}