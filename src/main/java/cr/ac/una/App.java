package cr.ac.una;

import cr.ac.una.ejercicios.Ejercicio1;
import cr.ac.una.ejercicios.Ejercicio3;

public final class App {
    private App() {
    }

    public static void main(String[] args) {
        // ejercicio 1
        System.out.println("Ejercicio 1");
        new Ejercicio1().run();
        System.out.println("Ejercicio 3");
        new Ejercicio3().run();
    }
}
