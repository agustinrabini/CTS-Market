package com.example.ctsmarket05.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ctsmarket05.R;
import com.example.ctsmarket05.clickListeners.ProductsOnCustomClickListener;
import com.example.ctsmarket05.entities.Product;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ExhibitionAdapter extends RecyclerView.Adapter<ExhibitionAdapter.ExibitionViewHolder > {

    private ArrayList<Product> exibition = new ArrayList<>();
    private final ProductsOnCustomClickListener listener;

    public ExhibitionAdapter(ProductsOnCustomClickListener listener) {
        this.listener = listener;
    }

    @NotNull
    @Override
    public ExibitionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cv_exhibicion, parent, false);
        return new ExibitionViewHolder(view);
    }

    @Override
    public void onBindViewHolder( ExibitionViewHolder holder, int position) {
        holder.bind(exibition.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return exibition.size();
    }

    public void setExhibition(List<Product> listProducts)  {
        exibition = (ArrayList<Product>) listProducts;
        notifyDataSetChanged();
    }

    public class ExibitionViewHolder extends RecyclerView.ViewHolder {

        public ExibitionViewHolder (View itemView) {
            super(itemView);
        }

       public void bind (Product product, ProductsOnCustomClickListener listener){

         ImageView ivImageEx = itemView.findViewById(R.id.iv_image_ex);
         TextView tvNameEx = itemView.findViewById(R.id.tv_name_ex);
         TextView tvDescriptionEx = itemView.findViewById(R.id.tv_description_ex);
         TextView tvLengthEx = itemView.findViewById(R.id.tv_length_ex);
         ConstraintLayout constraintLayoutEx = itemView.findViewById(R.id.cl_layout_ex);

         tvNameEx.setText(product.getName());
         tvDescriptionEx.setText(product.getDescription());
         tvLengthEx.setText(String.valueOf(product.getLength()));
         Picasso.with(itemView.getContext()).load(product.getImage()).into(ivImageEx);

         constraintLayoutEx.setOnClickListener(v -> {
             listener.onItemClick(product,getAdapterPosition());
         });
       }
    }
}
