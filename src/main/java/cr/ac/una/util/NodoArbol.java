package cr.ac.una.util;

import java.util.ArrayList;
import java.util.List;

/**
 * clase que representa un nodo del arbol con n hijos
 */
public class NodoArbol {

    public NodoArbol(String nombre) {
        this.nombre = nombre;
    }

    public NodoArbol(String nombre, NodoArbol padre) {
        this.nombre = nombre;
        this.padre = padre;
    }

    public List<NodoArbol> getHijos() {
        return hijos;
    }

    public void agregarHijo(NodoArbol hijo) {
        hijos.add(hijo);
        hijo.padre = this;
    }

    /**
     * metodo recursivo el cual recupera los ancestros del nodo
     * 
     * @return lista de ancestros
     */
    public List<NodoArbol> getAncestros() {
        List<NodoArbol> ancestros = new ArrayList<>();
        ancestros.add(this);
        if (padre != null) {
            ancestros.addAll(padre.getAncestros());
        }
        return ancestros;
    }

    public NodoArbol getAncestroComunCercano(NodoArbol nodo) {
        if (nodo == null) {
            return null;
        }
        List<NodoArbol> ancestros = getAncestros();
        List<NodoArbol> ancestros2 = nodo.getAncestros();
        // verificamos los ancestros del nodo 1
        for (NodoArbol nodoArbol : ancestros2) {
            // buscamos el primer ancestro que coincida
            if (ancestros.contains(nodoArbol)) {
                return nodoArbol;
            }
        }
        return null;
    }

    /**
     * recupera una lista de ancestros comunes en un arbol
     * 
     * @param nodo nodo a comparar
     * @return lista de ancestros comunes
     */
    public List<NodoArbol> getAncestrosComunes(NodoArbol nodo) {
        List<NodoArbol> ancestros = getAncestros();
        NodoArbol ancestroComun = getAncestroComunCercano(nodo);
        if (ancestroComun != null) {
            ancestros.add(ancestroComun);
            ancestros.addAll(ancestroComun.getAncestros());
        }
        return ancestros;
    }

    /**
     * recupera la altura del nodo
     * 
     * @return altura del nodo
     */
    public Integer getAltura() {
        List<NodoArbol> ancestros = getAncestros();
        return ancestros.size();
    }

    /**
     * obtiene la diferrencia absoluta entre la altura del nodo y el nodo dado
     * 
     * @param nodo nodo a comparar
     * @return diferencia entre la altura del nodo y el nodo dado
     */
    public Integer diferenciaAltura(NodoArbol nodo) {
        return Math.abs(getAltura() - nodo.getAltura());
    }

    /**
     * recupera el nodo raiz, si no hay nodo raiz entonces es el mismo
     * 
     * @return nodo raiz
     */
    public NodoArbol getRaiz() {
        return padre == null ? this : padre.getRaiz();
    }

    /**
     * obtiene la distancia entre el nodo y el nodo dado
     * 
     * @param nodo nodo a comparar
     * @return distancia entre el nodo y el nodo dado
     */
    public Integer distanciaNodos(NodoArbol nodo) {
        // primero verificamos el ancestro comun
        NodoArbol ancestroComun = getAncestroComunCercano(nodo);
        // si no hay ancestro comun, entonces no existen en el mismo arbol
        if (ancestroComun == null) {
            return -1;
        }

        // verificamos si uno de los nodos es ancestro comun
        // si es asi, sumamos la distancia entre el ancestro comun y el nodo
        if (ancestroComun.nombre.equals(this.getNombre())) {
            return ancestroComun.diferenciaAltura(nodo);
        }
        if (ancestroComun.getNombre().equals(nodo.nombre)) {
            return nodo.diferenciaAltura(this);
        }
        // si hay solo un ancestro comun, significa que el unico ancestro es la raíz
        // por lo tanto sumamos ambas alturas
        if (ancestroComun.padre == null) {
            return getAltura() + nodo.getAltura();
        }

        // si no lo es necesitamos sumar las distancias entre el ancestro común
        int distancia1 = diferenciaAltura(ancestroComun);
        int distancia2 = nodo.diferenciaAltura(ancestroComun);
        return distancia1 + distancia2;
    }

    public NodoArbol buscarNodo(String nombre) {
        if (this.nombre.equals(nombre)) {
            return this;
        }
        for (NodoArbol hijo : hijos) {
            NodoArbol nodo = hijo.buscarNodo(nombre);
            if (nodo != null) {
                return nodo;
            }
        }
        return null;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    private String nombre = "";
    private NodoArbol padre = null;
    private List<NodoArbol> hijos = new ArrayList<>();
}
