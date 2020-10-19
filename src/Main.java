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
        int i=0, j=0, c=0, limI=0, i1, j1;

        while(true){//5*4 + 3 + 7*3 + 9*9  + 3*3 + 3*11 + 11*12 + 3*4 + 6  (varía dependiendo de la matriz) 3*4
                    //5*5 + 4 + 7*4 + 10*9 + 4*3 + 6*11 + 20*12 + 4*4 + 6                                   4*4
            //5*(n+1) + n + 7*n +  (n + 6)*9 + n*3 + (3*n-6)*11 + (9*n - m*m)*12 + n*4 + 6
            //5n + 5 + n + 7n + 9n + 54 + 3n + 33n - 66 + 108n - 12m^2 + nm + 6
            //-12m^2 + nm + 166n - 1

            if(i<matriz.length && j == matriz[0].length-1 && matriz[i][j] != 0.0){
                c+=83;
                matriz[i][j] = 1;
                for(int i2=i+1; i2<matriz.length; i2++)
                    matriz[i2][j] = 0;
                break;
            }
            c+=7;
            if(j<matriz[0].length && i<matriz.length && matriz[i][j] != 0) {
                /*
                if(i != j){//por si hace falta hacer un cambio de filas
                    double[] temp = matriz[i];
                    matriz[i] = matriz[j];
                    matriz[j] = temp;
                }*/
                c+=4 + 3;
                double temp=1/matriz[i][j];
                for(int k=j; k<matriz[0].length; k++) {
                    c+=9;
                    matriz[i][k] = matriz[i][k] * temp;
                }
                //hace cero los valores por debajo del pivote
                c+=3;
                for (i1=i + 1; i1 < matriz.length; i1++) {
                    c+=11;
                    temp = matriz[i1][j] / matriz[i][j];//toma el valor que al sumar cumple la condición

                    for(j1=j; j1<matriz[0].length; j1++) {
                        c+=12;
                        matriz[i1][j1] = matriz[i1][j1] - temp * matriz[i][j1]; //sumo a toda la fila
                    }
                }
                c+=4;
                limI=j+1;
                i = limI;
                j = limI;
                continue;
            }

            //contador: aquí entra si la matriz esta no es cuadrada o no esta full de pivotes o en la ultima comparacion
            c+=3;
            //si no se llega a encontrar pivote en alguna columna o si es la última iteración
            if(i >= matriz.length - 1){
               c+=3;
                if(j < matriz[0].length - 1){
                    j++;
                    c++;
                }else break;
                c++;
                i = limI;
            }else {
                c++;
                i++;
            }
        }
        c++;

        return c;
    }

}
