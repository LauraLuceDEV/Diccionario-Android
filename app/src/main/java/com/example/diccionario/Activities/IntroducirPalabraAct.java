package com.example.diccionario.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.diccionario.Controls.Ctrl_Introducir_Palabra_ACT;
import com.example.diccionario.R;

public class IntroducirPalabraAct extends AppCompatActivity {
    private EditText et_palabraING;
    private EditText et_palabraESP;
    private EditText et_palabraTIPO;
    private Button btn_InsertarEntrada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introducir_palabra);

        this.et_palabraESP = findViewById(R.id.intro_palabraESP);
        this.et_palabraING = findViewById(R.id.intro_palabraENG);
        this.et_palabraTIPO = findViewById(R.id.intro_palabraTIPO);
        this.btn_InsertarEntrada = findViewById(R.id.insert_Entrada);

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


    }
}
