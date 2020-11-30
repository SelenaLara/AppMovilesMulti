package com.example.prueba;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.Button;

public class PopUpHorariosNotif extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up_horarios_notif);

        DisplayMetrics medidaVentana = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(medidaVentana);

        int ancho = medidaVentana.widthPixels;
        int alto = medidaVentana.heightPixels;

        getWindow().setLayout((int)(ancho*0.85),(int)(alto*0.3));
    }
}