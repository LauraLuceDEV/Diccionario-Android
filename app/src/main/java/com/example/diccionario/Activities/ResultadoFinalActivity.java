package com.example.diccionario.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.diccionario.R;

public class ResultadoFinalActivity extends AppCompatActivity {
    private ImageButton btnJugar;
    private ImageButton btnMenu;
    private TextView txtFinal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado_final);

        this.btnJugar = findViewById(R.id.btn_volverJugar);
        this.btnMenu = findViewById(R.id.btn_volverMenu);
        this.txtFinal = findViewById(R.id.tv_Result_Final);

        //Bundle
        int bundle_res = getIntent().getIntExtra("puntuacion", 0);
        this.txtFinal.setText("¡HA OBTENIDO " + bundle_res + " PUNTOS DE 5!");


        //Listeners
        this.btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultadoFinalActivity.this, MainActivity.class);
                startActivity(intent);
                finish();

            }
        });

        this.btnJugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultadoFinalActivity.this, EjercicioAct.class);
                startActivity(intent);
                finish();

            }
        });
    }
}
