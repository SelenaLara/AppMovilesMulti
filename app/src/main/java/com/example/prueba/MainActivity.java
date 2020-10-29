package com.example.prueba;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.prueba.Fragments.FragmentTratamiento;
import com.example.prueba.Fragments.MainFragment;

public class MainActivity extends Activity {

    //private final int DURACION_SPLASH = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        Animation animacion1 = AnimationUtils.loadAnimation(this, R.anim.desplazamiento_arriba);
        Animation animacion2 = AnimationUtils.loadAnimation(this, R.anim.desplazamiento_abajo);

        final TextView SmartPBTextView = findViewById(R.id.SmartPBTextView);
        final ImageView logoImageView = findViewById(R.id.logoImageView);

        SmartPBTextView.setAnimation(animacion1);
        logoImageView.setAnimation(animacion2);

        new Handler().postDelayed(new Runnable() {
            public void run(){
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);

                Pair[] pairs= new Pair[2];
                pairs[0] = new Pair<View, String>(logoImageView, "logoImageTrans" );
                pairs[1] = new Pair<View, String>(SmartPBTextView, "textTrans");

                if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, pairs);
                    startActivity(intent, options.toBundle());
                }else{
                    startActivity(intent);
                    finish();
                }
            };
        }, 2000);
    }
}