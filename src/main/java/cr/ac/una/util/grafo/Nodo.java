package cr.ac.una.util.grafo;

import java.util.ArrayList;

/**
 * clase que representa un nodo de un grafo, la cual se utiliza para el camino y
 * circuito hamiltoniano
 */
public class Nodo {

    public Nodo(String id) {
        this.id = id;
        this.adyacentes = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<Arista> getAristas() {
        return adyacentes;
    }

    public void agregarAdyacente(Nodo nodo, int peso) {
        this.adyacentes.add(new Arista(peso, nodo));
    }

    public boolean esAdyacente(Nodo nodo) {
        for (Arista arista : adyacentes) {
            if (arista.getNodoDestino().equals(nodo)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Nodo other = (Nodo) obj;
        if ((this.id == null) ? (other.id != null) : !this.id.equals(other.id)) {
            return false;
        }
        return true;
    }

    private String id;
    private ArrayList<Arista> adyacentes = new ArrayList<>();
}
