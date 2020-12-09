package mx.edu.itl.c17130038.tictaetoeapp.util;

import mx.edu.itl.c17130038.tictaetoeapp.R;

public class Juego {
    private int[][] matrizJuego;

    public Juego () {
         matrizJuego= new int[ 3 ][ 3 ];
    }

    //----------------------------------------------------------------------------------------------
    //Comprobar si un jugador ya gano
    //----------------------------------------------------------------------------------------------
    public int comprobarMatriz () {
        int jugadorGanador = 0;
        //revisar si se gano de forma horizontal
        for ( int i = 0; i < 3; i++ ) {
            if ( matrizJuego[ i ][ 0 ] == matrizJuego[ i ][ 1 ] &&
                    matrizJuego[ i ][ 0 ] == matrizJuego[ i ][ 2 ] &&
                    matrizJuego[ i ][ 0 ] != 0 ) {
                return matrizJuego[ i ][ 0 ];
            } else {
                jugadorGanador = 0;
            }
        }

        //revisar si se gano de forma vertical
        for ( int i = 0; i < 3; i++ ) {
            if ( matrizJuego[ 0 ][ i ] == matrizJuego[ 1 ][ i ] &&
                    matrizJuego[ 0 ][ i ] == matrizJuego[ 2 ][ i ] &&
                    matrizJuego[ 0 ][ i ] != 0 ) {
                return matrizJuego[ 0 ][ i ];
            } else {
                jugadorGanador = 0;
            }
        }

        //Comprobar las diagonales
        if ( matrizJuego[ 0 ][ 0 ] == matrizJuego[ 1 ][ 1 ] &&
                matrizJuego[ 0 ][ 0 ] == matrizJuego[ 2 ][ 2 ] ) {
            jugadorGanador = matrizJuego[ 0 ][ 0 ];
        } else if ( matrizJuego[ 0 ][ 2 ] == matrizJuego[ 1 ][ 1 ] &&
                matrizJuego[ 0 ][ 2 ] == matrizJuego[ 2 ][ 0 ] ) {
            jugadorGanador = matrizJuego[ 1 ][ 1 ];
        }

        return jugadorGanador;
    }

    //----------------------------------------------------------------------------------------------
    public boolean esEmpate () {
        for ( int i = 0; i < 3; i++ ) {
            for ( int j = 0; j < 3; j++ ) {
                if ( matrizJuego[ i ][ j ] == 0 ) {
                    return false;
                }
            }
        }
        return true;
    }

    //----------------------------------------------------------------------------------------------
    //Guardar la posicion en la que cada jugador dio click identificando al mismo jugador
    //----------------------------------------------------------------------------------------------
    public void guardarSeleccion ( int buttonId, int turno ) {
        switch ( buttonId ) {
            case R.id.btn00:
                matrizJuego[ 0 ][ 0 ] = turno == 0 ? 1 : 2;
                break;
            case R.id.btn01:
                matrizJuego[ 0 ][ 1 ] = turno == 0 ? 1 : 2;
                break;
            case R.id.btn02:
                matrizJuego[ 0 ][ 2 ] = turno == 0 ? 1 : 2;
                break;
            case R.id.btn10:
                matrizJuego[ 1 ][ 0 ] = turno == 0 ? 1 : 2;
                break;
            case R.id.btn11:
                matrizJuego[ 1 ][ 1 ] = turno == 0 ? 1 : 2;
                break;
            case R.id.btn12:
                matrizJuego[ 1 ][ 2 ] = turno == 0 ? 1 : 2;
                break;
            case R.id.btn20:
                matrizJuego[ 2 ][ 0 ] = turno == 0 ? 1 : 2;
                break;
            case R.id.btn21:
                matrizJuego[ 2 ][ 1 ] = turno == 0 ? 1 : 2;
                break;
            case R.id.btn22:
                matrizJuego[ 2 ][ 2 ] = turno == 0 ? 1 : 2;
                break;
        }
    }

    public void resetMatriz () {
        for ( int i = 0; i < 3; i++ ) {
            for ( int j = 0; j < 3; j++ ) {
                matrizJuego[ i ][ j ] = 0;
            }
        }
    }
}
