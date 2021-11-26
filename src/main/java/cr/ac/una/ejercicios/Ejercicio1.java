package cr.ac.una.ejercicios;

import cr.ac.una.util.Arbol;
import cr.ac.una.util.NodoArbol;

public class Ejercicio1 {
    public void run() {
        // creamos el arbol
        NodoArbol[] nodos = new NodoArbol[12];
        for (int i = 0; i < nodos.length; i++) {
            nodos[i] = new NodoArbol(String.valueOf(i + 1));
        }
        // 1->[2,3,4]
        nodos[0].agregarHijo(nodos[1]);
        nodos[0].agregarHijo(nodos[2]);
        nodos[0].agregarHijo(nodos[3]);
        // 2->[5]
        nodos[1].agregarHijo(nodos[4]);
        // 3->[6,7]
        nodos[2].agregarHijo(nodos[5]);
        nodos[2].agregarHijo(nodos[6]);
        // 6->[8]
        nodos[5].agregarHijo(nodos[7]);
        // 7->[9,10]
        nodos[6].agregarHijo(nodos[8]);
        nodos[6].agregarHijo(nodos[9]);
        // 9->[11,12]
        nodos[8].agregarHijo(nodos[10]);
        nodos[8].agregarHijo(nodos[11]);
        Arbol arbol = new Arbol(nodos[0]);
        String nodoA = "8";
        String nodoB = "12";
        System.out.println( //
                "la distancia entre el nodo" + nodoA //
                        + " y el nodo " + nodoB //
                        + " es " + arbol.distanciaNodos(nodoA, nodoB));
        nodoA = "3";
        nodoB = "10";
        System.out.println( //
                "la distancia entre el nodo" + nodoA //
                        + " y el nodo " + nodoB //
                        + " es " + arbol.distanciaNodos(nodoA, nodoB));

    }
}
