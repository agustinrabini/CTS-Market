package com.example.ctsmarket05.adapters;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ctsmarket05.R;
import com.example.ctsmarket05.clickListeners.FilterOnCustomClickListener;
import com.example.ctsmarket05.entities.Product;
import com.example.ctsmarket05.fragments.HomeFragment;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class FilterAdapter extends RecyclerView.Adapter<FilterAdapter.FilterViewHolder> {

    private ArrayList<String> filters = new ArrayList<>();

    private final FilterOnCustomClickListener listener;

    public FilterAdapter(FilterOnCustomClickListener listener) {
        this.listener = listener;
    }

    @NotNull
    @Override
    public FilterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cv_filters, parent, false);
        return new FilterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FilterViewHolder holder, int position) {
        holder.bind(filters.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return filters.size();
    }

    public void setFilters(ArrayList<String> filter)  {
        filters = (ArrayList<String>) filter;
        notifyDataSetChanged();
    }

    public static class FilterViewHolder extends RecyclerView.ViewHolder{

        public FilterViewHolder(View itemView) {
            super(itemView);
        }

        private void bind (String filter, FilterOnCustomClickListener listener){

            ConstraintLayout clFilter = itemView.findViewById(R.id.cl_filter);
            TextView tvFilter = itemView.findViewById(R.id.tv_filter_parameter);
            ImageView imageViewIcon = itemView.findViewById(R.id.iv_filter_button);

            tvFilter.setText(filter);


           clFilter.setOnClickListener(v -> {
               listener.onItemClick(filter,getAdapterPosition());

               Product.FILTER =filter;
               imageViewIcon.setVisibility(View.VISIBLE);
               tvFilter.setTextColor(Color.parseColor("#AE8B72"));
           });
        }
    }
}
