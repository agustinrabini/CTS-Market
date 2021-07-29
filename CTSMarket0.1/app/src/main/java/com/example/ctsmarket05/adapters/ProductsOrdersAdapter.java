package com.example.ctsmarket05.adapters;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ctsmarket05.R;
import com.example.ctsmarket05.activities.OrdersActivity;
import com.example.ctsmarket05.activities.productsClasses.ProductsActivity2;
import com.example.ctsmarket05.clickListeners.ProductsOrdersOnCustomClickListener;
import com.example.ctsmarket05.entities.Product;
import com.example.ctsmarket05.entities.ProductsOrder;
import com.example.ctsmarket05.retrofit.ordersRetrofit.OrderGetByIdUser;
import com.example.ctsmarket05.retrofit.ordersRetrofit.OrderSubtractPricePUT;
import com.example.ctsmarket05.retrofit.productRetrofit.ProductGET;
import com.example.ctsmarket05.retrofit.productsOrderRetrofit.ProductsOrderDELETE;
import com.squareup.picasso.Picasso;

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
            TextView tvInfo = itemView.findViewById(R.id.tv_product_namePO);
            TextView tvPrice = itemView.findViewById(R.id.tv_pricePO);
            TextView tvQuantity = itemView.findViewById(R.id.tv_quantityPO);
            TextView tvSeeProduct = itemView.findViewById(R.id.tv_see_productPO);
            ImageView ivProduct = itemView.findViewById(R.id.iv_product_order);

            //obtengo el id_products_order correspondiente el cual se le pasa a la funcion delete
            Integer id_products_order = productsOrder.getId_products_order();

            //setteo el valor quantity que le voy a pasar a la funcion delete
            Integer quantity = productsOrder.getQuantity();
            Product.QUANTITY= quantity;

            //obtengo el id_product del producto que se encuentra en el carrito
            Integer id_product= productsOrder.getId_product();

            //obtengo valores de cada producto y los muestro
            ProductGET productGET = new ProductGET();
            productGET.SetOnDataListenerProd(product -> {

                Integer price = product.getPrice()*quantity;

                //setteo el valor price que le voy a pasar a la funcion delete
                Product.PRICE=price;

                tvPrice.setText(price.toString() + "$ARS");
                tvQuantity.setText("Cantidad: " + quantity.toString());
                tvInfo.setText(product.getName());
                Picasso.with(itemView.getContext()).load(product.getImage()).into(ivProduct);
            });
            productGET.getProduct(id_product);


            //borra un producto de un carrito activo
            tvDelete.setOnClickListener(v -> {

                //update con los valores obtenido mas arriba
                ProductsOrderDELETE productsOrderDELETE = new ProductsOrderDELETE();
                productsOrderDELETE.removeProductOrder(id_products_order);

                OrderGetByIdUser orderGetByIdUser = new OrderGetByIdUser();
                orderGetByIdUser.SetOnDataListenerOrdersPO(order -> {

                    Integer id_order = order.getId_order();
                    Integer orderState = order.getOrder_state();

                    //si es nulo es porque es la primera vez que se crea el carrito
                    if (id_order != null){
                        OrderSubtractPricePUT subtractPricePUT = new OrderSubtractPricePUT();
                        subtractPricePUT.subtractPrice(Product.PRICE,Product.QUANTITY,id_order);
                    }

                    if (orderState == 0){
                        Toast.makeText(itemView.getContext(), "No es posible elmiminar un producto luego de haber realizado la compra", Toast.LENGTH_SHORT).show();
                    }
                });
                orderGetByIdUser.OrderGetByIdUser();

                Intent reload = new Intent(v.getContext(), OrdersActivity.class);
                v.getContext().startActivity(reload);
            });

            //ver el objeto de la orden en detalle.
            tvSeeProduct.setOnClickListener(v -> {

                ProductGET productGET2 = new ProductGET();
                productGET2.SetOnDataListenerProd(product -> {

                    Integer id = product.getId_product();
                    String id_prod = id.toString();

                    Intent clicked = new Intent(v.getContext(), ProductsActivity2.class);

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
                productGET2.getProduct(id_product);
            });
        }
    }
}