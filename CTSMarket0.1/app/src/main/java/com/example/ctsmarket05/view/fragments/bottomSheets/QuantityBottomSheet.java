package com.example.ctsmarket05.view.fragments.bottomSheets;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.ctsmarket05.R;
import com.example.ctsmarket05.entities.Product;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class QuantityBottomSheet extends BottomSheetDialogFragment {

    private QuantityListener mListener;
    private Integer stockQuantity;
    private Button btnQuantity1;
    private Button btnQuantity2;
    private Button btnQuantity3;
    private Button btnQuantity4;
    private Button btnQuantity5;
    private TextView tvBsQuantity;

    public interface QuantityListener {
        void onButtonClicked(Integer quantity);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.bottom_sheet_quantity, container, false);

        btnQuantity1 = v.findViewById(R.id.btn_quantity_1);
        btnQuantity2 = v.findViewById(R.id.btn_quantity_2);
        btnQuantity3 = v.findViewById(R.id.btn_quantity_3);
        btnQuantity4 = v.findViewById(R.id.btn_quantity_4);
        btnQuantity5 = v.findViewById(R.id.btn_quantity_5);
        tvBsQuantity = v.findViewById(R.id.tv_bsQuantity);

        stock();

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

    public void stock(){

        Bundle mArgs = getArguments();
        Integer stock = mArgs.getInt("stock");

        switch (stock) {

            case 0: {
                tvBsQuantity.setText("No hay stock");
                ((ViewGroup) btnQuantity5.getParent()).removeView(btnQuantity5);
                ((ViewGroup) btnQuantity4.getParent()).removeView(btnQuantity4);
                ((ViewGroup) btnQuantity3.getParent()).removeView(btnQuantity3);
                ((ViewGroup) btnQuantity2.getParent()).removeView(btnQuantity2);
                ((ViewGroup) btnQuantity1.getParent()).removeView(btnQuantity1);
            }
            break;

            case 1: {
                ((ViewGroup) btnQuantity5.getParent()).removeView(btnQuantity5);
                ((ViewGroup) btnQuantity4.getParent()).removeView(btnQuantity4);
                ((ViewGroup) btnQuantity3.getParent()).removeView(btnQuantity3);
                ((ViewGroup) btnQuantity2.getParent()).removeView(btnQuantity2);
            }
            break;

            case 2: {
                ((ViewGroup) btnQuantity5.getParent()).removeView(btnQuantity5);
                ((ViewGroup) btnQuantity4.getParent()).removeView(btnQuantity4);
                ((ViewGroup) btnQuantity3.getParent()).removeView(btnQuantity3);
            }
            break;

            case 3: {
                ((ViewGroup) btnQuantity5.getParent()).removeView(btnQuantity5);
                ((ViewGroup) btnQuantity4.getParent()).removeView(btnQuantity4);
            }
            break;

            case 4: {
                ((ViewGroup) btnQuantity5.getParent()).removeView(btnQuantity5);
            }
            break;

            default:
                break;
        }
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

