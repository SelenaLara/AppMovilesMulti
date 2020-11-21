package com.example.prueba.Fragments;

import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.prueba.R;
import com.google.android.material.button.MaterialButton;

import java.util.Calendar;

public class FragmentAnadirTrat2 extends Fragment implements View.OnClickListener {

    Spinner comboTipoMed;
    MaterialButton btonGuardar;
    Button btonHora;
    EditText editTextHora;
    private int hora, minutos;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_anadir_trat2, container, false);
        comboTipoMed=(Spinner)view.findViewById(R.id.spinnerTipoMed);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.combo_tiposMed, android.R.layout.simple_spinner_item);
        comboTipoMed.setAdapter(adapter);

        btonHora=(Button) view.findViewById(R.id.buttonHora);
        editTextHora=(EditText) view.findViewById(R.id.editTextHora);

        btonHora.setOnClickListener(this);

        FragmentTransaction fr = getFragmentManager().beginTransaction();
        fr.addToBackStack(null);
        fr.commit();
        return view;
    }

    @Override
    public void onClick(View v) {
        if (v==btonHora){
            final Calendar c = Calendar.getInstance();
            hora = c.get(Calendar.HOUR_OF_DAY);
            minutos = c.get(Calendar.MINUTE);
            final TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    editTextHora.setText(hourOfDay+":"+minute);
                }
            },hora,minutos,false);
            timePickerDialog.show();
        }
    }
}
