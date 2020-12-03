package com.example.prueba.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.prueba.PopUpHorariosNotif;
import com.example.prueba.PopUpNotifElim;
import com.example.prueba.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class FragmentConfiguracion2 extends Fragment {

    Button btnSelcH;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_configuracion2, container, false);

        FloatingActionButton buttonRegresar;
        btnSelcH=(Button)view.findViewById(R.id.buttonSeleccH);
        buttonRegresar=(FloatingActionButton)view.findViewById(R.id.floatingActionBRegresarConf);

        btnSelcH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PopUpHorariosNotif.class);
                getActivity().startActivity(intent);
            }
        });

        buttonRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.container, new MainFragment());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        FragmentTransaction fr = getFragmentManager().beginTransaction();
        fr.addToBackStack(null);
        fr.commit();
        return view;
    }
}
