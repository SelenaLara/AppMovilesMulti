package com.example.prueba;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.TextUtils;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
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
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseauth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        firebaseauth = FirebaseAuth.getInstance();

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
                String regex = "(?:[^<>()\\[\\].,;:\\s@\"]+(?:\\.[^<>()\\[\\].,;:\\s@\"]+)*|\"[^\\n\"]+\")@(?:[^<>()\\[\\].,;:\\s@\"]+\\.)+[^<>()\\[\\]\\.,;:\\s@\"]{2,63}";

                final String nombre=mTextInputLayotDatoNombre.getEditText().getText().toString().trim();
                final String Email=mTextInputLayotDatoEmail.getEditText().getText().toString().trim();
                final String contraseña=mTextInputLayotDatoContraseña.getEditText().getText().toString().trim();
                String confirma_contraseña=mTextInputLayotConfirmaContraseña.getEditText().getText().toString().trim();
                final String celular = mTextInputLayotNumeroDeCelular.getEditText().getText().toString().trim();
                final int edad = Integer.parseInt(mTextInputLayotDatoEdad.getEditText().getText().toString());
                final String sexo=mTextInputLayotDatoSexo.getEditText().getText().toString().trim();

                if(TextUtils.isEmpty(nombre)){
                    Toast.makeText(SignUpActivity.this,"Debes escribir un nombre", Toast.LENGTH_LONG).show();
                    mTextInputLayotDatoNombre.requestFocus();
                    return;
                }
                if(TextUtils.isEmpty(Email)){
                    Toast.makeText(SignUpActivity.this,"Debes escribir un correo electrónico", Toast.LENGTH_LONG).show();
                    mTextInputLayotDatoEmail.requestFocus();
                    return;
                }
                if (!Email.matches(regex)){
                    Toast.makeText(SignUpActivity.this,"Formato de correo electrónico incorrecto", Toast.LENGTH_LONG).show();
                    mTextInputLayotDatoEmail.requestFocus();
                    return;
                }
                if(TextUtils.isEmpty(contraseña)){
                    Toast.makeText(SignUpActivity.this,"Debes escribir una contraseña", Toast.LENGTH_LONG).show();
                    mTextInputLayotDatoContraseña.requestFocus();
                    return;
                }
                if (contraseña.length()<=7){
                    Toast.makeText(SignUpActivity.this,"Debes usar un mínimo de 8 caracteres para la contraseña", Toast.LENGTH_LONG).show();
                    mTextInputLayotDatoEmail.requestFocus();
                    return;
                }
                if (!contraseña.equals(confirma_contraseña)){
                    Toast.makeText(SignUpActivity.this,"Las contraseñas no coinciden", Toast.LENGTH_LONG).show();
                    mTextInputLayotDatoEmail.requestFocus();
                    return;
                }
                if (celular.length()<=9){
                    Toast.makeText(SignUpActivity.this,"Número de celular incompleto", Toast.LENGTH_LONG).show();
                    mTextInputLayotNumeroDeCelular.requestFocus();
                    return;
                }

                /*progressDialog.setMessage("Guardando registro");
                progressDialog.show();*/


                firebaseauth.createUserWithEmailAndPassword(Email,contraseña).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            String idUnico=databaseReference.push().getKey();
                            PersonasDTO personaDTD = new PersonasDTO(idUnico, nombre, Email,  celular,  sexo,  contraseña, edad);

                            databaseReference.child("Usuarios").child(idUnico).setValue(personaDTD);
                            //databaseReference.child
                            Toast.makeText(SignUpActivity.this, "Registro exitoso",Toast.LENGTH_LONG).show();

                            mTextInputLayotDatoNombre.getEditText().setText("");
                            mTextInputLayotDatoEmail.getEditText().setText("");
                            mTextInputLayotDatoContraseña.getEditText().setText("");
                            mTextInputLayotConfirmaContraseña.getEditText().setText("");
                            mTextInputLayotDatoEdad.getEditText().setText("");
                            mTextInputLayotNumeroDeCelular.getEditText().setText("");
                            mTextInputLayotDatoSexo.getEditText().setText("");
                            finish();

                        }else{
                            Toast.makeText(SignUpActivity.this, "No se pudo registrar el usuario",Toast.LENGTH_LONG).show();
                        }
                        progressDialog.dismiss();
                    }
                });



                //       InicializarFirebase();
                //CargarDatosFire(nombre, Email, contraseña, confirma_contraseña, celular, edad, sexo);


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

    /*private void CargarDatosFire(String nombre, String Email, String contraseña, String confirma_contraseña, String celular, int edad, String sexo) {
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


    }*/

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