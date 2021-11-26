package cr.ac.una.ejercicios;

import cr.ac.una.util.grafo.Grafo;

public class Ejercicio5 {

    /**
     * imprime una matriz con filas y columnas alfabéticas
     * 
     * @param matriz matriz a imprimir
     */
    public void imprimirMatriz(int[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                // verificamos si es infinito
                if (matriz[i][j] == Integer.MAX_VALUE) {
                    System.out.print("INF ");
                } else {
                    System.out.print(matriz[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    public void imprimirVector(int[] vector) {
        for (int i = 0; i < vector.length; i++) {
            // verificamos si es infinito
            if (vector[i] == Integer.MAX_VALUE) {
                System.out.print("INF ");
            } else {
                System.out.print(vector[i] + " ");
            }
        }
        System.out.println();
    }

    public void run() {
        // - A B C D E F G H
        // A 0 1 1 0 0 0 0 0
        // B 1 0 1 1 0 0 0 0
        // C 1 1 0 1 1 0 0 0
        // D 0 1 1 0 0 0 0 0
        // E 0 0 1 0 0 1 0 0
        // F 0 0 0 0 1 0 1 1
        // G 0 0 0 0 0 1 0 1
        // H 0 0 0 0 0 1 1 0

        int matrizAdyacencia[][] = { //
                { 0, 1, 1, 0, 0, 0, 0, 0 }, //
                { 1, 0, 1, 1, 0, 0, 0, 0 }, //
                { 1, 1, 0, 1, 1, 0, 0, 0 }, //
                { 0, 1, 1, 0, 0, 0, 0, 0 }, //
                { 0, 0, 1, 0, 0, 1, 0, 0 }, //
                { 0, 0, 0, 0, 1, 0, 1, 1 }, //
                { 0, 0, 0, 0, 0, 1, 0, 1 }, //
                { 0, 0, 0, 0, 0, 1, 1, 0 } //
        };

        Grafo grafo = new Grafo(matrizAdyacencia);
        imprimirVector(grafo.rutaMasCorta(0));
        //imprimirMatriz(grafo.generarMatrizRutaCorta());

    }
}
