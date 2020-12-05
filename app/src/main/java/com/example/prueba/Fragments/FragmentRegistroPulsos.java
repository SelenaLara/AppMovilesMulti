package com.example.prueba.Fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.jjoe64.graphview.GraphView;

import com.example.prueba.R;
import com.jjoe64.graphview.ValueDependentColor;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;

public class FragmentRegistroPulsos extends Fragment {

    GraphView grafica;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_registro_pul, container, false);

        grafica = view.findViewById(R.id.grafica);

        BarGraphSeries<DataPoint> series = new BarGraphSeries<>(new DataPoint[]{
           new DataPoint(0,1),
                new DataPoint(1,5),
                new DataPoint(2,3),
                new DataPoint(3,2),
                new DataPoint(4,6)
        });

        grafica.addSeries(series);

        series.setValueDependentColor(new ValueDependentColor<DataPoint>() {
            @Override
            public int get(DataPoint data) {
                return Color.rgb((int) data.getX()*255/4,(int) data.getY()*255/6,100);
            }
        });

        series.setSpacing(20);

        series.setDrawValuesOnTop(true);

        FragmentTransaction fr = getFragmentManager().beginTransaction();
        fr.addToBackStack(null);
        fr.commit();
        return view;
    }
}
