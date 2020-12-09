package mx.edu.itl.c17130038.tictaetoeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_splash );

        ImageView imageView = findViewById(R.id.imageView);
        //Lanzar un splash despues de 2 segundos
        new Handler ().postDelayed ( new Runnable () {

            @Override
            public void run () {
                startActivity ( new Intent ( SplashActivity.this, StartActivity.class ) );
                finish ();
            }
        }, 4000);
    }
}