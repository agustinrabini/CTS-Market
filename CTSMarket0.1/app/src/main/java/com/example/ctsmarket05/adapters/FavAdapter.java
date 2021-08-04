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
import com.example.ctsmarket05.activities.productsClasses.ProductsActivity2;
import com.example.ctsmarket05.clickListeners.FavOnCustomClickListener;
import com.example.ctsmarket05.clickListeners.ProductsOnCustomClickListener;
import com.example.ctsmarket05.entities.Favourite;
import com.example.ctsmarket05.entities.Product;
import com.example.ctsmarket05.retrofit.favouriteRetrofit.FavsGET;
import com.example.ctsmarket05.retrofit.productRetrofit.ProductGET;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class FavAdapter extends RecyclerView.Adapter<FavAdapter.FavViewHolder> {

    private ArrayList<Favourite> favorites = new ArrayList<>();
    private final FavOnCustomClickListener listener;

    public FavAdapter(FavOnCustomClickListener listener) {
        this.listener = listener;
    }
    //el activity que corresponde a cada layout de los item de la lista
    @NotNull
    @Override
    public FavViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cv_producto, parent, false);
        return new FavViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FavViewHolder holder, int position) {
        holder.bind(favorites.get(position),listener);
    }

    @Override
    public int getItemCount() {
        return favorites.size();
    }

    public void setFavs(List<Favourite> listFavorites)  {
        favorites = (ArrayList<Favourite>) listFavorites;
        notifyDataSetChanged();
    }

    public static class FavViewHolder extends RecyclerView.ViewHolder{

        public FavViewHolder(View itemView) {
            super(itemView);
        }

        private void bind (Favourite favourite, FavOnCustomClickListener listener){

            ImageView ivImage = itemView.findViewById(R.id.iv_product_cv);
            TextView tvName = itemView.findViewById(R.id.tv_name1);
            TextView tvPrice = itemView.findViewById(R.id.tv_price1);
            TextView tvLength = itemView.findViewById(R.id.tv_length1);
            TextView tvStock = itemView.findViewById(R.id.tv_stock);
            ConstraintLayout constraintLayoutProd = itemView.findViewById(R.id.cl_product1);

            Integer id = favourite.getId_product();
            String id_prod = id.toString();


            ProductGET productGET = new ProductGET();
            productGET.SetOnDataListenerProd(product -> {



            tvName.setText(product.getName());
            tvPrice.setText(String.valueOf(product.getPrice()));
            tvLength.setText(String.valueOf("Longitud hoja " + product.getLength())+"cm");
            Picasso.with(itemView.getContext()).load(product.getImage()).into(ivImage);

                Integer stock = product.getStock();
            if (stock == 0 ){
                tvStock.setText("Stock no disponible");

            }else{
                tvStock.setText("Stock disponible");
            }

            constraintLayoutProd.setOnClickListener(v -> {
                listener.onItemClick(favourite,getAdapterPosition());

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
                    clicked.putExtra("id_product", id_prod);

                    v.getContext().startActivity(clicked);
                });
            });
            productGET.getProduct(id);
        }
    }
}