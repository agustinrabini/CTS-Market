package com.example.ctsmarket05.activities;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;

import com.example.ctsmarket05.R;
import com.example.ctsmarket05.entities.Product;
import com.example.ctsmarket05.retrofit.productRetrofit.ProductGET;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class QuantityBottomSheet extends BottomSheetDialogFragment {

    private QuantityListener mListener;
    private Integer stockQuantity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.bottom_sheet_quantity, container, false);

        Button btnQuantity1 = v.findViewById(R.id.btn_quantity_1);
        Button btnQuantity2 = v.findViewById(R.id.btn_quantity_2);
        Button btnQuantity3 = v.findViewById(R.id.btn_quantity_3);
        Button btnQuantity4 = v.findViewById(R.id.btn_quantity_4);
        Button btnQuantity5 = v.findViewById(R.id.btn_quantity_5);

        ((ViewGroup) btnQuantity5.getParent()).removeView(btnQuantity5);

        btnQuantity1.setOnClickListener(v12 -> {
            mListener.onButtonClicked(1);
            dismiss();
        });

        btnQuantity2.setOnClickListener(v1 -> {
            mListener.onButtonClicked(2);
            dismiss();
        });

        btnQuantity3.setOnClickListener(v1 -> {
            mListener.onButtonClicked(3);
            dismiss();
        });

        btnQuantity4.setOnClickListener(v1 -> {
            mListener.onButtonClicked(4);
            dismiss();
        });

        btnQuantity5.setOnClickListener(v1 -> {
            mListener.onButtonClicked(5);
            dismiss();
        });

        return v;
    }

    public Integer stock(){

       //ProductGET productGET = new ProductGET();
       //productGET.SetOnDataListenerProd(product -> {
       // Integer stock = product.getStock();

       //    switch (stock){

       //        case 1:{
       //            sequence = "cartSequence";
       //        }break;

       //        case 2:{
       //            sequence = "oneProductSequence";
       //        }break;
       //    }
       //});
       //
       return stockQuantity;
    }

    public interface QuantityListener {
        void onButtonClicked(Integer quantity);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mListener = (QuantityListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement QuantityListener");
        }
    }
}

