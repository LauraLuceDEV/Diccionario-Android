package com.example.diccionario.Controls;

import com.example.diccionario.Models.Diccionario_DAO;
import com.example.diccionario.POJOS.Entrada_Diccionario;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Ctrl_EjerciciosFlechas {

    //Nos devuelve un listado de las palabras en ingles de nuestro listado de <Entrada_Diccionario>
    public static List<String> getListadoPalabrasIngles(List<Entrada_Diccionario> listaPalabras) {
        List<String> lstPalabrasIngles = new ArrayList<String>();
        //Obtenemos la palabra en inglés
        for (Entrada_Diccionario entrada: listaPalabras){
            lstPalabrasIngles.add(entrada.getPalabra_ing());
        }
        // La devolvemos desordenadas
        Collections.shuffle(lstPalabrasIngles);
        return lstPalabrasIngles;
    }

    //Nos devuelve un listado de las palabras en español de nuestro listado de <Entrada_Diccionario>
    public static List<String> getListadoPalabrasEspañol(List<Entrada_Diccionario> listaPalabras) {
        List<String> lstPalabrasEspañol = new ArrayList<String>();
        //Obtenemos la palabra en inglés
        for (Entrada_Diccionario entrada: listaPalabras){
            lstPalabrasEspañol.add(entrada.getPalabra_esp());
        }
        // La devolvemos desordenadas
        Collections.shuffle(lstPalabrasEspañol);
        return lstPalabrasEspañol;
    }

    //Comprobamos si coinciden las palabras
    public static boolean comprobarCoincidencia(String palabraIng, String palabraEsp) {
        boolean coincidenPalabras = false;
        Entrada_Diccionario entrada = Diccionario_DAO.getInstance().getEntradaDiccionariofrompalabraInglesorEspannol(palabraIng);

        //Comprobamos las palabras si coinciden
        if(entrada.getPalabra_esp().equalsIgnoreCase(palabraEsp) && entrada.getPalabra_ing().equalsIgnoreCase(palabraIng)){
            coincidenPalabras = true;
        }
        return coincidenPalabras;
    }

    //Nos calcula el porcenatje de fallos
    public static String calcularPorcentaje(int numFallos, int numAciertos) {
        double res = 100 - ((numFallos * 100)/numAciertos);
        return res + "% ";
    }
}
