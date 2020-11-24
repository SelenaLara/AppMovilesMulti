package com.example.prueba.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.prueba.R;
import com.example.prueba.PopUpNotifElim;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class FragmentElimTrat2 extends Fragment {

    Spinner comboSeleccMed;
    Button btonSi;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_elim_trat2, container, false);
        FloatingActionButton btnRegresarElim;
        comboSeleccMed=(Spinner)view.findViewById(R.id.spinnerSeleccMed);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.combo_seleccMed, android.R.layout.simple_spinner_item);
        comboSeleccMed.setAdapter(adapter);
        btnRegresarElim = (FloatingActionButton) view.findViewById(R.id.floatingActionBRegresarElim);
        btonSi=(Button)view.findViewById(R.id.buttonSi);


        btnRegresarElim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.container, new FragmentTratamiento());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        btonSi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PopUpNotifElim.class);
                getActivity().startActivity(intent);
            }
        });


        FragmentTransaction fr = getFragmentManager().beginTransaction();
        fr.addToBackStack(null);
        fr.commit();
        return view;
    }
}
