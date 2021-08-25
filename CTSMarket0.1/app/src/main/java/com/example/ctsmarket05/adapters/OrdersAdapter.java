package com.example.ctsmarket05.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ctsmarket05.R;
import com.example.ctsmarket05.activities.OrderExpandedActivity;
import com.example.ctsmarket05.clickListeners.OrdersOnCustomClickListener;
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
            TextView tvOrderPrice = itemView.findViewById(R.id.tv_order_priceO);
            TextView tvOrderState = itemView.findViewById(R.id.tv_stateO);
            TextView tvOptions = itemView.findViewById(R.id.tv_optionsO);
            TextView tvDate = itemView.findViewById(R.id.tv_dateO);

            Integer id_order = order.getId_order();
            Integer state = order.getOrder_state();

            tvQuantityProducts.setText(String.valueOf(order.getQuantity_products()));
            tvOrderPrice.setText(String.valueOf("$ARS " + order.getOrder_price()));
            tvDate.setText(order.getDate());

            switch (state){

                case 0:
                    tvOrderState.setText("En producción");
                   break;

                case 1:
                    tvOrderState.setText("Listo para envío");
                    break;

                case 2:
                    tvOrderState.setText("Enviado");
                    break;

                case 3:
                    tvOrderState.setText("En camino");
                    break;

                case 4:
                    tvOrderState.setText("Entregado");
                    break;
            }

            tvOptions.setOnClickListener(v -> {
                listener.onItemClick(order,getAdapterPosition());

                Intent fromOrdersActivity = new Intent(v.getContext(), OrderExpandedActivity.class);
                fromOrdersActivity.putExtra("id_order", id_order);
                v.getContext().startActivity(fromOrdersActivity);
            });
        }
    }
}

