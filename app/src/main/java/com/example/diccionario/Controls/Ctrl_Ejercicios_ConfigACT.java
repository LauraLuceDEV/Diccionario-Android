package com.example.diccionario.Controls;

import com.example.diccionario.Models.Diccionario_DAO;
import com.example.diccionario.POJOS.Entrada_Diccionario;
import java.util.Collections;
import java.util.List;



/**
 * Nuestro 'controlador' será un Singleton ya que,
 * en nuestra pantalla de preferencias se realizan una serie de cambios, y para que estos cambios se mantengan tenemos que realizarlo
 * */public class Ctrl_Ejercicios_ConfigACT {
     private static Ctrl_Ejercicios_ConfigACT ctrlSing = null;
    private int numEjecuciones = 5;
    private int contador;
    private final List<Entrada_Diccionario> listaEntradas;


    //CONSTRUCTOR
    private Ctrl_Ejercicios_ConfigACT() {
        contador = 0;
        //Obtenemos todas las entradas del diccioanrio
        this.listaEntradas = Diccionario_DAO.getInstance().getListadoEntradas();
    }

    //Singleton - Lo creamos usando al contructor
    public static Ctrl_Ejercicios_ConfigACT getInstance(){
        if(ctrlSing == null) {
            ctrlSing = new Ctrl_Ejercicios_ConfigACT();
        }

        return ctrlSing;
    }


    //SETTER Y GETTER
    public void setNumEjecuciones(int numEjecciones){ this.numEjecuciones = numEjecciones;}
    public void setListaEntradas(int contador){ this.contador = contador;}

    public int getNumEjecuciones(){return this.numEjecuciones;}
    public List<Entrada_Diccionario> getListaEntradas(){return this.listaEntradas;}
    public int getContador(){return this.contador;}

    /**
     * Método que nos realizará el número de ejecuciones para realizar el Test
     * Llamará al método 'obtenerElemetos' para rellenarnos nuestro Array
     *
     * @param entradas
     */
    public Boolean refrescarElementos_ctrl(List<Entrada_Diccionario> entradas) {
        boolean continuaTest = false;
        if (contador < this.numEjecuciones) {

            obtenerElemetos(entradas);
            contador++;
            continuaTest = true;
        }
        return continuaTest;
    }

    /**
     * MEJORA DEL ALGORITMO
     *  Se rellenará nuestro Listado 'entrada' primero, con  la palabra que tenga menos aciertos
     *  En caso de empate se elegirá la que tenga fecha más antigua
     *  El resto nos la rellenará de forma aleatoria
     *
     * Nos rellenará el array de instancias 'Entrada_Diccionario' dependiendo del núm_ejecuciones
     * @param entrada
     */
    private void obtenerElemetos(List<Entrada_Diccionario> entrada) {
        for (int i = 0; i < 4; i++) {
            if(i == 0){
                Entrada_Diccionario ent = obtenerPrimeraEntrada();
                entrada.add(ent);
                this.listaEntradas.remove(ent);

            }else{
                int num_pos_random = (int) (Math.random() * this.listaEntradas.size());
                entrada.add(this.listaEntradas.get(num_pos_random));
                this.listaEntradas.remove(num_pos_random);
            }
        }
    }

    /**
     * Algoritmo que nos encontrará en el listado la 'entrada' con menos aciertos
     * En caso de empatar, se escogerá aquella 'entrada' con la fecha más antigua
     * */private Entrada_Diccionario obtenerPrimeraEntrada(){
        Entrada_Diccionario ent = this.listaEntradas.get(0);

        for(Entrada_Diccionario entrada: this.listaEntradas){
            if(ent.getNumAciertos() > entrada.getNumAciertos()){
                ent = entrada;

            }else if(ent.getNumAciertos() == entrada.getNumAciertos()){
                if(ent.getFecha_latest_test().after(entrada.getFecha_latest_test())){
                    ent = entrada;
                }
            }
        }
        return  ent;
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
     * Si estas coinciden, se actualizarán las puntuaciones
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
                Diccionario_DAO.getInstance().actualizarPuntuaciones(1,  entradaAdivinar.getPalabra_ing());
                coincidencias = true;
            } else {
                Diccionario_DAO.getInstance().actualizarPuntuaciones(0,  entradaAdivinar.getPalabra_ing());
                coincidencias = false;
            }
        }
        return coincidencias;
    }
}
