package com.example.prueba.Fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.prueba.MedicamentosDTO;
import com.example.prueba.Mostrar_tratamiento;
import com.example.prueba.PersonasDTO;
import com.example.prueba.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.nio.BufferOverflowException;
import java.util.ArrayList;
import java.util.List;

public class FragmentHistorial extends Fragment implements CalendarView.OnDateChangeListener{

    private CalendarView calendarView;
    private DatabaseReference databaseReference;
    ListView listView;
    private List<MedicamentosDTO> listMed = new ArrayList<MedicamentosDTO>();
    ArrayAdapter arrayAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_historial, container, false);

        listView=view.findViewById(R.id.listaTratam);
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
                    //listarMedicamentos();
                    //Intent intent = new Intent(getActivity(), Mostrar_tratamiento.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("dia",dia);
                    bundle.putInt("mes",mes);
                    bundle.putInt("año",anio);
                    //intent.putExtras(bundle);
                    //startActivity(intent);
                }else {
                    return;
                }
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void listarMedicamentos() {
        databaseReference.child("Tratamientos").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listMed.clear();
                for (DataSnapshot obj : snapshot.getChildren()){
                    MedicamentosDTO m = obj.getValue(MedicamentosDTO.class);
                    listMed.add(m);

                    arrayAdapter = new ArrayAdapter<MedicamentosDTO>(getActivity(), android.R.layout.simple_list_item_1,listMed);
                    listView.setAdapter(arrayAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
