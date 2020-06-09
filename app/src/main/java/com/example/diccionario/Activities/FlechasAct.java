package com.example.diccionario.Activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.diccionario.Controls.CtrlPPL;
import com.example.diccionario.Controls.Ctrl_Consultas;
import com.example.diccionario.Controls.Ctrl_EjerciciosFlechas;
import com.example.diccionario.Controls.Ctrl_Ejercicios_ConfigACT;
import com.example.diccionario.POJOS.Entrada_Diccionario;
import com.example.diccionario.R;

import java.util.ArrayList;
import java.util.List;

//hay que llevar dos enteros: numero total de intentos y puntos. Solo he puesto uno el otro ponlo tu para entender el codigo
//falta por enlanzar los textview de las celdas 4 y 5. Tendrás que hacer un get al controlador para que te dé un listado de palabras
// y asignarselas tú a los textviews.
//También tendrás que controlar que, cuando todos los texview estén con isEnabled a false es que ha terminado (lo que viene a ser el entero que he puesto restando)
public class FlechasAct extends AppCompatActivity {

    private TableLayout tableLayout;
    private TextView tv1_1;
    private TextView tv1_2;
    private TextView tv2_1;
    private TextView tv2_2;
    private TextView tv3_1;
    private TextView tv3_2;
    private TextView tv4_1;
    private TextView tv4_2;
    private TextView tv5_1;
    private TextView tv5_2;

