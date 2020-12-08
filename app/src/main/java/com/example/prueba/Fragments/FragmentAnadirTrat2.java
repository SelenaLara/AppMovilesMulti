package com.example.prueba.Fragments;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.prueba.PopUpNotifTratamAnanido;
import com.example.prueba.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class FragmentAnadirTrat2 extends Fragment implements View.OnClickListener {

    Spinner comboTipoMed;
    MaterialButton btonGuardar;
    Button btonHora, btonInicio, btonFin;
    EditText editTextHora, editTextInicio, editTextFin;
    private int hora, minutos, dia, mes, anio;

    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    FloatingActionButton buttonGuardar;
    EditText editTextDatoNombreMed,editTextDatoCantidad;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_anadir_trat2, container, false);
        comboTipoMed=(Spinner)view.findViewById(R.id.spinnerTipoMed);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.combo_tiposMed, android.R.layout.simple_spinner_item);
        comboTipoMed.setAdapter(adapter);
        FloatingActionButton btnRegresar;

        editTextDatoNombreMed = view.findViewById(R.id.editTextTextPersonName6);
        editTextDatoCantidad = view.findViewById(R.id.editTextTextPersonName7);
        buttonGuardar= view.findViewById(R.id.floatingActionBGuardar);
        editTextHora= view.findViewById(R.id.editTextHora);
        editTextInicio=view.findViewById(R.id.editTextHora5);
        editTextFin=view.findViewById(R.id.editTextHora6);


        databaseReference=FirebaseDatabase.getInstance().getReference();

        buttonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                String nombreDelMedicam = editTextDatoNombreMed.getText().toString().trim();
                String cantidad = editTextDatoCantidad.getText().toString().trim();
                String tipoMed = comboTipoMed.getSelectedItem().toString().trim();
                String hora = editTextHora.getText().toString().trim();
                String fechaInicio = editTextInicio.getText().toString().trim();
                String fechaFin = editTextFin.getText().toString().trim();


                //       InicializarFirebase();
                CargarDatosFire(nombreDelMedicam, cantidad, tipoMed, hora, fechaInicio, fechaFin);

                Intent intent2 = new Intent(getActivity(), PopUpNotifTratamAnanido.class);
                getActivity().startActivity(intent2);

                editTextDatoNombreMed.setText("");
                editTextDatoCantidad.setText("");
                editTextHora.setText("");
                editTextInicio.setText("");
                editTextFin.setText("");

            }
        });

        btonHora=(Button) view.findViewById(R.id.buttonHora);
        btonInicio=(Button) view.findViewById(R.id.buttonFechaInicio);
        btonFin = (Button) view.findViewById(R.id.buttonFechaFin);
        btnRegresar = (FloatingActionButton) view.findViewById(R.id.floatingActionBRegresar);
        btonHora.setOnClickListener(this);

        btonInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                dia = c.get(Calendar.DAY_OF_MONTH);
                mes = c.get(Calendar.MONTH);
                anio = c.get(Calendar.YEAR);

                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        editTextInicio.setText(dayOfMonth+"/"+month+"/"+year);
                    }
                },dia,mes,anio);
                datePickerDialog.show();
            }
        });

        btonFin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                dia = c.get(Calendar.DAY_OF_MONTH);
                mes = c.get(Calendar.MONTH);
                anio = c.get(Calendar.YEAR);

                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        editTextFin.setText(dayOfMonth+"/"+month+"/"+year);
                    }
                },dia,mes,anio);
                datePickerDialog.show();
            }
        });

        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.container, new FragmentTratamiento());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        FragmentTransaction fr = getFragmentManager().beginTransaction();
        fr.addToBackStack(null);
        fr.commit();
        return view;



    }

    private void CargarDatosFire(String nombreDelMedicam, String cantidad, String tipoMed, String hora, String inicio, String fin) {
        // InicializarFirebase();
        Map<String, Object> DatosTrat = new HashMap<>();
        DatosTrat.put("Nombre del medicamento", nombreDelMedicam);
        DatosTrat.put("Tipo de medicamento", tipoMed);
        DatosTrat.put("Cantidad", cantidad);
        DatosTrat.put("Hora de medicamento", hora);
        DatosTrat.put("Fecha de inicio", inicio);
        DatosTrat.put("Fecha de fin", fin);


        databaseReference.child ("Tratamientos").push().setValue(DatosTrat);
        //   DatabaseReference = firebaseDatabase.getReference (). child ("Usuario"); DatabaseReference.setValue ("ernesto");


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
