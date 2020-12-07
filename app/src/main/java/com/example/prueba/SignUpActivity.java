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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {

    TextView usuarioNuevo, bienvenidoLabel, continuarLabel;
    ImageView signUpImageView;
    TextInputLayout usuarioSignUpTextField,passTextField;


    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    Button mButtonSubirDatosFirebase;
    TextInputLayout mTextInputLayotDatoNombre,mTextInputLayotDatoEmail,mTextInputLayotDatoContraseña,mTextInputLayotConfirmaContraseña,mTextInputLayotNumeroDeCelular,mTextInputLayotDatoEdad,mTextInputLayotDatoSexo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mButtonSubirDatosFirebase= findViewById(R.id.registro);
        mTextInputLayotDatoNombre= findViewById(R.id.nameTextField);
        mTextInputLayotDatoEmail= findViewById(R.id.usuarioSignUpTextField);
        mTextInputLayotDatoContraseña= findViewById(R.id.passTextField);
        mTextInputLayotConfirmaContraseña= findViewById(R.id.passConfTextField);
        mTextInputLayotNumeroDeCelular= findViewById(R.id.numeroTextField);
        mTextInputLayotDatoEdad= findViewById(R.id.edadPacienteTextField);
        mTextInputLayotDatoSexo= findViewById(R.id.sexTextField);

        databaseReference=FirebaseDatabase.getInstance().getReference();

        mButtonSubirDatosFirebase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                String nombre=mTextInputLayotDatoNombre.getEditText().getText().toString();
                String Email=mTextInputLayotDatoEmail.getEditText().getText().toString();
                String contraseña=mTextInputLayotDatoContraseña.getEditText().getText().toString();
                String confirma_contraseña=mTextInputLayotConfirmaContraseña.getEditText().getText().toString();
                String celular = mTextInputLayotNumeroDeCelular.getEditText().getText().toString();
                int edad = Integer.parseInt(mTextInputLayotDatoEdad.getEditText().getText().toString());
                String sexo=mTextInputLayotDatoSexo.getEditText().getText().toString();

                //       InicializarFirebase();
                CargarDatosFire(nombre, Email, contraseña, confirma_contraseña, celular, edad, sexo);
                Intent intent = new Intent(SignUpActivity.this, home_activity.class);
                startActivity(intent);
                finish();

            }
//            private void InicializarFirebase () {
//                FirebaseApp.initializeApp(this);FirebaseApp.initializeApp(this);
//                FirebaseApp.initializeApp(this);
//
//                firebaseDatabase = FirebaseDatabase.getInstance();
//                DatabasetReference=firebaseDatabase.getReference();
//
//            }

        });

        signUpImageView = findViewById(R.id.signUpImageView);
        bienvenidoLabel = findViewById(R.id.bienvenidoLabel);
        continuarLabel = findViewById(R.id.continuarLabel);
        usuarioSignUpTextField = findViewById(R.id.usuarioSignUpTextField);
        passTextField = findViewById(R.id.passTextField);
        usuarioNuevo = findViewById(R.id.usuarioNuevo);


        usuarioNuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transitionBack();
            }
        });
    }

    private void CargarDatosFire(String nombre, String Email, String contraseña, String confirma_contraseña, String celular, int edad, String sexo) {
        // InicializarFirebase();
        Map<String, Object> DatosUsuario = new HashMap<>();
        DatosUsuario.put("Nombre",nombre);
        DatosUsuario.put("Email", Email);
        DatosUsuario.put("Contraseña",contraseña);
        DatosUsuario.put("ConfirmarContraseña",confirma_contraseña);
        DatosUsuario.put("NumeroDeCelular",celular);
        DatosUsuario.put("Edad",edad);
        DatosUsuario.put("Sexo",sexo);


        databaseReference.child ("Usuario").push().setValue(DatosUsuario);
        //   DatabaseReference = firebaseDatabase.getReference (). child ("Usuario"); DatabaseReference.setValue ("ernesto");


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
        pairs[5] = new Pair<View, String>(mButtonSubirDatosFirebase, "buttonSignUpTrans" );
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