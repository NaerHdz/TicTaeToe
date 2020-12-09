package mx.edu.itl.c17130038.tictaetoeapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class StartActivity extends AppCompatActivity {
    private MediaPlayer mp;

    //----------------------------------------------------------------------------------------------
    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_start );

        mp = MediaPlayer.create ( this, R.raw.audio );
        mp.start ();
    }

    //----------------------------------------------------------------------------------------------
    @Override
    protected void onStop () {
        mp.pause();
        super.onStop ();
    }

    //----------------------------------------------------------------------------------------------
    @Override
    protected void onResume () {
        mp.start ();
        super.onResume ();
    }

    //----------------------------------------------------------------------------------------------
    public void btnAcercaDeClick ( View v ) {
        startActivity ( new Intent ( this, AboutActivity.class ) );
    }

    //----------------------------------------------------------------------------------------------
    public void btnJuegoClick ( View v ) {
        mp.pause ();
        Intent intent = new Intent ( this, MainActivity.class );
        intent.putExtra ( "musica", mp.getCurrentPosition () );
        startActivityForResult ( intent, 1 );
    }

    //----------------------------------------------------------------------------------------------
    public void btnComoJugarClick ( View v ) {
        startActivity ( new Intent ( this, HowToPlayActivity.class ) );
    }

    //----------------------------------------------------------------------------------------------
    @Override
    protected void onActivityResult ( int requestCode, int resultCode, @Nullable Intent data ) {
        super.onActivityResult ( requestCode, resultCode, data );

        if ( resultCode == Activity.RESULT_OK ) {
            mp.seekTo ( getIntent ().getIntExtra ( "valor_retorno", 0 ) );
            mp.start ();
        }
    }
}