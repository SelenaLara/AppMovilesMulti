package com.example.prueba;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.prueba.Fragments.FragmentConfiguracion2;
import com.example.prueba.Fragments.FragmentHistorial;
import com.example.prueba.Fragments.FragmentMiPerfil2;
import com.example.prueba.Fragments.FragmentRecargaMedic;
import com.example.prueba.Fragments.FragmentRegistroPulsos;
import com.example.prueba.Fragments.FragmentTratamiento;
import com.example.prueba.Fragments.MainFragment;
import com.google.android.material.navigation.NavigationView;

public class home_activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar, toolbar2;
    NavigationView navigationView;

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_activity);


        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.navigationView);


        navigationView.setNavigationItemSelectedListener(this);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.container, new MainFragment());
        fragmentTransaction.commit();

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        drawerLayout.closeDrawer(GravityCompat.START);
        if (menuItem.getItemId() == R.id.home){
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container, new MainFragment());
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
        if (menuItem.getItemId() == R.id.tratamiento){
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container, new FragmentTratamiento());
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
        if (menuItem.getItemId() == R.id.historial){
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container, new FragmentHistorial());
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
        if (menuItem.getItemId() == R.id.recargaMed){
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container, new FragmentRecargaMedic());
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
        if (menuItem.getItemId() == R.id.registroPulso){
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container, new FragmentRegistroPulsos());
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }

        if (menuItem.getItemId() == R.id.salir) {
            logout(this);
        }


        return false;
    }

    private static void logout(final Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("Salir");
        builder.setMessage("¿Estás seguro que quieres salir?");
        builder.setPositiveButton("SI", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                activity.finishAffinity();
                System.exit(0);
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    public boolean onCreateOptionsMenu(Menu menu ){
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem){
        if (menuItem.getItemId() == R.id.perfil2){
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container, new FragmentMiPerfil2());
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
        if (menuItem.getItemId() == R.id.configuracion2){
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container, new FragmentConfiguracion2());
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
        return true;
    }

}