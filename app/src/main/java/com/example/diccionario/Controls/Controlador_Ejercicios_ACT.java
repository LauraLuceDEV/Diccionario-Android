package com.example.diccionario.Controls;

import com.example.diccionario.Models.Diccionario_DAO;
import com.example.diccionario.POJOS.Entrada_Diccionario;
import java.util.Collections;
import java.util.List;

public class Controlador_Ejercicios_ACT {
    private int numEjecuciones;
    private final List<Entrada_Diccionario> listaEntradas;

    public Controlador_Ejercicios_ACT() {
        this.numEjecuciones = 0;
        this.listaEntradas = Diccionario_DAO.getInstance().getListadoEntradas();
    }

    /**
     * Método que nos realizará el número de ejecuciones para realizar el Test
     * Llamará al método 'obtenerElemetos' para rellenarnos nuestro Array
     *
     * @param entradas
     */
    public Boolean refrescarElementos_ctrl(List<Entrada_Diccionario> entradas) {
        boolean continuaTest = false;
        if (this.numEjecuciones < 5) {

            obtenerElemetos(entradas);
            this.numEjecuciones++;
            continuaTest = true;
        }
        return continuaTest;
    }

    /**
     * Nos rellenará el array dependiendo del núm_ejecuciones
     */
    private void obtenerElemetos(List<Entrada_Diccionario> entrada) {
        for (int i = 0; i < 4; i++) {
            int num_pos_random = (int) (Math.random() * this.listaEntradas.size());
            entrada.add(this.listaEntradas.get(num_pos_random));
            this.listaEntradas.remove(num_pos_random);
        }

    }

    /**
     * Nos devolverá un listado con los elementos desordenados
     */
    public List<Entrada_Diccionario> obtenerElemDesordenados(List<Entrada_Diccionario> lista_palabras) {
        Collections.shuffle(lista_palabras);
        return lista_palabras;
    }

    /**
     * Nos creará unas intancias de Entrada_Diccionario y nos comprobará que coinciden sus traducciones
     * Nos devolverá un boolean
     *
     * @param txt_Respuesta
     * @param txtButton
     */
    public boolean comprobarCoincidencias(String txtButton, String txt_Respuesta) {
        Boolean coincidencias = false;
        Entrada_Diccionario entradaBoton = new Entrada_Diccionario();
        Entrada_Diccionario entradaAdivinar = new Entrada_Diccionario();

        int res1 = Diccionario_DAO.getInstance().buscarPalabra(txtButton, entradaBoton);
        int res2 = Diccionario_DAO.getInstance().buscarPalabra(txt_Respuesta, entradaAdivinar);

        if (res1 == 1 && res2 == 1) {
            if (entradaBoton.getPalabra_esp().equalsIgnoreCase(entradaAdivinar.getPalabra_esp()) &&
                    entradaBoton.getPalabra_ing().equalsIgnoreCase(entradaAdivinar.getPalabra_ing())) {
                coincidencias = true;
            } else {
                coincidencias = false;
            }
        }
        return coincidencias;
    }
}
