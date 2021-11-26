package cr.ac.una.util;

public class Arbol {

    public Arbol(NodoArbol raiz) {
        this.raiz = raiz;
    }

    public NodoArbol getRaiz() {
        return raiz;
    }

    public NodoArbol buscarNodo(String nodo) {
        return raiz.buscarNodo(nodo);
    }

    public Integer distanciaNodos(String nodoA, String nodoB) {
        NodoArbol nodo1 = buscarNodo(nodoA);
        NodoArbol nodo2 = buscarNodo(nodoB);
        if (nodo1 == null || nodo2 == null) {
            return null;
        }
        return nodo1.distanciaNodos(nodo2);
    }

    private NodoArbol raiz;
}
