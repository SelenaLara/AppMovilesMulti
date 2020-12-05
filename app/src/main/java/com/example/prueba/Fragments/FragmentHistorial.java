package com.example.prueba.Fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.prueba.Mostrar_tratamiento;
import com.example.prueba.R;

import java.nio.BufferOverflowException;

public class FragmentHistorial extends Fragment implements CalendarView.OnDateChangeListener{

    private CalendarView calendarView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_historial, container, false);

        calendarView=(CalendarView)view.findViewById(R.id.calenarioView);
        calendarView.setOnDateChangeListener(this);//Escuchador de eventos

        FragmentTransaction fr = getFragmentManager().beginTransaction();
        fr.addToBackStack(null);
        fr.commit();

        return view;
    }

    @Override
    public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {//cuando se seleccione la fecha
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());//se despliega un dialogo
        CharSequence [] items = new CharSequence[2];
        items[0] = "Mostrar tratamiento(s)";
        items[1] = "Cancelar";

        final int dia, mes, anio;
        dia= dayOfMonth;
        mes = month+1;
        anio = year;

        builder.setTitle("Seleccione una opción").setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which==0){
                    //agregar mostrar evento
                    Intent intent = new Intent(getActivity(), Mostrar_tratamiento.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("dia",dia);
                    bundle.putInt("mes",mes);
                    bundle.putInt("año",anio);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }else {
                    return;
                }
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
