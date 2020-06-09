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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.diccionario.Adapter.Adaptador_Entradas;
import com.example.diccionario.Controls.CtrlPPL;
import com.example.diccionario.Controls.Ctrl_Consultas;
import com.example.diccionario.POJOS.Entrada_Diccionario;
import com.example.diccionario.R;
import com.google.android.material.navigation.NavigationView;

public class ConsultasActivity extends AppCompatActivity {
    private Spinner sp_alfaNum;
    private Spinner sp_Languajes;
    private Spinner sp_WordType;
    private ListView listado_Entradas;
    private Button btnSpinners;
    private Button btn_et;
    private Entrada_Diccionario[] entradas;
    private EditText et_entry;
    private NavigationView menuLateral;
    //Var estática para el permiso
    private final static int permisos = 4;
    private int opcionBackUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultas);

        this.opcionBackUp = 0;
        this.sp_alfaNum = findViewById(R.id.Spinner_AlfaNumeric);
        this.sp_Languajes = findViewById(R.id.Spinner_Languaje);
        this.sp_WordType = findViewById(R.id.Spinner_PalabraExpresion);
        this.listado_Entradas = findViewById(R.id.listadoEntradas);
        this.btnSpinners = findViewById(R.id.btnParam);
        this.btn_et = findViewById(R.id.btnCustom);
        this.et_entry = findViewById(R.id.et_entry);
        this.menuLateral = findViewById(R.id.navview);

        //Rellenamos lo Spinners
        //1-SPINER
        ArrayAdapter<CharSequence> adaptador1 = ArrayAdapter.createFromResource(this, R.array.valores_array1, android.R.layout.simple_spinner_item);
        this.sp_alfaNum.setAdapter(adaptador1);

        //2-SPINER
        ArrayAdapter<CharSequence> adaptador2 = ArrayAdapter.createFromResource(this, R.array.valores_array2, android.R.layout.simple_spinner_item);
        this.sp_Languajes.setAdapter(adaptador2);

        //3-SPINER
        ArrayAdapter<CharSequence> adaptador3 = ArrayAdapter.createFromResource(this, R.array.valores_array3, android.R.layout.simple_spinner_item);
        this.sp_WordType.setAdapter(adaptador3);

        //Rellenamos el Listado por defecto con todas las entradas
        this.entradas = Ctrl_Consultas.obtenerListadoEntradas();

        //Rellenamos con el Adapter
        Adaptador_Entradas adapt = new Adaptador_Entradas(this, entradas);
        this.listado_Entradas.setAdapter(adapt);

        //LISTENERS
        //Búsqueda por Spinners
        this.btnSpinners.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                entradas = Ctrl_Consultas.getArrayConsultasSpinners(sp_alfaNum.getSelectedItem().toString(),
                        sp_Languajes.getSelectedItem().toString(), sp_WordType.getSelectedItem().toString());

                Adaptador_Entradas adapt = new Adaptador_Entradas(ConsultasActivity.this, entradas);
                listado_Entradas.setAdapter(adapt);
            }
        });

        //Búsqueda por Edit Text
        btn_et.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                entradas = Ctrl_Consultas.getArrayConsultasEditText(et_entry.getText().toString());

                Adaptador_Entradas adapt = new Adaptador_Entradas(ConsultasActivity.this, entradas);
                listado_Entradas.setAdapter(adapt);
            }
        });


        //NAVIGATION VIEW - Menu Lateral
        this.menuLateral.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.menuIntroducirPalabras:
                        Intent int_Intro_palabra = new Intent(ConsultasActivity.this, IntroducirPalabraAct.class);
                        startActivity(int_Intro_palabra);
                        break;

                    case R.id.menuConsultPalabras:
                        Intent int_Consultar_palabra = new Intent(ConsultasActivity.this, ConsultasActivity.class);
                        startActivity(int_Consultar_palabra);
                        break;

                    case R.id.menuEjercicios:
                        Intent int_Ejercicio = new Intent(ConsultasActivity.this, EjercicioAct.class);
                        startActivity(int_Ejercicio);
                        break;

                    case R.id.menuConfigPreferencias:
                        Intent int_Preferencias = new Intent(ConsultasActivity.this, OpcionesPreferenciasAct.class);
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

        }else{
            if(this.opcionBackUp == 1){
                String res = CtrlPPL.realizarBackUpCCTRL();
                Toast.makeText(ConsultasActivity.this, res, Toast.LENGTH_SHORT).show();

            }else{
                String res2 = CtrlPPL.obtenerUltimoBackUp();
                Toast.makeText(ConsultasActivity.this, res2, Toast.LENGTH_SHORT).show();
            }

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case permisos: {
                // If request is cancelled, the result arrays are empty.
                boolean permisosAceptados = true;
                for(int i = 0; i < grantResults.length; i++){
                    if(grantResults[i] != PackageManager.PERMISSION_GRANTED){
                        permisosAceptados = false;
                    }
                }

                if (permisosAceptados) {
                    if(this.opcionBackUp == 1){
                        String res = CtrlPPL.realizarBackUpCCTRL();
                        Toast.makeText(ConsultasActivity.this, res, Toast.LENGTH_SHORT).show();

                    }else{
                        String res2 = CtrlPPL.obtenerUltimoBackUp();
                        Toast.makeText(ConsultasActivity.this, res2, Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(ConsultasActivity.this, "No tiene permiso para acceder al almacenamiento interno", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}
