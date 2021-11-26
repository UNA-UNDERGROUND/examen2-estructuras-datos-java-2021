package cr.ac.una.util.arbol;

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

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Arbol other = (Arbol) obj;
        if (this.raiz != other.raiz && (this.raiz == null || !this.raiz.equals(other.raiz))) {
            return false;
        }
        return true;
    }

    public Arbol generarArbol(String nodo) {
        NodoArbol nodoArbol = buscarNodo(nodo);
        if (nodoArbol == null) {
            return null;
        }
        return new Arbol(nodoArbol);
    }

    public boolean esSubArbol(Arbol arbol) {
        Arbol arbolGen = generarArbol(arbol.getRaiz().getNombre());
        if (arbolGen == null) {
            return false;
        }
        return arbolGen.equals(arbol);
    }

    private NodoArbol raiz;
}
