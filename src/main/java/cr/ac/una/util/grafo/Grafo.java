package cr.ac.una.util.grafo;

public class Grafo {

    public Grafo(int[][] matriz) {
        this.matrizAdyacencia = matriz;
    }

    /**
     * retorna el numero minimo de saltos entre dos nodos, -1 si es infinito, usa el
     * algoritmo de Dijkstra
     * 
     * @param vertice el vertice
     * @return el numero minimo de saltos entre los vertices
     */
    public int[] rutaMasCorta(int vertice) {
        int v = matrizAdyacencia.length;
        int costos[][] = new int[v][v];
        int distancia[] = new int[v];
        int pred[] = new int[v];
        int visitados[] = new int[v];

        // inicializamos la matriz de costos
        for (int i = 0; i < v; i++) {
            for (int j = 0; j < v; j++) {
                if (matrizAdyacencia[i][j] == 0) {
                    costos[i][j] = Integer.MAX_VALUE;
                } else {
                    costos[i][j] = matrizAdyacencia[i][j];
                }
            }
        }
        // inicializamos pred, distancia y visitados
        for (int i = 0; i < v; i++) {
            distancia[i] = costos[vertice][i];
            pred[i] = vertice;
            visitados[i] = 0;
        }
        distancia[vertice] = 0;
        visitados[vertice] = 1;
        int nodoSiguiente = 0;
        for (int contador = 0; contador < v - 1; contador++) {
            int distanciaMinima = Integer.MAX_VALUE;
            // encontramos el nodo con la distancia minima
            for (int i = 0; i < visitados.length; i++) {
                if (distancia[i] < distanciaMinima && visitados[i] != 0) {
                    distanciaMinima = distancia[i];
                    nodoSiguiente = i;
                }
            }
            // verificamos si existe un camino mas corto desde el nodo encontrado
            visitados[nodoSiguiente] = 1;
            for (int i = 0; i < v; i++) {
                if (visitados[i] != 0) {
                    if (distanciaMinima + costos[nodoSiguiente][i] < distancia[i]) {
                        distancia[i] = distanciaMinima + costos[nodoSiguiente][i];
                        pred[i] = nodoSiguiente;
                    }
                }
            }
        }

        return distancia;
    }

    public int[][] generarMatrizRutaCorta() {
        int v = matrizAdyacencia.length;
        int[][] matriz = new int[v][v];
        for (int i = 0; i < matriz.length; i++) {
            matriz[i] = rutaMasCorta(i);
        }
        return matriz;
    }

    int[][] matrizAdyacencia;

}
