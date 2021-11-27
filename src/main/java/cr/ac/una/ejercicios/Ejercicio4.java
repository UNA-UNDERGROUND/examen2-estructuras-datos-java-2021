package cr.ac.una.ejercicios;

import java.util.List;

import cr.ac.una.util.grafo.Grafo;
import cr.ac.una.util.grafo.Nodo;

public class Ejercicio4 {
    private static void imprimirRuta(List<Nodo> ruta, boolean esCiclo) {
        System.out.println("Ruta Hamiltoniana:");
        if (ruta.isEmpty()) {
            System.out.println("No hay ruta Hamiltoniana");
            return;
        }
        for (int i = 0; i < ruta.size(); i++) {
            String nodo = ruta.get(i).getId();
            System.out.print(nodo);
            if (i < ruta.size() - 1) {
                System.out.print("->");
            }
        }
        if (esCiclo) {
            System.out.print("->");
            System.out.println(ruta.get(0).getId());
        }
    }

    public static void run() {
        // creamos el grafo
        Grafo grafo = new Grafo();
        // agregamos los nodos
        for (int i = 0; i < 10; i++) {
            grafo.agregarNodo(String.valueOf(65 + i));
        }
        // agregamos las aristas, omitimos las que ya estan
        // osea los numeros menores al actual
        // 1->[3]
        grafo.agregarArista("1", "3", 1);
        // 2->[3, 4]
        grafo.agregarArista("2", "3", 1);
        grafo.agregarArista("2", "4", 1);
        // 3->[1, 2, 4] (ommitimos 3->[1, 2])
        grafo.agregarArista("3", "1", 1);
        grafo.agregarArista("3", "2", 1);
        grafo.agregarArista("3", "4", 1);
        // 4->[2, 3, 5, 6] (omitimos 4->[2, 3])
        grafo.agregarArista("4", "2", 1);
        grafo.agregarArista("4", "3", 1);
        grafo.agregarArista("4", "5", 1);
        grafo.agregarArista("4", "6", 1);
        // 5->[4] (ommitimos 5->[4])
        grafo.agregarArista("5", "4", 1);
        // 6->[4, 7, 8, 9] (ommitimos 6->[4])
        grafo.agregarArista("6", "4", 1);
        grafo.agregarArista("6", "7", 1);
        grafo.agregarArista("6", "8", 1);
        grafo.agregarArista("6", "9", 1);
        // 7->[6, 8] (ommitimos 7->[6])
        grafo.agregarArista("7", "6", 1);
        grafo.agregarArista("7", "8", 1);
        // 8->[6, 7] (ommitimos 8->[6, 7])
        grafo.agregarArista("8", "6", 1);
        grafo.agregarArista("8", "7", 1);
        // 9->[6] (ommitimos 9->[6])
        grafo.agregarArista("9", "6", 1);


        // imprimimos el ciclo hamiltoniano
        List<Nodo> ciclo = grafo.cicloHamiltoniano();
        List<Nodo> camino = grafo.caminoHamiltoniano();
        imprimirRuta(ciclo, true);
        imprimirRuta(camino, false);

    }
}
