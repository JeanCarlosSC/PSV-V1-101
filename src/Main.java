import javax.swing.*;

public class Main {

    public static double determinante=1;

    public static void main(String[] args) {
        new Window();
    }

    public static int calcularDeterminante(double[][] matriz) {
        double temp;
        determinante = 1;
        int c=0, i, i1, j;

        c+=3;
        for(i=0; i<matriz.length - 1; i++) {
            c+=6;
            //hace cero los valores por debajo del pivote
            for (i1=i + 1; i1 < matriz.length; i1++) {
                c+=10;
                temp = matriz[i1][i] / matriz[i][i];//toma el valor que al sumar cumple la condición

                for(j=i; j<matriz.length; j++) {
                    c+=11;
                    matriz[i1][j] = matriz[i1][j] - temp * matriz[i][j]; //sumo a toda la fila
                }
            }
        }

        //halla determinante de matriz triangular superior
        for(i=0; i<matriz.length; i++) {
            if(matriz[i][i]==0.0)
                JOptionPane.showMessageDialog(null, "no se puede calcular el determinante por gauss", "error",
                    JOptionPane.ERROR_MESSAGE);
            determinante = determinante * matriz[i][i];
        }

        return c;
    }

    public static int calcularMatrizEscalonada(double[][] matriz) {
        int c = 0; //contador de operaciones elementales
        int n = matriz.length; //cantidad de filas
        int m = matriz[0].length; // cantidad de columnas

        //itera por cada fila de la matriz
        for(int i=0; i<n; i++) {

            //itera por cada columna de la matriz
            for(int j=i; j<m; j++) {

                //En caso de que se necesite intercambiar dos filas
                for(int i1=i; i1<n; i1++) {
                    if (matriz[i1][j] != 0.0) {
                        double[] temp = matriz[i];
                        matriz[i] = matriz[i1];
                        matriz[i1] = temp;
                        break;
                    }
                }

                //si el pivote es cero después de organizar las filas, busca en la siguiente columna
                if(matriz[i][j] == 0.0)
                    continue;
                //sino, procede a hacer el pivote 1 y los valores inferiores del pivote a 0
                else {
                    //aquí hace al pivote 1
                    double temp = matriz[i][j];
                    for(int j1=j; j1<m; j1++) {
                        matriz[i][j1] = matriz[i][j1] / temp;
                    }

                    //aquí hace a los valores debajo del pivote 0
                    if(i < n-1) {
                        for (int i1 = i+1; i1<n; i1++) {
                            temp = matriz[i1][j];

                            for(int j1=j; j1<m; j1++) {
                                matriz[i1][j1] = matriz[i1][j1] - matriz[i][j1] * temp;
                            }
                        }
                    }

                    //continua el proceso con la siguiente fila
                    break;
                }

            }

        }

        return c;
    }

}
