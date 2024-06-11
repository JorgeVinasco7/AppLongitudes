package com.aristidev.googleloginapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Dashboard extends AppCompatActivity {

    CardView Temperatura, logout, Longitud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        //Temperatura
        Temperatura = findViewById(R.id.temperatura_1);

        //Longitud
        Longitud = findViewById(R.id.longitud);

        //logout
        logout = findViewById(R.id.Logout);

        //Actividad Temperatura
        Temperatura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard.this, temperatura.class);
                startActivity(intent);
                finish();
            }
        });

        //Actividad Longitud
        Longitud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard.this, Longitud.class);
                startActivity(intent);
                finish();
            }
        });


        // Actividad logout
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder Alerta = new AlertDialog.Builder(Dashboard.this);
                Alerta.setTitle("Pregunta:");
                Alerta.setMessage("¿Deseas salir de la App?");
                Alerta.setCancelable(false);

                //En caso que si
                Alerta.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Dashboard.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
                //En caso que No
                Alerta.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                }).show();
            }
        });
    }
}