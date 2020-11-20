package com.example.prueba.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.prueba.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class FragmentTratamiento extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tratamiento, container, false);
        FloatingActionButton btnAnadirTrat = (FloatingActionButton) view.findViewById(R.id.floatingActionButton);
        FloatingActionButton btnModTrat = (FloatingActionButton) view.findViewById(R.id.floatingActionButton2);
        FloatingActionButton btnElimTrat = (FloatingActionButton) view.findViewById(R.id.floatingActionButton3);
        FragmentTransaction fr = getFragmentManager().beginTransaction();
        fr.addToBackStack(null);
        fr.commit();


        btnAnadirTrat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.container, new FragmentAnadirTrat());
                fr.addToBackStack(null);
                fr.commit();
            }
        });
        btnModTrat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.container, new FragmentModTrat());
                fr.addToBackStack(null);
                fr.commit();
            }
        });
        btnElimTrat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.container, new FragmentElimTrat());
                fr.addToBackStack(null);
                fr.commit();
            }
        });

        return view;
    }
}
