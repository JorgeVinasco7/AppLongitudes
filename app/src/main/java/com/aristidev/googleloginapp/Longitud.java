package com.aristidev.googleloginapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Longitud extends AppCompatActivity {

    //VARIABLES------------------------------------------------------------------------------------------------
    public Button Atras, Calcular, Limpiar;
    public EditText longitud1;
    public TextView longitud2;
    public Spinner Menu1, Menu2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_longitu2);

        //ENLACES-----------------------------------------------------------------------------------------------
        Menu1 = findViewById(R.id.menu1);
        Menu2 = findViewById(R.id.menu2);
        Atras = findViewById(R.id.atras);
        Limpiar = findViewById(R.id.limpiar);
        Calcular = findViewById(R.id.calcular);
        longitud1 = findViewById(R.id.Longitud1);
        longitud2 = findViewById(R.id.Longitud2);

        //MENU-1------------------------------------------------------------------------------------------------
        Menu1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                String item = adapterView.getItemAtPosition(position).toString();
                //Toast.makeText(temperatura.this, "Escala: " + item , Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        ArrayList<String> List1 = new ArrayList<>();
        List1.add("Centimetros->Cm"); //centimetros
        List1.add("Metros->m"); //metros
        List1.add("Kilometros->Km");  //kilometros
        List1.add("Millas->mi"); //millas
        List1.add("Yardas->yd"); //yardad
        List1.add("Pies->ft"); //pies
        List1.add("Pulgadas->in"); //pulgadas

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, List1);
        adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        Menu1.setAdapter(adapter);

        //MENU-2--------------------------------------------------------------------------------------------------

        Menu2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                String item = adapterView.getItemAtPosition(position).toString();
                //Toast.makeText(temperatura.this, "Escala: " + item , Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        ArrayList<String> List2 = new ArrayList<>();
        List2.add("Centimetros->Cm"); //centimetros
        List2.add("Metros->m"); //metros
        List2.add("Kilometros->Km");  //kilometros
        List2.add("Millas->mi"); //millas
        List2.add("Yardas->yd"); //yardad
        List2.add("Pies->ft"); //pies
        List2.add("Pulgadas->in"); //pulgadas

        ArrayAdapter<String> Adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, List2);
        Adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        Menu2.setAdapter(Adapter);

        //BOTON DE LIMPIAR---------------------------------------------------------------------------------------
        Limpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                longitud1.setText("");
                longitud2.setText("");
            }
        });

        //BOTON DE ATRAS-----------------------------------------------------------------------------------------
        Atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(Longitud.this, Dashboard.class);
                startActivity(intent);
                finish();
            }
        });

        //BOTON DE CALCULAR----------------------------------------------------------------------------------------
        Calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String valor1 = longitud1.getText().toString();

                //SI ESTA VACIO-------------------------------------------------------------------------------------
                if (valor1.length() == 0) {
                    AlertDialog.Builder Alerta1 = new AlertDialog.Builder(Longitud.this);
                    Alerta1.setIcon(R.drawable.error);
                    Alerta1.setTitle("ERROR:");
                    Alerta1.setMessage("No has Digitado la Longitud a Convertir");
                    Alerta1.setNegativeButton("Aceptar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    AlertDialog alertDialog = Alerta1.create();
                    alertDialog.show();
                    longitud1.setFocusableInTouchMode(true);
                    longitud1.requestFocus();
                } else {
                    //EL NUMERO A CONVERTIR--------------------------------------------------------------------------
                    String valor2 = longitud1.getText().toString();
                    Double long1 = Double.parseDouble(valor2);

                    //DE LOS MENUS-----------------------------------------------------------------------------------
                    String Selec1 = Menu1.getSelectedItem().toString();
                    String Selec2 = Menu2.getSelectedItem().toString();

                    //PARA CENTRIMETPO-----------------------------------------------------------------------------------------------------------
                    //SI SON UNIDADES IGUALES DE CENTIMETROS A CENTIMETROS------------------------------------------------------------------------
                    if (Selec1.equals("Centimetros->Cm") && Selec2.equals("Centimetros->Cm")) {
                        AlertDialog.Builder Alerta = new AlertDialog.Builder(Longitud.this);
                        Alerta.setIcon(R.drawable.error);
                        Alerta.setTitle("ERROR:");
                        Alerta.setMessage("Son las mismas mismas unidades de Longitud.");
                        Alerta.setNegativeButton("Aceptar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        }).show();
                        //SI ES DE CENTIMETROS A METROS--------------------------------------------------------------------
                    } else if (Selec1.equals("Centimetros->Cm") && Selec2.equals("Metros->m")) {
                        Double met = long1 / 100;
                        String metros = String.valueOf(met);
                        longitud2.setText(metros);
                    }
                    //SI ES DE CENTIMETROS A KILOMETROS-----------------------------------------------------------------
                    else if (Selec1.equals("Centimetros->Cm") && Selec2.equals("Kilometros->Km")) {
                        Double Kilo = long1 / 100000;
                        String Kilometros = String.valueOf(Kilo);
                        longitud2.setText(Kilometros);
                    }
                    //SI ES DE CENTIMETROS A MILLAS-----------------------------------------------------------------------
                    else if (Selec1.equals("Centimetros->Cm") && Selec2.equals("Millas->mi")) {
                        Double Milla = long1 / 160900;
                        String Millas = String.valueOf(Milla);
                        longitud2.setText(Millas);
                    }
                    //SI ES DE CENTIMETROS A YARDAS-----------------------------------------------------------------------
                    else if (Selec1.equals("Centimetros->Cm") && Selec2.equals("Yardas->yd")) {
                        Double Yarda = long1 / 91.44;
                        String Yardas = String.valueOf(Yarda);
                        longitud2.setText(Yardas);
                    }
                    //SI ES DE CENTIMETROS A PIES
                    else if (Selec1.equals("Centimetros->Cm") && Selec2.equals("Pies->ft")) {
                        Double Pie = long1 / 30.48;
                        String Pies = String.valueOf(Pie);
                        longitud2.setText(Pies);
                    }
                    //SI ES DE CENTIMETROS A PULGADAS
                    else if (Selec1.equals("Centimetros->Cm") && Selec2.equals("Pulgadas->in")) {
                        Double Pulgada = long1 / 2.54;
                        String Pulgadas = String.valueOf(Pulgada);
                        longitud2.setText(Pulgadas);
                    }
                    //LONGITUD METROS--------------------------------------------------------------------------------------------------
                    //DE METROS A CENTIMETROS
                    else if (Selec1.equals("Metros->m") && Selec2.equals("Centimetros->Cm")) {
                        Double Centimetro = long1 * 100;
                        String Centimetros = String.valueOf(Centimetro);
                        longitud2.setText(Centimetros);
                    }
                    //METROS A METROS
                    //SI SON UNIDADES IGUALES DE METROS A METROS------------------------------------------------------------------------
                    else if (Selec1.equals("Metros->m") && Selec2.equals("Metros->m")) {
                        AlertDialog.Builder Alerta = new AlertDialog.Builder(Longitud.this);
                        Alerta.setIcon(R.drawable.error);
                        Alerta.setTitle("ERROR:");
                        Alerta.setMessage("Son las mismas mismas unidades de Longitud.");
                        Alerta.setNegativeButton("Aceptar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        }).show();
                    }
                    //METROS A KILOMETROS
                    else if (Selec1.equals("Metros->m") && Selec2.equals("Kilometros->Km")) {
                        Double Kilometro = long1 / 1000;
                        String Kilometros = String.valueOf(Kilometro);
                        longitud2.setText(Kilometros);
                    }
                    //METROS A MILLAS
                    else if (Selec1.equals("Metros->m") && Selec2.equals("Millas->mi")) {
                        Double Milla = long1 / 1609;
                        String Millas = String.valueOf(Milla);
                        longitud2.setText(Millas);
                    }
                    //METROS A YARDAS
                    else if (Selec1.equals("Metros->m") && Selec2.equals("Yardas->yd")) {
                        Double Yarda = long1 * 1.09361;
                        String Yardas = String.valueOf(Yarda);
                        longitud2.setText(Yardas);
                    }
                    //METROS A PIES
                    else if (Selec1.equals("Metros->m") && Selec2.equals("Pies->ft")) {
                        Double Pie = long1 * 3.28084;
                        String Pies = String.valueOf(Pie);
                        longitud2.setText(Pies);
                    }
                    //METROS A PULGADAS
                    else if (Selec1.equals("Metros->m") && Selec2.equals("Pulgadas->in")) {
                        Double Pulgada = long1 * 39.3701;
                        String Pulgadas = String.valueOf(Pulgada);
                        longitud2.setText(Pulgadas);
                    }

                }
            }
        });
    }
}
