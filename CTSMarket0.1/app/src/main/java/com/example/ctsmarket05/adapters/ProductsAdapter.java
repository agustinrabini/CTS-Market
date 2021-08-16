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
import com.example.ctsmarket05.clickListeners.ProductsOnCustomClickListener;
import com.example.ctsmarket05.activities.productsClasses.ProductsActivity2;
import com.example.ctsmarket05.entities.Product;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductsViewHolder> {

    private ArrayList<Product> products = new ArrayList<>();
    private final ProductsOnCustomClickListener listener;

    public ProductsAdapter(ProductsOnCustomClickListener listener) {
        this.listener = listener;
    }
    //el activity que corresponde a cada layout de los item de la lista
    @NotNull
    @Override
    public ProductsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cv_producto, parent, false);
        return new ProductsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductsViewHolder holder, int position) {
        holder.bind(products.get(position),listener);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public void setProducts(List<Product> listProducts)  {
        products = (ArrayList<Product>) listProducts;
        notifyDataSetChanged();
    }

    public static class ProductsViewHolder extends RecyclerView.ViewHolder{

        public ProductsViewHolder(View itemView) {
            super(itemView);
        }

        private void bind (Product product, ProductsOnCustomClickListener listener){

            ImageView ivImage = itemView.findViewById(R.id.iv_product_cv);
            TextView tvName = itemView.findViewById(R.id.tv_name1);
            TextView tvPrice = itemView.findViewById(R.id.tv_price1);
            TextView tvLength = itemView.findViewById(R.id.tv_length1);
            TextView tvStock = itemView.findViewById(R.id.tv_stock);
            ConstraintLayout constraintLayoutProd = itemView.findViewById(R.id.cl_product1);

            Integer id_product = product.getId_product();
            Integer stock = product.getStock();

            tvName.setText(product.getName());
            tvPrice.setText(String.valueOf(product.getPrice()));
            tvLength.setText(String.valueOf("Longitud hoja " + product.getLength())+"cm");
            Picasso.with(itemView.getContext()).load(product.getImage()).into(ivImage);

            if (stock == 0 ){
                tvStock.setText("Stock no disponible");

            }else{
                tvStock.setText("Stock disponible");
            }

            constraintLayoutProd.setOnClickListener(v -> {
                listener.onItemClick(product,getAdapterPosition());

                Intent clicked = new Intent(v.getContext(), ProductsActivity2.class);

                clicked.putExtra("name", product.getName());
                clicked.putExtra("blade", product.getBlade());
                clicked.putExtra("brand", product.getBrand());
                clicked.putExtra("description", product.getDescription());
                clicked.putExtra("image", product.getImage());
                clicked.putExtra("price", product.getPrice());
                clicked.putExtra("length", product.getLength());
                clicked.putExtra("stock", product.getStock());
                clicked.putExtra("id_product", id_product);

                v.getContext().startActivity(clicked);
            });
        }
    }
}
