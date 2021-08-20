package com.example.ctsmarket05.activities.bottomSheets;

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

public class InfoProdActv3BottomSheet extends BottomSheetDialogFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.bottom_sheet_info_pa3, container, false);

        return v;
    }

}

