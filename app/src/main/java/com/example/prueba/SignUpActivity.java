package com.example.prueba;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

public class SignUpActivity extends AppCompatActivity {

    TextView usuarioNuevo, bienvenidoLabel, continuarLabel;
    ImageView signUpImageView;
    TextInputLayout usuarioSignUpTextField,passTextField;
    MaterialButton inicioSesion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        signUpImageView = findViewById(R.id.signUpImageView);
        bienvenidoLabel = findViewById(R.id.bienvenidoLabel);
        continuarLabel = findViewById(R.id.continuarLabel);
        usuarioSignUpTextField = findViewById(R.id.usuarioSignUpTextField);
        passTextField = findViewById(R.id.passTextField);
        inicioSesion=findViewById(R.id.inicioSesion);
        usuarioNuevo = findViewById(R.id.usuarioNuevo);


        usuarioNuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transitionBack();
            }
        });
    }

    @Override
    public void onBackPressed() {
        transitionBack();
    }

    public void transitionBack(){
        Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);

        Pair[] pairs= new Pair[7];
        pairs[0] = new Pair<View, String>(signUpImageView, "logoImageTrans" );
        pairs[1] = new Pair<View, String>(bienvenidoLabel, "textTrans" );
        pairs[2] = new Pair<View, String>(continuarLabel, "iniciaSesionTextTrans" );
        pairs[3] = new Pair<View, String>(usuarioSignUpTextField, "emailInputTrans" );
        pairs[4] = new Pair<View, String>(passTextField, "passwTrans" );
        pairs[5] = new Pair<View, String>(inicioSesion, "buttonSignUpTrans" );
        pairs[6] = new Pair<View, String>(usuarioNuevo, "newUserTrans" );

        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SignUpActivity.this, pairs);
            startActivity(intent, options.toBundle());
        }else{
            startActivity(intent);
            finish();
        }
    }
}