    //SETTER Y GETTER
    public TableLayout getTableLayout(){ return this.tableLayout; }
    public TextView getTv1_1() { return tv1_1; }
    public TextView getTv1_2() { return tv1_2; }
    public TextView getTv2_1() { return tv2_1; }
    public TextView getTv2_2() { return tv2_2; }
    public TextView getTv3_1() { return tv3_1; }
    public TextView getTv3_2() { return tv3_2; }
    public TextView getTv4_1() { return tv4_1; }
    public TextView getTv4_2() { return tv4_2; }
    public TextView getTv5_1() { return tv5_1; }
    public TextView getTv5_2() { return tv5_2; }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flechas);

        //Bindeo de los TextBox(rectangulos) y el tablelayout
        this.tableLayout = findViewById(R.id.tableLayout1);
        this.tv1_1 = findViewById(R.id.tvLeft1);
        this.tv1_2 = findViewById(R.id.tvRight1);
        this.tv2_1 = findViewById(R.id.tvLeft2);
        this.tv2_2 = findViewById(R.id.tvRight2);
        this.tv3_1 = findViewById(R.id.tvLeft3);
        this.tv3_2 = findViewById(R.id.tvRight3);
        this.tv4_1 = findViewById(R.id.tvLeft4);
        this.tv4_2 = findViewById(R.id.tvRight4);
        this.tv5_1 = findViewById(R.id.tvLeft5);
        this.tv5_2 = findViewById(R.id.tvRight5);

        //Rellenamos los TextView con palabras aleatorias
        refrescarElementos();

        //Relacionamos el Layout con la clase
        RelativeLayout rl = findViewById(R.id.RelativeLayout1);
        DrawPanel drawingPanel = new DrawPanel(getApplicationContext());
        rl.addView(drawingPanel);
    }

    /**
     * Obtenemos el listado completo y luego lo desordenamos
     * Utilizaremos varios controladores
     * */
    private void refrescarElementos(){
        Ctrl_Ejercicios_ConfigACT crtl = new Ctrl_Ejercicios_ConfigACT();
        List<Entrada_Diccionario> listaPalabrasCompleta = crtl.obtenerElemDesordenados(CtrlPPL.getEntradasDiccionario()); //Obtenemos toda la lista entera, pero con sus elementos desordenados
        // Obtener sólo 5 palabras del lista
        List<Entrada_Diccionario> listaPalabras = new ArrayList<>();
        for(int i=0; i < 5; i++){
            listaPalabras.add(listaPalabrasCompleta.get(i));
        }
        // Obtener 2 String de ingles y otro de español
        List<String> palabrasING = Ctrl_EjerciciosFlechas.getListadoPalabrasIngles(listaPalabras);
        List<String> palabrasESP = Ctrl_EjerciciosFlechas.getListadoPalabrasEspañol(listaPalabras);

        //Escribir en los textbox correspondientes
        //TODO: Se podría mejorar y hacer con un 'for'
        this.tv1_1.setText(palabrasING.get(0));
        this.tv2_1.setText(palabrasING.get(1));
        this.tv3_1.setText(palabrasING.get(2));
        this.tv4_1.setText(palabrasING.get(3));
        this.tv5_1.setText(palabrasING.get(4));

        this.tv1_2.setText(palabrasESP.get(0));
        this.tv2_2.setText(palabrasESP.get(1));
        this.tv3_2.setText(palabrasESP.get(2));
        this.tv4_2.setText(palabrasESP.get(3));
        this.tv5_2.setText(palabrasESP.get(4));
    }

    public class DrawPanel extends View {
        private int numAciertos;
        private int numFallos;
        private Paint paint;
        int color = 0;
        TextView textViewOrigen;
        private ArrayList points;
        private ArrayList strokes;
        //GETTER
        public int getColor() { return color; }

        //CONSTRUCTORES
        public DrawPanel(Context context) {
            super(context);
            this.numAciertos = 5;
            this.numFallos = 0;
            points = new ArrayList();
            strokes = new ArrayList();
            paint = createPaint(Color.GREEN, 9);
        }

        //Métodos
        public void onDraw(Canvas c) {
            super.onDraw(c);
            for (Object obj : strokes) {
                drawStroke((ArrayList) obj, c, color);
            }
            drawStroke(points, c, color);
            color = 0;
        }


        //Dependiendo del evento hacemos una lógica u otra
        public boolean onTouchEvent(MotionEvent event) {
            if (event.getActionMasked() == MotionEvent.ACTION_MOVE) {
                onActionMove(event);
            }

            if (event.getActionMasked() == MotionEvent.ACTION_DOWN) {
                onActionDown(event);
            }

            if (event.getActionMasked() == MotionEvent.ACTION_UP) {
                onActionUp(event);
            }
            return true;
        }

        //Eventos:
        //Evento para 'ACTION_MOVE'
        private void onActionMove(MotionEvent event) {
            if (textViewOrigen != null && textViewOrigen.isEnabled()) {
                points.add(new Point((int) event.getX(), (int) event.getY()));
                color = getColor();
            } else {
                strokes.clear();
                points.clear();
            }
            invalidate();
        }

        //Evento para 'ACTION_UP'
        private void onActionUp(MotionEvent event) {
            try {
                for (int r = 0; r < getTableLayout().getChildCount(); r++) {
                    int y = (int) getTableLayout().getChildAt(r).getY();
                    if ((int) event.getY() >= y && (int) event.getY() < (y + getTableLayout().getChildAt(r).getHeight())) {
                        TableRow tr1 = (TableRow) getTableLayout().getChildAt(r);
                        for (int i = 1; i < ((TableRow) getTableLayout().getChildAt(r)).getChildCount(); i += 2) {
                            int x1 = (int) tr1.getChildAt(i).getX();
                            if ((int) event.getX() >= x1 && (int) event.getX() < (x1 + tr1.getChildAt(i).getWidth())) {

                                TextView textViewDestino = ((TextView) ((TableRow) getTableLayout().getChildAt(r)).getChildAt(i));
                                //TODO: Lógica de coincidencia
                                if((this.textViewOrigen != null && textViewDestino != null)
                                        && Ctrl_EjerciciosFlechas.comprobarCoincidencia(this.textViewOrigen.getText().toString(), textViewDestino.getText().toString())){
                                    color = 1;
                                    //Inhabilitamos
                                    textViewOrigen.setEnabled(false);
                                    textViewDestino.setEnabled(false);
                                    //Cambiamos el color de fondo
                                    this.textViewOrigen.setBackground(ContextCompat.getDrawable(FlechasAct.this, R.color.colorPrimary));
                                    textViewDestino.setBackground(ContextCompat.getDrawable(FlechasAct.this, R.color.colorPrimary));
                                    this.numAciertos--;
                                    //Pop-up/Alert del número de aciertos
                                    if(this.numAciertos == 0){
                                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(FlechasAct.this);
                                        alertDialog.setTitle("¡ENHORABUENA!");
                                        alertDialog.setMessage("¡Has completado el reto con un porcentaje de " + Ctrl_EjerciciosFlechas.calcularPorcentaje(this.numFallos, 5) +
                                                " aciertos!");
                                        alertDialog.setPositiveButton("Volver", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                finish();
                                            }
                                        });
                                        alertDialog.create().show();
                                    }else{
                                        Toast.makeText(getApplicationContext(),"Te quedan " + numAciertos + "palabras por acertar", Toast.LENGTH_SHORT).show();
                                    }
                                }else{
                                    this.numFallos++;
                                }
                            }
                        }
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            strokes.clear();
            points.clear();
            invalidate();
        }

        //Evento para 'ACTION_DOWN'
        private void onActionDown(MotionEvent event) {
            boolean existsOverlapping = false;
            try {
                for (int r = 0; r < getTableLayout().getChildCount(); r++) {
                    int y = (int) getTableLayout().getChildAt(r).getY();
                    if ((int) event.getY() >= y && (int) event.getY() < (y + getTableLayout().getChildAt(r).getHeight())) {
                        TableRow tr1 = (TableRow) getTableLayout().getChildAt(r);
                        for (int i = 0; i < ((TableRow) getTableLayout().getChildAt(r)).getChildCount(); i += 2) {
                            int x1 = (int) tr1.getChildAt(i).getX();
                            if ((int) event.getX() >= x1 && (int) event.getX() < (x1 + tr1.getChildAt(i).getWidth())) {
                                existsOverlapping = true;
                                textViewOrigen = ((TextView) ((TableRow) getTableLayout().getChildAt(r)).getChildAt(i));
                            }
                        }
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            if (!existsOverlapping || !textViewOrigen.isEnabled()) {
                textViewOrigen = null;
                invalidate();
            }
        }

        private void drawStroke(@SuppressWarnings("rawtypes") ArrayList stroke,
                                Canvas c, int i1) {

            if (stroke.size() > 0 && i1 == 0) {
                Point p0 = (Point) stroke.get(0);
                for (int i = 1; i < stroke.size(); i++) {
                    Point p1 = (Point) stroke.get(i);
                    c.drawLine(p0.x, p0.y, p1.x, p1.y, paint);
                    p0 = p1;
                }
            }

        }


        private Paint createPaint(int color, float width) {
            Paint temp = new Paint();
            temp.setStyle(Paint.Style.STROKE);
            temp.setAntiAlias(true);
            temp.setColor(color);
            temp.setStrokeWidth(width);
            temp.setStrokeCap(Paint.Cap.ROUND);

            return temp;
        }
    }
}