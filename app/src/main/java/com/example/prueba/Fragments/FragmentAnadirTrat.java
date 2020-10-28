package com.example.prueba.Fragments;

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

import com.example.prueba.LoginActivity;
import com.example.prueba.R;
import com.google.android.material.button.MaterialButton;

public class FragmentAnadirTrat extends Fragment {

    Spinner comboTipoMed;
    MaterialButton btonGuardar;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_anadir_trat, container, false);
        comboTipoMed=(Spinner)view.findViewById(R.id.spinnerTipoMed);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.combo_tiposMed, android.R.layout.simple_spinner_item);
        comboTipoMed.setAdapter(adapter);
        return view;
    }
}
