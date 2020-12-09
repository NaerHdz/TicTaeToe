package mx.edu.itl.c17130038.tictaetoeapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

import mx.edu.itl.c17130038.tictaetoeapp.util.Juego;

public class MainActivity extends AppCompatActivity {
    private Chronometer cronometro;
    private MediaPlayer mp;

    private Button btnMusica, btnPausa, btnColocar;

    private Juego juego;

    private boolean esPausa, sonido;
    private int turno, fichaSeleccionada;

    private int[] botones = {
            R.id.btn00, R.id.btn01, R.id.btn02,
            R.id.btn10, R.id.btn11, R.id.btn12,
            R.id.btn20, R.id.btn21, R.id.btn22
    };

    //----------------------------------------------------------------------------------------------
    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );

        //Botones  y cronometro
        btnPausa = findViewById ( R.id.btnPausa );
        btnMusica = findViewById ( R.id.btnMusica );
        cronometro = findViewById ( R.id.cronometro );

        //Creamos jugador
        juego = new Juego ();

        //Bandera necesarias para la logica de juego
        esPausa = sonido = true;
        turno = 0;
        empezarTimer ( SystemClock.elapsedRealtime () );

        //Elegir ficha de los jugadores
        escojerFigura ();
        //Reproductor de musica e inicializacion de atributos
        mp = MediaPlayer.create ( this, R.raw.audio );
        mp.seekTo ( getIntent ().getIntExtra ( "musica", 0 ) );
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
    @Override
    protected void onDestroy () {
        regresarAlStartActivity ();
        super.onDestroy ();
    }
    //----------------------------------------------------------------------------------------------
    //Inicio de los metodos que son utilizados para los onClick de los botones
    //Boton que detiene o corre el timer
    //----------------------------------------------------------------------------------------------
    public void btnTimerClick ( View v ) {
        if ( esPausa ) {
            empezarTimer ( cronometro.getBase () );
        } else {
            pausarTimer ();
        }
    }

    //----------------------------------------------------------------------------------------------
    //boton que devuelve todo a su estado inicial
    public void btnResetClick ( View v ) {
        reset ();
    }

    //----------------------------------------------------------------------------------------------
    //boton que reproduce musica de fondo
    public void btnMusicaClick ( View v ) {
        if ( mp.isPlaying () ) {
            mp.pause ();
            btnMusica.setBackgroundResource ( android.R.drawable.ic_lock_silent_mode );
        } else {
            mp.start ();
            btnMusica.setBackgroundResource ( android.R.drawable.ic_lock_silent_mode_off );
        }
    }

    //----------------------------------------------------------------------------------------------
    //Colocar las x y o segun el turno del jugador
    public void btnColocarClick ( View v ) {
        btnColocar = findViewById ( v.getId () );
        juego.guardarSeleccion ( v.getId (), turno );
        int ficha;
        if ( turno == 0 ) {
            ficha = R.drawable.cancel;
            turno++;
        } else {
            ficha = R.drawable.circle_ring;
            turno--;
        }
        btnColocar.setBackground ( this.getResources ().getDrawable ( ficha ) );
        btnColocar.setEnabled ( false );

        if ( juego.comprobarMatriz () != 0 ) {
            mensajeDialog ( "Gano el jugador " + juego.comprobarMatriz () );
        } else if ( juego.esEmpate () ) {
            mensajeDialog ( "Empate!" );
        }
    }

    //----------------------------------------------------------------------------------------------
    //Inicio de los metodos utilizados para resolver
    //Metodo para resetear todo
    //----------------------------------------------------------------------------------------------
    private void reset () {
        cronometro.setBase ( SystemClock.elapsedRealtime () );
        empezarTimer ( cronometro.getBase () );
        turno = fichaSeleccionada;
        juego.resetMatriz ();
        for ( int i = 0; i < botones.length; i++ ) {
            btnColocar = findViewById ( botones[ i ] );
            btnColocar.setBackground ( this.getResources ().getDrawable ( android.R.drawable.btn_default ) );
            btnColocar.setEnabled ( true );
        }
    }

    //----------------------------------------------------------------------------------------------
    //metodo que detiene el timer
    private void pausarTimer () {
        btnPausa.setBackground ( this.getResources ().getDrawable ( android.R.drawable.ic_media_play ) );
        cronometro.stop ();
        esPausa = true;
    }

    //----------------------------------------------------------------------------------------------
    //metodo que inicia o restaura el timer segun el parametro
    private void empezarTimer ( long tiempo ) {
        btnPausa.setBackground ( this.getResources ().getDrawable ( android.R.drawable.ic_media_pause ) );
        cronometro.setBase ( tiempo );
        cronometro.start ();
        esPausa = false;
    }

    //----------------------------------------------------------------------------------------------
    public void mensajeDialog ( String mensaje ) {
        AlertDialog.Builder builder = new AlertDialog.Builder ( this );
        builder.setTitle ( "Fin de la partida: " )
                .setMessage ( mensaje )
                .setPositiveButton ( "Jugar Otra vez", new DialogInterface.OnClickListener () {
                    @Override
                    public void onClick ( DialogInterface dialog, int which ) {
                        reset ();
                        dialog.dismiss ();
                    }
                } )
                .setNegativeButton ( "Aceptar", new DialogInterface.OnClickListener () {
                    @Override
                    public void onClick ( DialogInterface dialog, int which ) {
                        regresarAlStartActivity ();
                        dialog.dismiss ();
                        MainActivity.this.finish ();
                    }
                } )
                .create ()
                .show ();
    }

    //----------------------------------------------------------------------------------------------
    public void escojerFigura () {
        String opciones[] = { "X", "O" };

        AlertDialog.Builder builder = new AlertDialog.Builder ( this );
        builder.setTitle ( "Escoja su figura para jugador 1: " )
                .setSingleChoiceItems ( opciones, turno, new DialogInterface.OnClickListener () {

                    @Override
                    public void onClick ( DialogInterface dialog, int which ) {
                        turno = which;
                        fichaSeleccionada = which;
                    }
                } )
                .setPositiveButton ( "Aceptar", new DialogInterface.OnClickListener () {
                    @Override
                    public void onClick ( DialogInterface dialog, int which ) {
                        dialog.dismiss ();
                    }
                } )
                .create ()
                .show ();
    }

    //----------------------------------------------------------------------------------------------
    public void regresarAlStartActivity () {
        mp.pause ();
        Intent intent = getIntent ();
        intent.putExtra ( "valor_retorno", mp.getCurrentPosition () );
        setResult ( RESULT_OK, intent );
    }
}