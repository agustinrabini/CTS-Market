package com.example.ctsmarket05.view.fragments;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ctsmarket05.R;
import com.example.ctsmarket05.view.activities.InfoActivity;

public class InfoFragment extends Fragment {

    private TextView tvGmail;
    private TextView tvFace;
    private TextView tvPhone;
    private TextView tvQuestions;

    public InfoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private boolean appInstalledOrNot(String uri) {
        PackageManager pm = getActivity().getPackageManager();
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
        }

        return false;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_info, container, false);


        tvGmail = v.findViewById(R.id.tv_gmail_info);
        tvFace = v.findViewById(R.id.tv_facebook_info);
        tvPhone = v.findViewById(R.id.tv_phone_info);
        tvQuestions = v.findViewById(R.id.tv_questions_info);

        tvGmail.setOnClickListener(b -> {
            String email = "agustinrabini99@gmail.com";
            Intent intentG = new Intent(Intent.ACTION_SEND, Uri.parse(email));
            intentG.setType("plain/text");
            intentG.putExtra(Intent.EXTRA_EMAIL, new String[]{"agustinrabini99@gmail.com"});

            try {
                startActivity(Intent.createChooser(intentG, "Seleccione una app"));
            } catch (android.content.ActivityNotFoundException ex) {
                Toast.makeText(getActivity().getApplicationContext(), "No tienes aplciaciones de mensajeria por correo instaladas en tu celular", Toast.LENGTH_LONG).show();
            }
        });

        tvPhone.setOnClickListener(b -> {
            String telefono = "+54 1132424233";
            String url = "https://api.whatsapp.com/send?phone=";
            Intent llamada = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:" + telefono));
            llamada.setData(Uri.parse(url + telefono));
            startActivity(llamada);
        });

        tvFace.setOnClickListener(b -> {

            String url = "https://www.facebook.com/CuchillosTitanDelSur";
            boolean isAppInstalled = appInstalledOrNot("com.facebook.katana");

            if (isAppInstalled) {

                Intent face = new Intent(Intent.ACTION_VIEW);
                face.setData(Uri.parse("fb://facewebmodal/f?href=" + url));
                startActivity(face);

            } else {
                Intent faceweb = new Intent(Intent.ACTION_VIEW);
                faceweb.setData(Uri.parse(url));
                startActivity(faceweb);
            }
        });

        tvQuestions.setOnClickListener(v1 -> {

            Intent info = new Intent(getContext(), InfoActivity.class);
            startActivity(info);
        });
        return v;
    }
}
