package com.example.ctsmarket05.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ctsmarket05.R;
import com.example.ctsmarket05.entities.User;
import com.example.ctsmarket05.interfaces.OPSActivityInterface;
import com.example.ctsmarket05.interfaces.clickListeners.ProductsOnCustomClickListener;
import com.example.ctsmarket05.model.ProductChecker;
import com.example.ctsmarket05.model.favourite.FavCheckGET;
import com.example.ctsmarket05.presenter.OPSActivityPresenter;
import com.example.ctsmarket05.view.activities.oneProductSequence.OPSActivity;
import com.example.ctsmarket05.entities.Product;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductsViewHolder> {

    private ArrayList<Product> products = new ArrayList<>();
    private final ProductsOnCustomClickListener listener;

    public ProductsAdapter(ProductsOnCustomClickListener listener) {
        this.listener = listener;
    }

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


    public static class ProductsViewHolder extends RecyclerView.ViewHolder {

        private Context ctx;
        private Integer favValue;

        private OPSActivityInterface view;
        private ProductChecker productsInteractor;
        private ProductsAdapter ProductsAdapter;

        public ProductsViewHolder(View itemView) {
            super(itemView);
        }

        private void bind (Product product, ProductsOnCustomClickListener listener){

            ImageView ivImage = itemView.findViewById(R.id.iv_product_cv);
            TextView tvName = itemView.findViewById(R.id.tv_name1);
            TextView tvPrice = itemView.findViewById(R.id.tv_price1);
            TextView tvLength = itemView.findViewById(R.id.tv_length1);
            ImageView ivFav = itemView.findViewById(R.id.iv_fav_1);
            ConstraintLayout constraintLayoutProd = itemView.findViewById(R.id.cl_product1);

            Integer id_product = product.getId_product();
            Integer stock = product.getStock();

            tvName.setText(product.getName());
            tvPrice.setText(String.valueOf(product.getPrice()));
            tvLength.setText(String.valueOf("Longitud hoja " + product.getLength())+"cm");
            Picasso.with(itemView.getContext()).load(product.getImage()).into(ivImage);


            FavCheckGET favCheckGET = new FavCheckGET();
            favCheckGET.SetOnDataListenerFavCheck(check -> {

                String c =  new String(check.getBytes());

                Integer e = Integer.parseInt(c);

                if (e == 10){
                    ivFav.setImageResource(R.drawable.ic_heart2);
                    e=null;
                    c=null;
                }
            });
            favCheckGET.check(User.IDUSER, id_product);

            constraintLayoutProd.setOnClickListener(v -> {
                listener.onItemClick(product,getAdapterPosition());

                Intent clicked = new Intent(v.getContext(), OPSActivity.class);
                clicked.putExtra("name", product.getName());
                clicked.putExtra("blade", product.getBlade());
                clicked.putExtra("brand", product.getBrand());
                clicked.putExtra("description", product.getDescription());
                clicked.putExtra("image", product.getImage());
                clicked.putExtra("price", product.getPrice());
                clicked.putExtra("length", product.getLength());
                clicked.putExtra("stock", product.getStock());
                clicked.putExtra("idProduct", id_product);

                ctx= v.getContext();
                ((Activity) ctx).finish();
                v.getContext().startActivity(clicked);
            });
        }
    }
}
