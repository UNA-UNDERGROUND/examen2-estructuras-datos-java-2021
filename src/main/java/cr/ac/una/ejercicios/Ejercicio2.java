package cr.ac.una.ejercicios;

import java.util.ArrayList;
import java.util.List;

public class Ejercicio2 {
   /**
    * Parte uno del ejercicio sin embargo utilice un metodo iterativo para generar las lineas en la párte 2
    * @param i
    * @param j
    * @return 
    */
    public static int formula(int i, int j){
        if(j==0||j==i){
            return 1;
        }else{
            return formula(i-1, j-1)+ formula(i-1, j);
        }
    }

    /**
     * genera las filas del triangulo de pascal de manera recursiva
     * 
     * EJ:
     * triangulo(8) produce:
     * 
     * la primera linea es [1]
     * la segunda linea es [1,1]
     * la tercera linea es [1,2,1]
     * la cuarta linea es [1,3,3,1]
     * la quinta linea es [1,4,6,4,1]
     * la sexta linea es [1,5,10,10,5,1]
     * la septima linea es [1,6,15,20,15,6,1]
     * la octava linea es [1,7,21,35,35,21,7,1]
     * la novena linea es [1,8,28,56,70,56,28,8,1]
     * 
     *                         1
     *                      1     1
     *                   1     2     1
     *                1     3     3     1
     *             1     4     6     4     1
     *          1     5    10    10     5     1
     *       1     6    15    20    15     6     1
     *    1     7    21    35    35    21     7     1
     * 1     8    28    56    70    56    28     8     1
     * 
     * @param val numero iteraciones
     */
    public List<String> generarTriangulo(int val){
        List<String> lineas = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        val = val + 1;
        int[] a = new int[1];
        for (int i = 1; i <= val; i++) {
            // limpiamos el StringBuilder
            sb.setLength(0);
            int[] x = new int[i];
            // tenemos que llenar de espacios la linea segun el numero de iteraciones
            // hay que tener en cuenta que el espacio entre numeros es 4
            // esto es el equivalente a la funcion, pero en su version iterativa
            // inicio de bloque
            for (int j = 0; j < i; j++) {
                // condicion base
                if (j == 0 || j == (i - 1)) {
                    x[j] = 1;
                } 
                // caso iterativo / recursivo
                else {
                    x[j] = a[j] + a[j - 1];
                }
                // fin del bloque, el resto es para imprimir el resultado
                if(x[j] < 10){
                    sb.append(" ");
                }
                sb.append(x[j] + "    ");
            }
            // fin de bloque
            a = x;
            lineas.add(sb.toString());
        }
        return lineas;
    }

    public void imprimirTriangulo(List<String> lineas){
        int len = lineas.get(lineas.size() - 1).length();
        for (int i = 0; i < lineas.size(); i++) {
            // tenemos que imprimir la linea con el numero de espacios
            // para que quede alineado
            int espacios = (len - lineas.get(i).length()) / 2;
            for (int j = 0; j < espacios; j++) {
                System.out.print(" ");
            }
            // luego imprimimos la linea
            System.out.println(lineas.get(i));
        }

    }


    public void run() {
        List<String> lineas = generarTriangulo(8);
        imprimirTriangulo(lineas);
    }
}
