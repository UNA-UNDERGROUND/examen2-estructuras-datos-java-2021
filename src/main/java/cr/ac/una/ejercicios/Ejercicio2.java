package cr.ac.una.ejercicios;

public class Ejercicio2 {

    /**
     * genera las filas del triangulo de pascal
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
    public void generarTriangulo(int val){
        int[] linea = new int[val];
        for(int i = 0; i < val; i++){
            linea[i] = 1;
            for(int j = 0; j < i; j++){
                linea[j] = linea[j] + linea[j+1];
            }
            for(int j = 0; j < linea.length; j++){
                System.out.print(linea[j] + " ");
            }
            System.out.println();
        }
    }
    
    public void run() {
        generarTriangulo(8);
    }
}
