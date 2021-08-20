package com.example.ctsmarket05.activities.bottomSheets;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;

import com.example.ctsmarket05.R;
import com.example.ctsmarket05.fragments.HomeFragment;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class FilterBottomSheet extends BottomSheetDialogFragment {

    private FilterListenerBottomSheet filterListener;
    private HomeFragment homeFragment;
    private FilterBottomSheet filterBottomSheet;
    private Button btnTodos ;
    private Button btnCuchillos;
    private Button btnCuchillas;
    private Button btnTenedores;
    private Button btnChairas;
    private Button btnTrinchetes;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.bottom_sheet_filter, container, false);
        btnTodos = v.findViewById(R.id.btn_all);
        btnCuchillos = v.findViewById(R.id.btn_cuchillo);
        btnCuchillas = v.findViewById(R.id.btn_cuchillas);
        btnTenedores = v.findViewById(R.id.btn_tenedores);
        btnChairas = v.findViewById(R.id.btn_chairas);
        btnTrinchetes = v.findViewById(R.id.btn_trinchetes);

        homeFragment = (HomeFragment) getParentFragment();
        homeFragment.getView().findViewById(R.id.fmt_home);

        homeFragment = (HomeFragment) getParentFragment();
        homeFragment.getView().findViewById(R.id.fmt_home);

        btnTodos.setOnClickListener(v14 -> {
            filterListener.onButtonClickedFilter("*");
            dismiss();
        });

        btnCuchillos.setOnClickListener(v12 -> {
            filterListener.onButtonClickedFilter("Cuchillo");
            dismiss();
        });

        btnCuchillas.setOnClickListener(v2 -> {
            filterListener.onButtonClickedFilter("Cuchilla");
            dismiss();
        });

        btnTenedores.setOnClickListener(v3 -> {
            filterListener.onButtonClickedFilter("Tenedor");
            dismiss();
        });

        btnChairas.setOnClickListener(v4 -> {
            filterListener.onButtonClickedFilter("Chaira");
            dismiss();
        });

        btnTrinchetes.setOnClickListener(v5 -> {
            filterListener.onButtonClickedFilter("Trinchetes");
            dismiss();
        });

        return v;
    }

    public interface FilterListenerBottomSheet {
        void onButtonClickedFilter(String filterProduct);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            filterListener = (FilterListenerBottomSheet) getParentFragment();
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement FilterListenerBottomSheet");
        }
    }
}

