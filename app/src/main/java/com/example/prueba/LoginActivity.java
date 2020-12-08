package com.example.prueba;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.dynamic.IFragmentWrapper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firestore.v1.Target;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    TextView bienvenidoLabel, continuarLabel, nuevoUsuario;
    ImageView loginImageView;
    TextInputLayout usuarioTextField, passTextField;
    MaterialButton inicioSesion;
    private DatabaseReference mdataBase;
    private FirebaseAuth firebaseauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseauth = FirebaseAuth.getInstance();
        mdataBase = FirebaseDatabase.getInstance().getReference("Usuarios");

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
                String regex = "(?:[^<>()\\[\\].,;:\\s@\"]+(?:\\.[^<>()\\[\\].,;:\\s@\"]+)*|\"[^\\n\"]+\")@(?:[^<>()\\[\\].,;:\\s@\"]+\\.)+[^<>()\\[\\]\\.,;:\\s@\"]{2,63}";
                final String Email=usuarioTextField.getEditText().getText().toString().trim();
                final String contraseña=passTextField.getEditText().getText().toString().trim();

                if(TextUtils.isEmpty(Email)){
                    Toast.makeText(LoginActivity.this,"Debes escribir un correo electrónico", Toast.LENGTH_LONG).show();
                    usuarioTextField.requestFocus();
                    return;
                }
                if (!Email.matches(regex)){
                    Toast.makeText(LoginActivity.this,"Formato de correo electrónico incorrecto", Toast.LENGTH_LONG).show();
                    usuarioTextField.requestFocus();
                    return;
                }
                if (contraseña.length()<=7){
                    Toast.makeText(LoginActivity.this,"Debes usar un mínimo de 8 caracteres para la contraseña", Toast.LENGTH_LONG).show();
                    passTextField.requestFocus();
                    return;
                }

                firebaseauth.signInWithEmailAndPassword(Email,contraseña)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){
                                    Query query = mdataBase.orderByChild("email").equalTo(Email);
                                    query.addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            for (DataSnapshot ds:snapshot.getChildren()){
                                                PersonasDTO personasDTO = ds.getValue(PersonasDTO.class);
                                                Intent intent = new Intent(LoginActivity.this, home_activity.class);
                                                intent.putExtra("email", personasDTO.getEmail());
                                                usuarioTextField.getEditText().setText("");
                                                passTextField.getEditText().setText("");
                                                startActivity(intent);
                                            }
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {

                                        }
                                    });
                                }
                            }
                        });
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