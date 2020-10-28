package com.example.prueba.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.prueba.R;

public class FragmentElimTrat extends Fragment {

    Spinner comboSeleccMed;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_elim_trat, container, false);
        comboSeleccMed=(Spinner)view.findViewById(R.id.spinnerSeleccMed);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.combo_seleccMed, android.R.layout.simple_spinner_item);
        comboSeleccMed.setAdapter(adapter);

        return view;
    }
}
