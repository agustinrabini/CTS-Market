package com.example.ctsmarket05.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ctsmarket05.R;
import com.example.ctsmarket05.activities.clickListeners.OrdersOnCustomClickListener;
import com.example.ctsmarket05.entities.Orders;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.OrdersViewHolder> {

    private ArrayList<Orders> orders = new ArrayList<>();
    private final OrdersOnCustomClickListener listener;

    public OrdersAdapter(OrdersOnCustomClickListener listener) {
        this.listener = listener;
    }

    @NotNull
    @Override
    public OrdersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cv_order, parent, false);
        return new OrdersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(OrdersViewHolder holder, int position) {
        holder.bind(orders.get(position),listener);
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public void setOrders(List<Orders> listOrders)  {
        orders = (ArrayList<Orders>) listOrders;
        notifyDataSetChanged();
    }

    public static class OrdersViewHolder extends RecyclerView.ViewHolder{

        public OrdersViewHolder(View itemView) {
            super(itemView);
        }

        private void bind (Orders order, OrdersOnCustomClickListener listener){

            ConstraintLayout clOrder = itemView.findViewById(R.id.cl_order);
            TextView tvQuantityProducts = itemView.findViewById(R.id.tv_quantity_productsO);
            TextView tvOrderPrice = itemView.findViewById(R.id.tv_id_orderO);
            TextView tvIdOrder = itemView.findViewById(R.id.tv_id_orderO);
            TextView tvShipping = itemView.findViewById(R.id.tv_shippingO);

            tvIdOrder.setText(String.valueOf(order.getId_order()));
            tvQuantityProducts.setText(String.valueOf(order.getQuantity_products()));
            tvOrderPrice.setText(String.valueOf(order.getOrder_price()));
            tvShipping.setText(String.valueOf(order.getShipping()));

            clOrder.setOnClickListener(v -> {
                listener.onItemClick(order,getAdapterPosition());
            });
        }
    }
}

