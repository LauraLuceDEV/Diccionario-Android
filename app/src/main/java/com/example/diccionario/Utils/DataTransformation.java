package com.example.diccionario.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DataTransformation {

    /**
     * Cambio de formato DATE a STRING
     * Nos será necesario a la hora de introducir nuestros datos, ya que SQLiteOpenHelper no acepta del tipo 'DATE'
     * pero si cadenas de TEXTO
     *
     * @param date
     */
    public static String dateToString(Date date) {
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
    public static Date stringToDate(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        try {
            return dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return Calendar.getInstance().getTime();
    }
}
