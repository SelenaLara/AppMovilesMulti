package com.example.prueba;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {

    TextView bienvenidoLabel, continuarLabel, nuevoUsuario;
    ImageView loginImageView;
    TextInputLayout usuarioTextField, passTextField;
    MaterialButton inicioSesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginImageView = findViewById(R.id.loginImageView);
        bienvenidoLabel = findViewById(R.id.bienvenidoLabel);
        continuarLabel = findViewById(R.id.continuarLabel);
        usuarioTextField = findViewById(R.id.usuarioTextField);
        passTextField = findViewById(R.id.passTextField);
        inicioSesion=findViewById(R.id.inicioSesion);
        nuevoUsuario = findViewById(R.id.usuarioNuevo);

        inicioSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, home_activity.class);
                startActivity(intent);
                finish();
            }
        });
        nuevoUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);

                Pair[] pairs= new Pair[7];
                pairs[0] = new Pair<View, String>(loginImageView, "logoImageTrans" );
                pairs[1] = new Pair<View, String>(bienvenidoLabel, "textTrans" );
                pairs[2] = new Pair<View, String>(continuarLabel, "iniciaSesionTextTrans" );
                pairs[3] = new Pair<View, String>(usuarioTextField, "emailInputTrans" );
                pairs[4] = new Pair<View, String>(passTextField, "passwTrans" );
                pairs[5] = new Pair<View, String>(inicioSesion, "buttonSignInTrans" );
                pairs[6] = new Pair<View, String>(nuevoUsuario, "newUserTrans" );

                if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(LoginActivity.this, pairs);
                    startActivity(intent, options.toBundle());
                }else{
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}