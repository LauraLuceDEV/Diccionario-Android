package com.example.diccionario.Models;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.diccionario.POJOS.Entrada_Diccionario;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Diccionario_DAO extends SQLiteOpenHelper {
    private static Diccionario_DAO Dicc_Singleton;
    private static final String DB = "Diccionario";
    private static final int DBVersion = 1;
    //Sentencia SQL para crear la tabla de Entradas
    private String sqlCreate = "CREATE TABLE IF NOT EXISTS  Entrada (palabra_eng TEXT, palabra_esp TEXT, pabla_tipo TEXT, " +
            "fecha_intro TEXT, fecha_latest_test TEXT, num_aciertos INTEGER)";

    //Constructor
    public Diccionario_DAO(Context contexto, SQLiteDatabase.CursorFactory factory) {
        super(contexto, DB, factory, DBVersion);
    }

    //SINGLETON DIVIDIDO EN 2 MÉTODOS
    public static void createInstance(Context context) {
        Dicc_Singleton = new Diccionario_DAO(context, null);
    }

    public static Diccionario_DAO getInstance() {
        return Dicc_Singleton;
    }


    //MÉTODOS DE LA CLASE SQLiteOpenHelper
    @Override
    public void onCreate(SQLiteDatabase db) {

        //Convertimos los datos
        Date currentTime = Calendar.getInstance().getTime();
        String currentDate = dateToString(currentTime);

        db.execSQL(sqlCreate);

        db.execSQL("INSERT INTO Entrada (palabra_eng, palabra_esp, pabla_tipo, fecha_intro, fecha_latest_test, num_aciertos) " +
                "VALUES ('Bibliography', 'Bibliografía', 'Sust', '" + currentDate + "', '" + currentDate + "', 0 )");

        db.execSQL("INSERT INTO Entrada (palabra_eng, palabra_esp, pabla_tipo, fecha_intro, fecha_latest_test, num_aciertos) " +
                "VALUES ('Navy', 'Armada', 'Sust', '" + currentDate + "', '" + currentDate + "', 0 )");

        db.execSQL("INSERT INTO Entrada (palabra_eng, palabra_esp, pabla_tipo, fecha_intro, fecha_latest_test, num_aciertos) " +
                "VALUES ('Army', 'Ejército', 'Sust', '" + currentDate + "', '" + currentDate + "', 0 )");

        db.execSQL("INSERT INTO Entrada (palabra_eng, palabra_esp, pabla_tipo, fecha_intro, fecha_latest_test, num_aciertos) " +
                "VALUES ('Component', 'Componente', 'Sust', '" + currentDate + "', '" + currentDate + "', 0 )");

        db.execSQL("INSERT INTO Entrada (palabra_eng, palabra_esp, pabla_tipo, fecha_intro, fecha_latest_test, num_aciertos) " +
                "VALUES ('Biology', 'Biología', 'Sust', '" + currentDate + "', '" + currentDate + "', 0 )");

        db.execSQL("INSERT INTO Entrada (palabra_eng, palabra_esp, pabla_tipo, fecha_intro, fecha_latest_test, num_aciertos) " +
                "VALUES ('Boilerplate', 'Repetitivo', 'Adj', '" + currentDate + "', '" + currentDate + "', 0 )");

        db.execSQL("INSERT INTO Entrada (palabra_eng, palabra_esp, pabla_tipo, fecha_intro, fecha_latest_test, num_aciertos) " +
                "VALUES ('Blueprint', 'Plano', 'Sust', '" + currentDate + "', '" + currentDate + "', 0 )");

        db.execSQL("INSERT INTO Entrada (palabra_eng, palabra_esp, pabla_tipo, fecha_intro, fecha_latest_test, num_aciertos) " +
                "VALUES ('Source code', 'Código-Fuente', 'Sust', '" + currentDate + "', '" + currentDate + "', 0 )");

        db.execSQL("INSERT INTO Entrada (palabra_eng, palabra_esp, pabla_tipo, fecha_intro, fecha_latest_test, num_aciertos) " +
                "VALUES ('Resources', 'Recursos', 'Sust', '" + currentDate + "', '" + currentDate + "', 0 )");

        db.execSQL("INSERT INTO Entrada (palabra_eng, palabra_esp, pabla_tipo, fecha_intro, fecha_latest_test, num_aciertos) " +
                "VALUES ('Above', 'Por encima de', 'Expres', '" + currentDate + "', '" + currentDate + "', 0 )");

        db.execSQL("INSERT INTO Entrada (palabra_eng, palabra_esp, pabla_tipo, fecha_intro, fecha_latest_test, num_aciertos) " +
                "VALUES ('Goat', 'Cabra', 'Sust', '" + currentDate + "', '" + currentDate + "', 0 )");

        db.execSQL("INSERT INTO Entrada (palabra_eng, palabra_esp, pabla_tipo, fecha_intro, fecha_latest_test, num_aciertos) " +
                "VALUES ('Goal', 'Meta', 'Sust', '" + currentDate + "', '" + currentDate + "', 0 )");

        db.execSQL("INSERT INTO Entrada (palabra_eng, palabra_esp, pabla_tipo, fecha_intro, fecha_latest_test, num_aciertos) " +
                "VALUES ('To Got', 'Tener', 'Verbo', '" + currentDate + "', '" + currentDate + "', 0 )");

        db.execSQL("INSERT INTO Entrada (palabra_eng, palabra_esp, pabla_tipo, fecha_intro, fecha_latest_test, num_aciertos) " +
                "VALUES ('Heirloom', 'Reliquia Familiar', 'Sust', '" + currentDate + "', '" + currentDate + "', 0 )");

        db.execSQL("INSERT INTO Entrada (palabra_eng, palabra_esp, pabla_tipo, fecha_intro, fecha_latest_test, num_aciertos) " +
                "VALUES ('Browser', 'Navedagor', 'Sust', '" + currentDate + "', '" + currentDate + "', 0 )");

        db.execSQL("INSERT INTO Entrada (palabra_eng, palabra_esp, pabla_tipo, fecha_intro, fecha_latest_test, num_aciertos) " +
                "VALUES ('Mixer', 'Batidora', 'Sust', '" + currentDate + "', '" + currentDate + "', 0 )");

        db.execSQL("INSERT INTO Entrada (palabra_eng, palabra_esp, pabla_tipo, fecha_intro, fecha_latest_test, num_aciertos) " +
                "VALUES ('To Mix', 'Mezclar', 'Verbo', '" + currentDate + "', '" + currentDate + "', 0 )");

        db.execSQL("INSERT INTO Entrada (palabra_eng, palabra_esp, pabla_tipo, fecha_intro, fecha_latest_test, num_aciertos) " +
                "VALUES ('Determination', 'Determinación', 'Sust', '" + currentDate + "', '" + currentDate + "', 0 )");

        db.execSQL("INSERT INTO Entrada (palabra_eng, palabra_esp, pabla_tipo, fecha_intro, fecha_latest_test, num_aciertos) " +
                "VALUES ('Madness', 'Locura', 'Sust', '" + currentDate + "', '" + currentDate + "', 0 )");

        db.execSQL("INSERT INTO Entrada (palabra_eng, palabra_esp, pabla_tipo, fecha_intro, fecha_latest_test, num_aciertos) " +
                "VALUES ('To Convince', 'Convencer', 'Verbo', '" + currentDate + "', '" + currentDate + "', 0 )");

        db.execSQL("INSERT INTO Entrada (palabra_eng, palabra_esp, pabla_tipo, fecha_intro, fecha_latest_test, num_aciertos) " +
                "VALUES ('To Resist', 'Resistir', 'Verbo', '" + currentDate + "', '" + currentDate + "', 0 )");

        db.execSQL("INSERT INTO Entrada (palabra_eng, palabra_esp, pabla_tipo, fecha_intro, fecha_latest_test, num_aciertos) " +
                "VALUES ('To Give In', 'Darse por vencido', 'Expres', '" + currentDate + "', '" + currentDate + "', 0 )");

        db.execSQL("INSERT INTO Entrada (palabra_eng, palabra_esp, pabla_tipo, fecha_intro, fecha_latest_test, num_aciertos) " +
                "VALUES ('Dreadful', 'Horrible', 'Adj', '" + currentDate + "', '" + currentDate + "', 0 )");

        db.execSQL("INSERT INTO Entrada (palabra_eng, palabra_esp, pabla_tipo, fecha_intro, fecha_latest_test, num_aciertos) " +
                "VALUES ('Have a good tan', 'Estar moreno', 'Expres', '" + currentDate + "', '" + currentDate + "', 0 )");

        db.execSQL("INSERT INTO Entrada (palabra_eng, palabra_esp, pabla_tipo, fecha_intro, fecha_latest_test, num_aciertos) " +
                "VALUES ('On a leash', 'Con correa', 'Expres', '" + currentDate + "', '" + currentDate + "', 0 )");

        db.execSQL("INSERT INTO Entrada (palabra_eng, palabra_esp, pabla_tipo, fecha_intro, fecha_latest_test, num_aciertos) " +
                "VALUES ('Company', 'Empresa', 'Sust', '" + currentDate + "', '" + currentDate + "', 0 )");

        db.execSQL("INSERT INTO Entrada (palabra_eng, palabra_esp, pabla_tipo, fecha_intro, fecha_latest_test, num_aciertos) " +
                "VALUES ('Bulletproof', 'A Prueba de Balas', 'Adj', '" + currentDate + "', '" + currentDate + "', 0 )");

        db.execSQL("INSERT INTO Entrada (palabra_eng, palabra_esp, pabla_tipo, fecha_intro, fecha_latest_test, num_aciertos) " +
                "VALUES ('Self-centered', 'Egocéntrico/-a', 'Adj', '" + currentDate + "', '" + currentDate + "', 0 )");

        db.execSQL("INSERT INTO Entrada (palabra_eng, palabra_esp, pabla_tipo, fecha_intro, fecha_latest_test, num_aciertos) " +
                "VALUES ('Broad-minded', 'Tolerante', 'Adj', '" + currentDate + "', '" + currentDate + "', 0 )");

        db.execSQL("INSERT INTO Entrada (palabra_eng, palabra_esp, pabla_tipo, fecha_intro, fecha_latest_test, num_aciertos) " +
                "VALUES ('Hollow', 'Hueco', 'Adj', '" + currentDate + "', '" + currentDate + "', 0 )");

        db.execSQL("INSERT INTO Entrada (palabra_eng, palabra_esp, pabla_tipo, fecha_intro, fecha_latest_test, num_aciertos) " +
                "VALUES ('Light', 'Ligero', 'Adj', '" + currentDate + "', '" + currentDate + "', 0 )");

        db.execSQL("INSERT INTO Entrada (palabra_eng, palabra_esp, pabla_tipo, fecha_intro, fecha_latest_test, num_aciertos) " +
                "VALUES ('Full', 'Lleno', 'Adj', '" + currentDate + "', '" + currentDate + "', 0 )");

        db.execSQL("INSERT INTO Entrada (palabra_eng, palabra_esp, pabla_tipo, fecha_intro, fecha_latest_test, num_aciertos) " +
                "VALUES ('Sticky', 'Pegajoso', 'Adj', '" + currentDate + "', '" + currentDate + "', 0 )");

        db.execSQL("INSERT INTO Entrada (palabra_eng, palabra_esp, pabla_tipo, fecha_intro, fecha_latest_test, num_aciertos) " +
                "VALUES ('Spicy', 'Picante', 'Adj', '" + currentDate + "', '" + currentDate + "', 0 )");

        db.execSQL("INSERT INTO Entrada (palabra_eng, palabra_esp, pabla_tipo, fecha_intro, fecha_latest_test, num_aciertos) " +
                "VALUES ('Ready', 'Preparado', 'Adj', '" + currentDate + "', '" + currentDate + "', 0 )");

        db.execSQL("INSERT INTO Entrada (palabra_eng, palabra_esp, pabla_tipo, fecha_intro, fecha_latest_test, num_aciertos) " +
                "VALUES ('Sudden', 'Repentino', 'Adj', '" + currentDate + "', '" + currentDate + "', 0 )");

        db.execSQL("INSERT INTO Entrada (palabra_eng, palabra_esp, pabla_tipo, fecha_intro, fecha_latest_test, num_aciertos) " +
                "VALUES ('Foolish', 'Tonto', 'Adj', '" + currentDate + "', '" + currentDate + "', 0 )");

        db.execSQL("INSERT INTO Entrada (palabra_eng, palabra_esp, pabla_tipo, fecha_intro, fecha_latest_test, num_aciertos) " +
                "VALUES ('Bill', 'Factura', 'Sust', '" + currentDate + "', '" + currentDate + "', 0 )");

        db.execSQL("INSERT INTO Entrada (palabra_eng, palabra_esp, pabla_tipo, fecha_intro, fecha_latest_test, num_aciertos) " +
                "VALUES ('Corn', 'Maíz', 'Adj', '" + currentDate + "', '" + currentDate + "', 0 )");

        db.execSQL("INSERT INTO Entrada (palabra_eng, palabra_esp, pabla_tipo, fecha_intro, fecha_latest_test, num_aciertos) " +
                "VALUES ('Furniture', 'Mueble', 'Adj', '" + currentDate + "', '" + currentDate + "', 0 )");

        db.execSQL("INSERT INTO Entrada (palabra_eng, palabra_esp, pabla_tipo, fecha_intro, fecha_latest_test, num_aciertos) " +
                "VALUES ('String', 'Cadena', 'Sust', '" + currentDate + "', '" + currentDate + "', 0 )");

        db.execSQL("INSERT INTO Entrada (palabra_eng, palabra_esp, pabla_tipo, fecha_intro, fecha_latest_test, num_aciertos) " +
                "VALUES ('Strong', 'Fuerte', 'Sust', '" + currentDate + "', '" + currentDate + "', 0 )");

        db.execSQL("INSERT INTO Entrada (palabra_eng, palabra_esp, pabla_tipo, fecha_intro, fecha_latest_test, num_aciertos) " +
                "VALUES ('Strength', 'Fuerza', 'Sust', '" + currentDate + "', '" + currentDate + "', 0 )");

        db.execSQL("INSERT INTO Entrada (palabra_eng, palabra_esp, pabla_tipo, fecha_intro, fecha_latest_test, num_aciertos) " +
                "VALUES ('Pole', 'Polaco (de Polonia)', 'Sust', '" + currentDate + "', '" + currentDate + "', 0 )");

        db.execSQL("INSERT INTO Entrada (palabra_eng, palabra_esp, pabla_tipo, fecha_intro, fecha_latest_test, num_aciertos) " +
                "VALUES ('Polish', 'Polaco', 'Adj', '" + currentDate + "', '" + currentDate + "', 0 )");

        db.execSQL("INSERT INTO Entrada (palabra_eng, palabra_esp, pabla_tipo, fecha_intro, fecha_latest_test, num_aciertos) " +
                "VALUES ('Application', 'Aplicación', 'Sust', '" + currentDate + "', '" + currentDate + "', 0 )");

        db.execSQL("INSERT INTO Entrada (palabra_eng, palabra_esp, pabla_tipo, fecha_intro, fecha_latest_test, num_aciertos) " +
                "VALUES ('Database', 'Base de Datos', 'Sust', '" + currentDate + "', '" + currentDate + "', 0 )");

        db.execSQL("INSERT INTO Entrada (palabra_eng, palabra_esp, pabla_tipo, fecha_intro, fecha_latest_test, num_aciertos) " +
                "VALUES ('Enviroment', 'Entorno', 'Sust', '" + currentDate + "', '" + currentDate + "', 0 )");

        db.execSQL("INSERT INTO Entrada (palabra_eng, palabra_esp, pabla_tipo, fecha_intro, fecha_latest_test, num_aciertos) " +
                "VALUES ('Gadget', 'Dispositivo', 'Sust', '" + currentDate + "', '" + currentDate + "', 0 )");

        db.execSQL("INSERT INTO Entrada (palabra_eng, palabra_esp, pabla_tipo, fecha_intro, fecha_latest_test, num_aciertos) " +
                "VALUES ('Browser tabs', 'Pestañas de Navegación', 'Sust', '" + currentDate + "', '" + currentDate + "', 0 )");

        db.execSQL("INSERT INTO Entrada (palabra_eng, palabra_esp, pabla_tipo, fecha_intro, fecha_latest_test, num_aciertos) " +
                "VALUES ('Crowdfunding', 'Micromecenazgo', 'Sust', '" + currentDate + "', '" + currentDate + "', 0 )");

        db.execSQL("INSERT INTO Entrada (palabra_eng, palabra_esp, pabla_tipo, fecha_intro, fecha_latest_test, num_aciertos) " +
                "VALUES ('E-commerce', 'Comercio-Electrónico', 'Sust', '" + currentDate + "', '" + currentDate + "', 0 )");

        db.execSQL("INSERT INTO Entrada (palabra_eng, palabra_esp, pabla_tipo, fecha_intro, fecha_latest_test, num_aciertos) " +
                "VALUES ('To Forward', 'Reenviar', 'Verbo', '" + currentDate + "', '" + currentDate + "', 0 )");

        db.execSQL("INSERT INTO Entrada (palabra_eng, palabra_esp, pabla_tipo, fecha_intro, fecha_latest_test, num_aciertos) " +
                "VALUES ('Front-facing camera', 'Cámara frontal', 'Sust', '" + currentDate + "', '" + currentDate + "', 0 )");

        db.execSQL("INSERT INTO Entrada (palabra_eng, palabra_esp, pabla_tipo, fecha_intro, fecha_latest_test, num_aciertos) " +
                "VALUES ('Hashtag', 'Etiqueta', 'Sust', '" + currentDate + "', '" + currentDate + "', 0 )");

        db.execSQL("INSERT INTO Entrada (palabra_eng, palabra_esp, pabla_tipo, fecha_intro, fecha_latest_test, num_aciertos) " +
                "VALUES ('Hoax', 'Bulo', 'Sust', '" + currentDate + "', '" + currentDate + "', 0 )");

        db.execSQL("INSERT INTO Entrada (palabra_eng, palabra_esp, pabla_tipo, fecha_intro, fecha_latest_test, num_aciertos) " +
                "VALUES ('Fake-News', 'Noticia-Falsa', 'Sust', '" + currentDate + "', '" + currentDate + "', 0 )");

        db.execSQL("INSERT INTO Entrada (palabra_eng, palabra_esp, pabla_tipo, fecha_intro, fecha_latest_test, num_aciertos) " +
                "VALUES ('Multitasking', 'Multitarea', 'Adj', '" + currentDate + "', '" + currentDate + "', 0 )");

        db.execSQL("INSERT INTO Entrada (palabra_eng, palabra_esp, pabla_tipo, fecha_intro, fecha_latest_test, num_aciertos) " +
                "VALUES ('To Reboot', 'Reiniciar', 'Verbo', '" + currentDate + "', '" + currentDate + "', 0 )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Se elimina la versión anterior de la tabla
        db.execSQL("DROP TABLE IF EXISTS Entrada");
        //Se crea la nueva versión de la tabla
        db.execSQL(sqlCreate);
    }

    //---------------------
    // CONSULTAS - QUERYS
    //---------------------

    /**
     * Obtenemos nuestro Listado de entradas completo
     */
    public List<Entrada_Diccionario> getListadoEntradas() {
        List<Entrada_Diccionario> list_Entradas = new ArrayList<Entrada_Diccionario>();

        String sql = "SELECT * FROM Entrada";

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                Entrada_Diccionario entrada = new Entrada_Diccionario(cursor.getString(0), cursor.getString(1),
                        cursor.getString(2), stringToDate(cursor.getString(3)),
                        stringToDate(cursor.getString(4)), cursor.getInt(5));

                list_Entradas.add(entrada);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list_Entradas;
    }

    /**
     * Comprobamos que la palabra que vamos a introducir no se encuentra en la DB.
     * Para así podernos evitar que se nos dupliquen las entradas
     * Nos comprobará la palabra en inglés y su traducción al español
     *
     * @param entrada - instancia que vamos a comprobar antes de insertarla
     */
    public boolean cheking_palabraExiste_in_DB(Entrada_Diccionario entrada) {
        boolean coincidence = false;
        String sql = "SELECT * FROM Entrada";

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                if (cursor.getString(0).equalsIgnoreCase(entrada.getPalabra_ing()) &&
                        cursor.getString(1).equalsIgnoreCase(entrada.getPalabra_esp())) {
                    coincidence = true;
                }
            } while (cursor.moveToNext());
        }
        return coincidence;
    }

    /**
     * Nos devuelve la entrada a partir de la palabra en inglés/español
     */
    public int buscarPalabra(String palabra, Entrada_Diccionario entrada) {
        int res = 0;

        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT * FROM Entrada WHERE palabra_eng = '" + palabra + "' " +
                "OR palabra_esp = '" + palabra + "'";
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            entrada.setPalabra_ing(cursor.getString(0));
            entrada.setPalabra_esp(cursor.getString(1));
            entrada.setPalabra_tipo(cursor.getString(2));
            entrada.setFecha_intro(stringToDate(cursor.getString(3)));
            entrada.setFecha_latest_test(stringToDate(cursor.getString(4)));
            entrada.setNumAciertos(cursor.getInt(5));

            res = 1;
        }
        cursor.close();
        return res;
    }

    //-----------
    // CRUD
    //-----------

    /**
     * Nos inserta una entrada en nuestra DB_Diccionario (embebida)
     * Pero antres llamamos al método 'cheking_palabraExiste_in_DB'
     * para comprobar que no existe esa palabra dentro de la DB
     *
     * @param entrada - Objeto Entrada_Diccionario
     * @return res - Nos devolverá un enterio y dependiendo veremos si ha resultado con éxito o no
     */
    public int insertar_EntradaDiccionario(Entrada_Diccionario entrada) {
        int res = 0;

        SQLiteDatabase db = getWritableDatabase();

        boolean existeEntrada = cheking_palabraExiste_in_DB(entrada);
        if (existeEntrada) {
            res = -1;

        } else {
            db.execSQL("INSERT INTO Entrada (palabra_eng, palabra_esp, pabla_tipo, fecha_intro, fecha_latest_test, num_aciertos) " +
                    "VALUES ('" + entrada.getPalabra_ing() + "', '" + entrada.getPalabra_esp() + "', '" + entrada.getPalabraTipo() + "'," +
                    "'" + dateToString(entrada.getFechaIntro()) + "', '" + dateToString(entrada.getFecha_latest_test()) + "', " + entrada.getNumAciertos() + ")");
            res = 1;
        }
        return res;
    }


    /**
     * Nos devuelve un List ordenado dependiendo de las opciones que hayamos seleccionado en los Spinner
     *
     * @param op_alfaNum
     * @param op_ingEsp
     * @param op_palabExpres
     */
    public List<Entrada_Diccionario> obtenerArrayConsultas_Spinner(String op_alfaNum, String op_ingEsp, String op_palabExpres) {
        List<Entrada_Diccionario> list_Entradas = new ArrayList<Entrada_Diccionario>();
        String sql = "";
        SQLiteDatabase db;

        //Ninguna es null
        if (op_alfaNum != null && op_ingEsp != null && op_palabExpres != null) {
            if (op_alfaNum.equalsIgnoreCase("Alfabético") && op_ingEsp.equalsIgnoreCase("Inglés") && op_palabExpres.equalsIgnoreCase("Palabra")) {
                sql = "SELECT * FROM Entrada WHERE pabla_tipo In ('Sust', 'Adj', 'Adv', 'Verbo') " +
                        "ORDER BY palabra_eng ASC, pabla_tipo ASC";

            } else if (op_alfaNum.equalsIgnoreCase("Alfabético") && op_ingEsp.equalsIgnoreCase("Inglés") && op_palabExpres.equalsIgnoreCase("Expresión")) {
                sql = "SELECT * FROM Entrada WHERE pabla_tipo = 'Expres'  " +
                        "ORDER BY palabra_eng ASC";

            } else if (op_alfaNum.equalsIgnoreCase("Alfabético") && op_ingEsp.equalsIgnoreCase("Español") && op_palabExpres.equalsIgnoreCase("Palabra")) {
                sql = "SELECT * FROM Entrada WHERE pabla_tipo In ('Sust', 'Adj', 'Adv', 'Verbo') " +
                        "ORDER BY palabra_esp ASC, pabla_tipo ASC";

            } else if (op_alfaNum.equalsIgnoreCase("Alfabético") && op_ingEsp.equalsIgnoreCase("Español") && op_palabExpres.equalsIgnoreCase("Expresión")) {
                sql = "SELECT * FROM Entrada WHERE pabla_tipo = 'Expres' " +
                        "ORDER BY palabra_esp ASC";

            } else if (op_alfaNum.equalsIgnoreCase("Nº Aciertos") && op_ingEsp.equalsIgnoreCase("Inglés") && op_palabExpres.equalsIgnoreCase("Palabra")) {
                sql = "SELECT * FROM Entrada WHERE pabla_tipo In ('Sust', 'Adj', 'Adv', 'Verbo') " +
                        "ORDER BY num_aciertos ASC, palabra_eng ASC";

            } else if (op_alfaNum.equalsIgnoreCase("Nº Aciertos") && op_ingEsp.equalsIgnoreCase("Inglés") && op_palabExpres.equalsIgnoreCase("Expresión")) {
                sql = "SELECT * FROM Entrada WHERE pabla_tipo = 'Expres' " +
                        "ORDER BY num_aciertos ASC, palabra_eng ASC";

            } else if (op_alfaNum.equalsIgnoreCase("Nº Aciertos") && op_ingEsp.equalsIgnoreCase("Español") && op_palabExpres.equalsIgnoreCase("Palabra")) {
                sql = "SELECT * FROM Entrada WHERE pabla_tipo In ('Sust', 'Adj', 'Adv', 'Verbo') " +
                        "ORDER BY num_aciertos ASC, palabra_esp ASC";

            } else if (op_alfaNum.equalsIgnoreCase("Nº Aciertos") && op_ingEsp.equalsIgnoreCase("Español") && op_palabExpres.equalsIgnoreCase("Expresión")) {
                sql = "SELECT * FROM Entrada WHERE pabla_tipo = 'Expres' " +
                        "ORDER BY num_aciertos ASC, palabra_esp ASC";

            }
            //op_palabExpres == null
        } else if (op_alfaNum != null && op_ingEsp != null) {
            if (op_alfaNum.equalsIgnoreCase("Alfabético") && op_ingEsp.equalsIgnoreCase("Inglés")) {
                sql = "SELECT * FROM Entrada ORDER BY palabra_eng ASC";

            } else if (op_alfaNum.equalsIgnoreCase("Alfabético") && op_ingEsp.equalsIgnoreCase("Español")) {
                sql = "SELECT * FROM Entrada ORDER BY palabra_esp ASC";

            } else if (op_alfaNum.equalsIgnoreCase("Nº Aciertos") && op_ingEsp.equalsIgnoreCase("Inglés")) {
                sql = "SELECT * FROM Entrada ORDER BY num_aciertos ASC, palabra_eng ASC";

            } else if (op_alfaNum.equalsIgnoreCase("Nº Aciertos") && op_ingEsp.equalsIgnoreCase("Español")) {
                sql = "SELECT * FROM Entrada ORDER BY num_aciertos ASC, palabra_esp ASC";

            }
            //op_ingEsp == null
        } else if (op_alfaNum != null && op_palabExpres != null) {
            if (op_alfaNum.equalsIgnoreCase("Alfabético") && op_palabExpres.equalsIgnoreCase("Palabra")) {
                sql = "SELECT * FROM Entrada WHERE pabla_tipo In ('Sust', 'Adj', 'Adv', 'Verbo')" +
                        " ORDER BY palabra_eng ASC, pabla_tipo ASC";

            } else if (op_alfaNum.equalsIgnoreCase("Alfabético") && op_palabExpres.equalsIgnoreCase("Expresión")) {
                sql = "SELECT * FROM Entrada WHERE pabla_tipo = 'Expres' " +
                        "ORDER BY palabra_eng ASC";

            } else if (op_alfaNum.equalsIgnoreCase("Nº Aciertos") && op_palabExpres.equalsIgnoreCase("Palabra")) {
                sql = "SELECT * FROM Entrada WHERE pabla_tipo In ('Sust', 'Adj', 'Adv', 'Verbo')" +
                        " ORDER BY palabra_eng ASC, pabla_tipo ASC";

            } else if (op_alfaNum.equalsIgnoreCase("Nº Aciertos") && op_palabExpres.equalsIgnoreCase("Expresión")) {
                sql = "SELECT * FROM Entrada WHERE pabla_tipo = 'Expres' " +
                        "ORDER BY palabra_eng ASC";

            }

            //op_alfaNum == null
        } else if (op_ingEsp != null && op_palabExpres != null) {
            if (op_ingEsp.equalsIgnoreCase("Inglés") && op_palabExpres.equalsIgnoreCase("Palabra")) {
                sql = "SELECT * FROM Entrada WHERE pabla_tipo In ('Sust', 'Adj', 'Adv', 'Verbo')" +
                        " ORDER BY palabra_eng ASC, pabla_tipo ASC";

            } else if (op_ingEsp.equalsIgnoreCase("Inglés") && op_palabExpres.equalsIgnoreCase("Expresión")) {
                sql = "SELECT * FROM Entrada WHERE pabla_tipo = 'Expres' " +
                        "ORDER BY palabra_eng ASC";

            } else if (op_ingEsp.equalsIgnoreCase("Español") && op_palabExpres.equalsIgnoreCase("Palabra")) {
                sql = "SELECT * FROM Entrada WHERE pabla_tipo In ('Sust', 'Adj', 'Adv', 'Verbo')" +
                        " ORDER BY palabra_esp ASC, pabla_tipo ASC";

            } else if (op_ingEsp.equalsIgnoreCase("Español") && op_palabExpres.equalsIgnoreCase("Expresión")) {
                sql = "SELECT * FROM Entrada WHERE pabla_tipo = 'Expres' " +
                        "ORDER BY palabra_esp ASC";

            }
            //op_ingEsp == null && op_palabExpres == null
        } else if (op_alfaNum != null) {
            if (op_alfaNum.equalsIgnoreCase("Alfabético")) {
                sql = "SELECT * FROM Entrada ORDER BY palabra_eng ASC";
            } else {
                sql = "SELECT * FROM Entrada ORDER BY num_aciertos ASC";
            }
        }//op_alfaNum == null && op_palabExpres == null
        else if (op_ingEsp != null) {
            if (op_ingEsp.equalsIgnoreCase("Inglés")) {
                sql = "SELECT * FROM Entrada ORDER BY palabra_eng ASC";
            } else {
                sql = "SELECT * FROM Entrada ORDER BY palabra_esp ASC";
            }
        }
        //op_ingEsp == null && op_alfaNum == null
        else if (op_palabExpres != null) {
            if (op_palabExpres.equalsIgnoreCase("Palabra")) {
                sql = "SELECT * FROM Entrada WHERE pabla_tipo In ('Sust', 'Adj', 'Adv', 'Verbo') ORDER BY pabla_tipo ASC";
            } else {
                sql = "SELECT * FROM Entrada WHERE pabla_tipo = 'Expres' ORDER BY palabra_eng ASC";
            }

        } else {
            sql = "SELECT * FROM Entrada";
        }

        //FIN CONSULTAS
        db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                Entrada_Diccionario entrada = new Entrada_Diccionario(cursor.getString(0), cursor.getString(1),
                        cursor.getString(2), stringToDate(cursor.getString(3)),
                        stringToDate(cursor.getString(4)), cursor.getInt(5));

                list_Entradas.add(entrada);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list_Entradas;
    }

    /**
     * Nos devuelve un Array ordenado dependiendo de las opciones que hayamos escrito en el EditText
     * Encuentra coincidencias tanto en inglés como en español
     *
     * @param editText_palabra
     */
    public List<Entrada_Diccionario> obtenerArrayConsultas_EditText(String editText_palabra) {
        List<Entrada_Diccionario> list_Entradas = new ArrayList<Entrada_Diccionario>();
        String sql = "";
        SQLiteDatabase db;


        if (editText_palabra == null) {
            sql = "SELECT * FROM Entrada";

        } else {
            sql = "SELECT * FROM Entrada WHERE palabra_eng LIKE '%" + editText_palabra + "%' " +
                    "OR palabra_esp LIKE '%" + editText_palabra + "%' ";
        }
        //EJEC Consulta
        db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                Entrada_Diccionario entrada = new Entrada_Diccionario(cursor.getString(0), cursor.getString(1),
                        cursor.getString(2), stringToDate(cursor.getString(3)),
                        stringToDate(cursor.getString(4)), cursor.getInt(5));

                list_Entradas.add(entrada);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list_Entradas;
    }


    //-----------
    //UTILS
    //-----------

    /**
     * Cambio de formato DATE a STRING
     * Nos será necesario a la hora de introducir nuestros datos, ya que SQLiteOpenHelper no acepta del tipo 'DATE'
     * pero si cadenas de TEXTO
     *
     * @param date
     */
    public String dateToString(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        return dateFormat.format(date);
    }


    /**
     * Cambio de formato STRING a DATE.
     * Nos será necesario a la hora de obtener nuestros datos y transformarlos a POJO
     * Ya que cuentan con 2 variables de tipo DATE
     *
     * @param date
     */
    public Date stringToDate(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        try {
            return dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return Calendar.getInstance().getTime();
    }

}
