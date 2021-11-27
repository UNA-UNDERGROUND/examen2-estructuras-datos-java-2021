package cr.ac.una.util.grafo;

import java.util.ArrayList;
import java.util.List;

public class Grafo {

    public Grafo() {
    }

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

    int[][] matrizAdyacencia = null;


    /**
     * nos indica si un nodo es valido como siguiente salto
     * <ul>
     * <li>no es el mismo nodo, esto se cumple ya que se marca como visitado antes
     * de llamarse esta funcion</li>
     * <li>no es un nodo de la lista de visitados</li>
     * <li>tiene una arista con el nodo actual</li>
     * </ul>
     * 
     * @param nodo             nodo actual
     * @param matrizAdyacencia matriz de adyacencia
     * @param visitados        lista de visitados
     * @param posicion         posicion de la lista de visitados
     * @return true si se puede visitar, false en caso contrario
     */
    private boolean puedeSaltar(int nodo, int[][] matrizAdyacencia, int[] visitados, int posicion) {

        // verifica si el nodo es adyacente al nodo previo
        // en otras palabras solo podemos ir a los nodos adyacentes
        if (matrizAdyacencia[visitados[posicion - 1]][nodo] == 0) {
            return false;
        }

        // verifica si el nodo ya fue visitado
        for (int i = 0; i < posicion; i++) {
            if (visitados[i] == nodo) {
                return false;
            }
        }
        return true;
    }

    /**
     * funcion recursiva que resuelve el ciclo hamiltoniano
     * 
     * @param matrizAdj matriz de adyacencia
     * @param visitados lista de nodos visitados
     * @param posicion  nodo actual
     * @param esCiclo   si es ciclo o no
     * @return true si se encuentra un ciclo hamiltoniano
     */
    private Boolean HamiltonianoR(int[][] matrizAdj, int[] visitados, int posicion, boolean esCiclo) {
        int v = matrizAdj.length;
        // caso base, estan todos los nodos visitados
        if (posicion == v) {
            // verifica si hay una arista entre el ultimo nodo visitado y el primero
            return matrizAdj[visitados[v - 1]][visitados[0]] == (esCiclo ? 1 : 0);
        }

        // intentamos visitar cada nodo
        for (int i = 0; i < visitados.length; i++) {
            // verifica si el vertice puede ser agregado al ciclo hamiltoniano
            if (puedeSaltar(i, matrizAdj, visitados, posicion)) {
                // agrega el vertice al ciclo hamiltoniano
                visitados[posicion] = i;
                // verifica si se encuentra un ciclo hamiltoniano
                if (HamiltonianoR(matrizAdj, visitados, posicion + 1, esCiclo)) {
                    return true;
                }
                // si no se encuentra un ciclo hamiltoniano, se elimina el vertice
                visitados[posicion] = -1;
            }
        }

        // si no se puede realizar mas operaciones, se retorna false
        return false;
    }

    /**
     * funcion que verifica si el grafo tiene un ciclo hamiltoniano
     * 
     * @return la lista de nodos que conforman el ciclo hamiltoniano
     */
    public List<Nodo> cicloHamiltoniano() {
        return resolverHamiltoniano(true);
    }

    /**
     * funcion que verifica si el grafo tiene un circuito hamiltoniano
     * 
     * @return la lista de nodos que conforman el camino hamiltoniano
     */
    public List<Nodo> caminoHamiltoniano() {
        return resolverHamiltoniano(false);
    }

    /**
     * funcion que resuelve el circuito/ciclo hamiltoniano
     * 
     * @param esCiclo si es ciclo o no
     * @return lista de nodos visitados
     */
    public List<Nodo> resolverHamiltoniano(boolean esCiclo) {
        int[][] matrizAdyacencia = generarMatrizAdyacencia();
        int v = matrizAdyacencia.length;
        int[] ruta = new int[v];
        for (int i = 0; i < matrizAdyacencia.length; i++) {
            ruta[i] = -1;
        }
        // tenemos que establecer un vertice inicial
        // como es un ciclo, el vertice inicial puede ser cualquiera
        // en este caso, el vertice 0
        ruta[0] = 0;
        if (!HamiltonianoR(matrizAdyacencia, ruta, 1, esCiclo)) {
            return new ArrayList<Nodo>();
        }
        List<Nodo> resultado = new ArrayList<>();
        for (int i = 0; i < ruta.length; i++) {
            resultado.add(nodos.get(ruta[i]));
        }
        return resultado;
    }

    private int[][] generarMatrizAdyacencia() {
        // este metodo se puede optimizar
        // sin embargo, para el ejemplo, se deja asi
        int v = nodos.size();
        int[][] matrizAdyacencia = new int[v][v];
        for (int i = 0; i < matrizAdyacencia.length; i++) {
            matrizAdyacencia[i][i] = 0;
            Nodo nodo = nodos.get(i);
            for (int j = 0; j < matrizAdyacencia.length; j++) {
                // revisamos si el nodo j es adyacente al nodo i
                if (nodo.esAdyacente(nodos.get(j))) {
                    matrizAdyacencia[i][j] = 1;
                }
            }
        }

        return matrizAdyacencia;
    }

    public void agregarNodo(String nombre) {
        // primero verificamos que el nodo no exista
        if (buscarNodo(nombre) != null) {
            return;
        }
        nodos.add(new Nodo(nombre));
    }

    public Nodo buscarNodo(String nombre) {
        for (Nodo nodo : nodos) {
            if (nodo.getId().equals(nombre)) {
                return nodo;
            }
        }
        return null;
    }

    public void agregarArista(String nodo1, String nodo2, int peso) {
        // primero verificamos que los nodos existan
        Nodo n1 = buscarNodo(nodo1);
        Nodo n2 = buscarNodo(nodo2);
        if (n1 == null || n2 == null) {
            return;
        }
        // verificamos que la arista no exista
        if (n1.esAdyacente(n2)) {
            return;
        }
        n1.agregarAdyacente(n2, peso);
        n2.agregarAdyacente(n1, peso);
    }

    List<Nodo> nodos = new ArrayList<>();
}
