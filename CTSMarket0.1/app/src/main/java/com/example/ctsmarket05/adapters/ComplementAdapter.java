package com.example.ctsmarket05.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ctsmarket05.R;
import com.example.ctsmarket05.activities.OnCustomClickListener;
import com.example.ctsmarket05.activities.productsClasses.ProductsActivity2;
import com.example.ctsmarket05.entities.Product;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


public class ComplementAdapter extends RecyclerView.Adapter<ComplementAdapter.ComplementViewHolder> {

    private ArrayList<Product> complement = new ArrayList<>();
    private final OnCustomClickListener listener;

    public ComplementAdapter( OnCustomClickListener listener) {
        this.listener = listener;
    }

    @NotNull
    @Override
    public ComplementViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cv_complemento, parent, false);
        return new ComplementViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ComplementViewHolder holder, int position) {
        holder.bind(complement.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return complement.size();
    }

    public void setComplement(List<Product> listProducts)  {
        complement = (ArrayList<Product>) listProducts;
        notifyDataSetChanged();
    }

    public static class ComplementViewHolder extends RecyclerView.ViewHolder{

        public ComplementViewHolder(View itemView) {
            super(itemView);
        }

        private void bind (Product product, OnCustomClickListener listener){

            ImageView ivImageComp = itemView.findViewById(R.id.iv_image_comp);
            TextView tvNameComp = itemView.findViewById(R.id.tv_name_comp);
            TextView tvPriceComp = itemView.findViewById(R.id.tv_price_comp);
            ConstraintLayout constraintLayoutComp = itemView.findViewById(R.id.cl_complement);

            tvNameComp.setText(product.getName());
            tvPriceComp.setText(String.valueOf(product.getPrice()));
            Picasso.with(itemView.getContext()).load(product.getImage()).into(ivImageComp);

            constraintLayoutComp.setOnClickListener(v -> {
                listener.onItemClick(product, getAdapterPosition());

                Intent clicked = new Intent(v.getContext(), ProductsActivity2.class);
                //clicked.putExtra("product", getAdapterPosition());
                clicked.putExtra("name", product.getName());
                clicked.putExtra("blade", product.getBlade());
                clicked.putExtra("brand", product.getBrand());
                clicked.putExtra("description", product.getDescription());
                clicked.putExtra("image", product.getImage());
                clicked.putExtra("price", product.getPrice());
                clicked.putExtra("length", product.getLength());
                clicked.putExtra("stock", product.getStock());
                v.getContext().startActivity(clicked);
            });
        }
    }
}
