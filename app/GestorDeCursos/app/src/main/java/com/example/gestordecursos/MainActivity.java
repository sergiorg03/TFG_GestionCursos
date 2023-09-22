/**
 * @author sergio Rodríguez Geniz
 */

package com.example.gestordecursos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void pantallaInfo(View view) {
        Intent i = new Intent(this, PantallaInfo.class);
        startActivity(i);

        finish();
    }
}