package cr.ac.una.ejercicios;

import cr.ac.una.util.grafo.Arbol;
import cr.ac.una.util.grafo.NodoArbol;

public class Ejercicio3 {
    public void run() {
        NodoArbol[] nodos = new NodoArbol[11];
        for (int i = 0; i < nodos.length; i++) {
            nodos[i] = new NodoArbol(String.valueOf(i + 1));
        }
        // 1->[2,3,4]
        nodos[0].agregarHijo(nodos[1]);
        nodos[0].agregarHijo(nodos[2]);
        nodos[0].agregarHijo(nodos[3]);
        // 2->[5,6]
        nodos[1].agregarHijo(nodos[4]);
        nodos[1].agregarHijo(nodos[5]);
        // 4->[7,8,9]
        nodos[3].agregarHijo(nodos[6]);
        nodos[3].agregarHijo(nodos[7]);
        nodos[3].agregarHijo(nodos[8]);
        // 7->[10,11]
        nodos[6].agregarHijo(nodos[9]);
        nodos[6].agregarHijo(nodos[10]);

        Arbol arbol1 = new Arbol(nodos[0]);
        String nodoA = "4";
        Arbol arbol2 = arbol1.generarArbol(nodoA);
        System.out.print("el arbol 2 ");
        if (arbol1.esSubArbol(arbol2)) {
            System.out.println("es sub arbol");
        } else {
            System.out.println("no es sub arbol");
        }
    }
}
