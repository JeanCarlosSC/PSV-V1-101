import javax.swing.*;

public class Main {

    public static double determinante=1;

    public static void main(String[] args) {
        new Window();
    }

    public static int calcularDeterminante(double[][] matriz) {
        int c = 0; //contador de operaciones elementales
        int n = matriz.length; //cantidad de filas
        determinante = 1;

        //itera por cada fila de la matriz
        c+=3;
        for(int i=0; i < n-1; i++) {
            c+=3;

            //itera por cada columna de la matriz
            c+=2;
            for(int j=i; j<n; j++) {
                c+=2;

                //En caso de que se necesite intercambiar dos filas
                for(int i1=i; i1<n; i1++) {
                    if (matriz[i1][j] != 0.0 && matriz[i][j]==0) {
                        double[] temp = matriz[i];
                        matriz[i] = matriz[i1];
                        matriz[i1] = temp;
                        determinante *= -1;
                        break;
                    }
                }

                //si el pivote es cero después de organizar las filas, busca en la siguiente columna
                //sino, procede a hacer los valores inferiores del pivote a 0
                if(matriz[i][j] != 0.0) {//este if no se cuenta porque tiene que ver con la forma de organizar las filas

                    //aquí hace a los valores debajo del pivote 0
                    c+=3;
                    for (int i1 = i+1; i1<n; i1++) {
                        c+=5;
                        double temp = matriz[i1][j] / matriz[i][j];

                        c+=2;
                        for(int j1=j; j1<n; j1++) {
                            c+=11;
                            matriz[i1][j1] = matriz[i1][j1] - matriz[i][j1] * temp;
                        }
                    }

                    //continua el proceso con la siguiente fila
                    break;
                }

            }
        }

        //halla determinante de matriz triangular superior
        for(int i=0; i<n; i++) {
            determinante = determinante * matriz[i][i];
            if(matriz[i][i]==0.0) {
                JOptionPane.showMessageDialog(null, "no se puede calcular el determinante por gauss", "error",
                        JOptionPane.ERROR_MESSAGE);
                break;
            }
        }

        return c;
    }

    public static int calcularMatrizEscalonada(double[][] matriz) {
        int c = 0; //contador de operaciones elementales
        int n = matriz.length; //cantidad de filas
        int m = matriz[0].length; // cantidad de columnas

        //itera por cada fila de la matriz
        c+=2;
        for(int i=0; i<n; i++) {
            c+=2;

            //itera por cada columna de la matriz
            c+=2;

            //para hacer c constante dado diversos tamaños de matrices, contamos las OE y les quitamos las variables independientes al tamaño
            c += 28*m;

            for(int j=i; j<m; j++) {//2

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
                //sino, procede a hacer el pivote 1 y los valores inferiores del pivote a 0

                if(matriz[i][j] != 0.0) { //3 depende de la cantidad de pivotes es decir, es independiente del tamaño

                    //aquí hace al pivote 1
                    double temp = matriz[i][j];//3
                    for(int j1=j; j1<m; j1++) {//valor independiente del tamaño
                        matriz[i][j1] = matriz[i][j1] / temp;//6
                    }

                    //aquí hace a los valores debajo del pivote 0
                    if(i < n-1) {//3
                        for (int i1 = i+1; i1<n; i1++) {//n-i-1
                            temp = matriz[i1][j];//3

                            for(int j1=j; j1<m; j1++) {//11(m-j) valor independiente del tamaño
                                matriz[i1][j1] = matriz[i1][j1] - matriz[i][j1] * temp;//11
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
