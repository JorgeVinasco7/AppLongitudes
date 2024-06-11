package com.aristidev.googleloginapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Arrays;

public class temperatura extends AppCompatActivity {

    //VARIABLES
    public Button atras, calcular, limpiar;
    public EditText temperatura1;
    public TextView temperatura2;
    public Spinner menu1, menu2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperatura);

        //ENLACES
        menu1 = findViewById(R.id.Menu1);
        menu2 = findViewById(R.id.Menu2);
        atras = findViewById(R.id.Atras);
        calcular = findViewById(R.id.Calcular);
        limpiar = findViewById(R.id.Limpiar);
        temperatura1 = findViewById(R.id.Temperatura1);
        temperatura2 = findViewById(R.id.Temperatura2);


        //MENU-1
        menu1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                String item = adapterView.getItemAtPosition(position).toString();
                //Toast.makeText(temperatura.this, "Escala: " + item , Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ArrayList<String> list1 = new ArrayList<>();
        list1.add("Celsius (°C)");
        list1.add("Fahrenheit (°F)");
        list1.add("Kelvin (°K)");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list1);
        adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        menu1.setAdapter(adapter);


        /*
        String [] Tem1 = {"Celsius (°C)", "Fahrenheit (°F)", "Kelvin (°K)"};
        ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(Tem1));
        ArrayAdapter<String> arrayAdapter =new ArrayAdapter<>(this,R.layout.style_sipnner, arrayList);
        menu1.setAdapter(arrayAdapter);
        */

        //MENU-2
        menu2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                String item = adapterView.getItemAtPosition(position).toString();
                //Toast.makeText(temperatura.this, "Escala: " + item , Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ArrayList<String> list2 = new ArrayList<>();
        list2.add("Fahrenheit (°F)");
        list2.add("Kelvin (°K)");
        list2.add("Celsius (°C)");
        ArrayAdapter<String> Adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list2);
        Adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        menu2.setAdapter(Adapter);

        /*
        String [] Tem2 = {"Fahrenheit (°F)", "Kelvin (°K)", "Celsius (°C)"};
        ArrayList<String> adapter = new ArrayList<>(Arrays.asList(Tem2));
        ArrayAdapter<String> array = new ArrayAdapter<>(this,R.layout.style_sipnner, adapter);
        menu2.setAdapter(array);
        */

        //BOTON DE ATRAS
        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(temperatura.this, Dashboard.class);
                startActivity(intent);
                finish();
            }
        });

        //BOTON DE LIMPIAR
        limpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                temperatura1.setText("");
                temperatura2.setText("");
            }
        });

        //BOTON DE CALCULAR
        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String valor2 = temperatura1.getText().toString();

                if (valor2.length() == 0) {
                    AlertDialog.Builder alerta1 = new AlertDialog.Builder(temperatura.this);
                    alerta1.setIcon(R.drawable.error);
                    alerta1.setTitle("ERROR:");
                    alerta1.setMessage("No has Digitado la Temperatura a Convertir");
                    alerta1.setNegativeButton("Aceptar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            dialog.cancel();
                        }
                    });
                    AlertDialog alertDialog = alerta1.create();
                    alertDialog.show();
                    temperatura1.setFocusableInTouchMode(true);
                    temperatura1.requestFocus();
                } else {
                    //El numero a convertir
                    String valor1 = temperatura1.getText().toString();
                    Double tem1 = Double.parseDouble(valor1);

                    //De los menus
                    String selec1 = menu1.getSelectedItem().toString();
                    String selec2 = menu2.getSelectedItem().toString();

                    //DE CELSIUS A FAHRENHEIT
                    if (selec1.equals("Celsius (°C)") && selec2.equals("Fahrenheit (°F)")) {
                        Double Fahr = ((tem1 * 1.8) + 32);
                        String fahrenheit = String.valueOf(Fahr);
                        temperatura2.setText(fahrenheit);
                    }
                    //De Grados Celius a Kelvin
                    else if (selec1.equals("Celsius (°C)") && selec2.equals("Kelvin (°K)")) {
                        Double kel = tem1 + 273.15;
                        String kelvin = String.valueOf(kel);
                        temperatura2.setText(kelvin);
                    }
                    //De Grados Celsius a Celsius
                    else if (selec1.equals("Celsius (°C)") && selec2.equals("Celsius (°C)")) {
                        AlertDialog.Builder alerta = new AlertDialog.Builder(temperatura.this);
                        alerta.setIcon(R.drawable.error);
                        alerta.setTitle("ERROR:");
                        alerta.setMessage("Son las mismas longitudes.");
                        alerta.setNegativeButton("Aceptar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        }).show();
                    }
                    //De Grados Faherenheit a Grados Celsius
                    else if (selec1.equals("Fahrenheit (°F)") && selec2.equals("Celsius (°C)")) {
                        Double Cel = (tem1 - 32) * 0.5;
                        String celsius = String.valueOf(Cel);
                        temperatura2.setText(celsius);
                    }
                    //De grados Fahrenheit a Grados Kelvin
                    else if (selec1.equals("Fahrenheit (°F)") && selec2.equals("Kelvin (°K)")) {
                        Double kel = 273.15 + 0.5 * (32 - tem1);
                        String kelvin = String.valueOf(kel);
                        temperatura2.setText(kelvin);

                        //De grados Fahrenheit a Grados Fahrenheit
                    } else if (selec1.equals("Fahrenheit (°F)") && selec2.equals("Fahrenheit (°F)")) {
                        AlertDialog.Builder alerta1 = new AlertDialog.Builder(temperatura.this);
                        alerta1.setIcon(R.drawable.error);
                        alerta1.setTitle("ERROR:");
                        alerta1.setMessage("Son las mismas longitudes.");
                        alerta1.setNegativeButton("Aceptar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        }).show();

                        // De grados Kelvin a Grados Celsius
                    } else if (selec1.equals("Kelvin (°K)") && selec2.equals("Celsius (°C)")) {
                        Double cel = tem1 - 273;
                        String celsius = String.valueOf(cel);
                        temperatura2.setText(celsius);

                        //hay un error
                        // De grados Kelvin a Grados Fahrenheit
                    } else if (selec1.equals("Kelvin (°K)") && selec2.equals("Fahrenheit (°F)")) {
                        Double Fahr = 32 + 1.8 * (tem1-273);
                        String fahrenheit = String.valueOf(Fahr);
                        temperatura2.setText(fahrenheit);
                    }
                    //De grados Kelvin a grados Kelvin
                    else if (selec1.equals("Kelvin (°K)") && selec2.equals("Kelvin (°K)")) {
                        AlertDialog.Builder alerta2 = new AlertDialog.Builder(temperatura.this);
                        alerta2.setIcon(R.drawable.error);
                        alerta2.setTitle("ERROR:");
                        alerta2.setMessage("Son las mismas longitudes.");
                        alerta2.setNegativeButton("Aceptar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        }).show();
                    }
                }
            }
        });

    }
}

