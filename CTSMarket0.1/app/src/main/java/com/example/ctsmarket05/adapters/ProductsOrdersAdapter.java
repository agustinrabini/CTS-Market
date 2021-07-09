package com.example.ctsmarket05.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ctsmarket05.R;
import com.example.ctsmarket05.activities.CartExpandedActivity;
import com.example.ctsmarket05.activities.OrdersActivity;
import com.example.ctsmarket05.clickListeners.ProductsOrdersOnCustomClickListener;
import com.example.ctsmarket05.entities.Product;
import com.example.ctsmarket05.entities.ProductsOrder;
import com.example.ctsmarket05.retrofit.ordersRetrofit.OrderGetByIdUser;
import com.example.ctsmarket05.retrofit.ordersRetrofit.OrderSubtractPricePUT;
import com.example.ctsmarket05.retrofit.productRetrofit.ProductGET;
import com.example.ctsmarket05.retrofit.productsOrderRetrofit.ProductsOrderDELETE;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ProductsOrdersAdapter extends RecyclerView.Adapter<ProductsOrdersAdapter.ProductsOrdersViewHolder> {

    private ArrayList<ProductsOrder> productsOrders = new ArrayList<>();
    private final ProductsOrdersOnCustomClickListener listener;

    public ProductsOrdersAdapter(ProductsOrdersOnCustomClickListener listener) {
        this.listener = listener;
    }

    @NotNull
    @Override
    public ProductsOrdersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cv_products_order, parent, false);
        return new ProductsOrdersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductsOrdersViewHolder holder, int position) {
        holder.bind(productsOrders.get(position),listener);
    }

    @Override
    public int getItemCount() {
        return productsOrders.size();
    }

    public void setProductsOrders(List<ProductsOrder> listProductsOrder)  {
        productsOrders = (ArrayList<ProductsOrder>) listProductsOrder;
        notifyDataSetChanged();
    }

    public static class ProductsOrdersViewHolder extends RecyclerView.ViewHolder{

        public ProductsOrdersViewHolder(View itemView) {
            super(itemView);
        }

        private void bind (ProductsOrder productsOrder, ProductsOrdersOnCustomClickListener listener){

            ConstraintLayout clProductsOrder = itemView.findViewById(R.id.cl_prodcuts_order);
            TextView tvDelete = itemView.findViewById(R.id.tv_deletePO);
            TextView tvInfo = itemView.findViewById(R.id.tv_product_infoPO);

            //obtengo el id_products_order correspondiente el cual se le pasa a la funcion delete
            Integer id_products_order = productsOrder.getId_products_order();

            //setteo el valor quantity que le voy a pasar a la funcion delete
            Integer quantity = productsOrder.getQuantity();
            Product.QUANTITY= quantity;

            //obtengo el id_product del producto que se encuentra en el carrito
            Integer id_product= productsOrder.getId_product();

            //obtengo sus valores y los muestro
            ProductGET productGET = new ProductGET();
            productGET.SetOnDataListenerProd(product -> {
                Integer price = product.getPrice()*Product.QUANTITY;

                //setteo el valor price que le voy a pasar a la funcion delete
                Product.PRICE=price;
                tvInfo.setText(price.toString());
            });
            productGET.getProduct(id_product);

            tvDelete.setOnClickListener(v -> {

                //update con los valores obtenido mas arriba
                ProductsOrderDELETE productsOrderDELETE = new ProductsOrderDELETE();
                productsOrderDELETE.removeProductOrder(id_products_order);

                OrderGetByIdUser orderGetByIdUser = new OrderGetByIdUser();
                orderGetByIdUser.SetOnDataListenerOrdersPO(order -> {

                    Integer id_order = order.getId_order();
                    //si es nulo es porque es la primera vez que se crea el carrito
                    if (id_order != null){
                        OrderSubtractPricePUT subtractPricePUT = new OrderSubtractPricePUT();
                        subtractPricePUT.subtractPrice(Product.PRICE,Product.QUANTITY,id_order);
                    }
                });
                orderGetByIdUser.OrderGetByIdUser();

                Intent reload = new Intent(v.getContext(), OrdersActivity.class);
                v.getContext().startActivity(reload);
            });
        }
    }
}