package com.example.diccionario.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.diccionario.Controls.CtrlPPL;
import com.example.diccionario.Controls.Ctrl_Introducir_Palabra_ACT;
import com.example.diccionario.R;
import com.google.android.material.navigation.NavigationView;

public class IntroducirPalabraAct extends AppCompatActivity {
    private EditText et_palabraING;
    private EditText et_palabraESP;
    private EditText et_palabraTIPO;
    private Button btn_InsertarEntrada;
    private NavigationView menuLateral;
    //Var estática para el permiso
    private final static int permisos = 4;
    private int opcionBackUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introducir_palabra);

        this.opcionBackUp = 0;
        this.et_palabraESP = findViewById(R.id.intro_palabraESP);
        this.et_palabraING = findViewById(R.id.intro_palabraENG);
        this.et_palabraTIPO = findViewById(R.id.intro_palabraTIPO);
        this.btn_InsertarEntrada = findViewById(R.id.insert_Entrada);
        this.menuLateral = findViewById(R.id.navview);

        //Evento insertar palabra
        this.btn_InsertarEntrada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int camposEditTex = Ctrl_Introducir_Palabra_ACT.checking_CamposCompletados(et_palabraING.getText().toString(),
                        et_palabraESP.getText().toString(), et_palabraTIPO.getText().toString());

                if (camposEditTex == 0) {
                    Toast toast_Campos = Toast.makeText(IntroducirPalabraAct.this, "CAMPOS INCOMPLETOS", Toast.LENGTH_SHORT);
                    toast_Campos.show();

                } else {
                    int res_Entrada = Ctrl_Introducir_Palabra_ACT.insertarPalabra(et_palabraING.getText().toString(),
                            et_palabraESP.getText().toString(), et_palabraTIPO.getText().toString());

                    if (res_Entrada == 1) {
                        Toast toast_Campos = Toast.makeText(IntroducirPalabraAct.this, "Insercción realizada.", Toast.LENGTH_SHORT);
                        toast_Campos.show();

                    } else if (res_Entrada == 0) {
                        Toast toast_Campos = Toast.makeText(IntroducirPalabraAct.this, "ERROR: No se ha podido insertar correctamente su palabra.", Toast.LENGTH_LONG);
                        toast_Campos.show();

                    } else if (res_Entrada == -1) {
                        Toast toast_Campos = Toast.makeText(IntroducirPalabraAct.this, "Esta palabra ya se encuentra registrada.", Toast.LENGTH_LONG);
                        toast_Campos.show();
                    }
                }

            }
        });


        //NAVIGATION VIEW - Menu Lateral
        this.menuLateral.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.menuIntroducirPalabras:
                        Intent int_Intro_palabra = new Intent(IntroducirPalabraAct.this, IntroducirPalabraAct.class);
                        startActivity(int_Intro_palabra);

                        break;
                    case R.id.menuConsultPalabras:
                        Intent int_Consultar_palabra = new Intent(IntroducirPalabraAct.this, ConsultasActivity.class);
                        startActivity(int_Consultar_palabra);
                        break;

                    case R.id.menuEjercicios:
                        Intent int_Ejercicio = new Intent(IntroducirPalabraAct.this, EjercicioAct.class);
                        startActivity(int_Ejercicio);
                        break;

                    case R.id.menuConfigPreferencias:
                        Intent int_Preferencias = new Intent(IntroducirPalabraAct.this, OpcionesPreferenciasAct.class);
                        startActivity(int_Preferencias);
                        break;

                    case R.id.realizarBackUp:
                        opcionBackUp = 1;
                        //Permisos
                        chequearPermisos();
                        break;

                    case R.id.resetBackUp:
                        opcionBackUp = 2;
                        //Permisos
                        chequearPermisos();
                        break;
                }
                return true;
            }
        });


    }

    public void chequearPermisos() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, permisos);

        } else {
            if (this.opcionBackUp == 1) {
                String res = CtrlPPL.realizarBackUpCCTRL();
                Toast.makeText(IntroducirPalabraAct.this, res, Toast.LENGTH_SHORT).show();

            } else {
                String res2 = CtrlPPL.obtenerUltimoBackUp();
                Toast.makeText(IntroducirPalabraAct.this, res2, Toast.LENGTH_SHORT).show();
            }

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case permisos: {
                // If request is cancelled, the result arrays are empty.
                boolean permisosAceptados = true;
                for (int i = 0; i < grantResults.length; i++) {
                    if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                        permisosAceptados = false;
                    }
                }

                if (permisosAceptados) {
                    if (this.opcionBackUp == 1) {
                        String res = CtrlPPL.realizarBackUpCCTRL();
                        Toast.makeText(IntroducirPalabraAct.this, res, Toast.LENGTH_SHORT).show();

                    } else {
                        String res2 = CtrlPPL.obtenerUltimoBackUp();
                        Toast.makeText(IntroducirPalabraAct.this, res2, Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(IntroducirPalabraAct.this, "No tiene permiso para acceder al almacenamiento interno", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}
