package cr.ac.una.util.grafo;

public class Grafo {

    public Grafo(int[][] matriz) {
        this.matrizAdyacencia = matriz;
    }

    private boolean existeArista(int origen, int destino) {
        return matrizAdyacencia[origen][destino] == 1;
    }

    private boolean visitadosTodos(boolean[] visitados) {
        for (boolean visitado : visitados) {
            if (!visitado) {
                return false;
            }
        }
        return true;
    }

    private int tomarMinimo(boolean[] visitados, int[] distancias) {
        int min = Integer.MAX_VALUE;
        int minIndex = -1;
        for (int i = 0; i < distancias.length; i++) {
            if (!visitados[i] && distancias[i] <= min) {
                min = distancias[i];
                minIndex = i;
            }
        }
        return minIndex;
    }

    /**
     * retorna los sucesores de un nodo
     * 
     * @param s nodo
     * @return sucesores
     */
    private int[] sucesores(int s) {
        int V = matrizAdyacencia.length;
        int[] sucesores = new int[V];
        int contador = 0;
        for (int i = 0; i < V; i++) {
            if (matrizAdyacencia[s][i] == 1) {
                sucesores[contador] = i;
                contador++;
            }
        }
        return sucesores;
    }

    /**
     * retorna el numero minimo de saltos entre dos nodos, -1 si es infinito, usa el
     * algoritmo de Dijkstra
     * 
     * @param vertice el vertice
     * @return el numero minimo de saltos entre los vertices
     */
    public int[] rutaMasCorta(int origen) {
        int V = matrizAdyacencia.length;
        int distancia[] = new int[V];
        boolean visitado[] = new boolean[V];

        for (int i = 0; i < V; i++) {
            if (i == origen) {
                distancia[i] = 0;
            } else if (!existeArista(origen, i)) {
                distancia[i] = Integer.MAX_VALUE;
            } else {
                distancia[i] = 1;
            }
        }

        distancia[origen] = 0;
        visitado[origen] = true;
        // mientras no se haya visitado todos los vertices
        while (!visitadosTodos(visitado)) {
            int vertice = tomarMinimo(visitado, distancia);
            visitado[vertice] = true;
            for (int i = 0; i < V; i++) {
                int [] listaSucesores = sucesores(vertice);
                char chr = (char)(vertice + 65);
                for (int w : listaSucesores) {
                    char chrD = (char)(w + 65);
                    int peso = matrizAdyacencia[vertice][w];
                    int val = distancia[vertice] + peso;
                    if (distancia[w] > val) {
                        distancia[w] = val;
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
