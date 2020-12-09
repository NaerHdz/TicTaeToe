package mx.edu.itl.c17130038.tictaetoeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

public class HowToPlayActivity extends AppCompatActivity {

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_how_to_play );

        getSupportActionBar ().setDisplayHomeAsUpEnabled ( true );
    }

    @Override
    public boolean onOptionsItemSelected ( @NonNull MenuItem item ) {
        if ( item.getItemId() == android.R.id.home ) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected ( item );
    }
}