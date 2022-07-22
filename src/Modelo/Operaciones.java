/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author xochi
 */
public class Operaciones {

    public short[][] Suma(short[][] matriz1, short[][] matriz2) {
        short[][] matrizNueva = new short[matriz1.length][matriz1[0].length];
        short k = 2;
        if (matriz1.length == matriz2.length && matriz1[0].length == matriz2[0].length) {
            // System.out.println("Entro al iffff");
            for (int i = 0; i < matriz1.length; i++) {
                for (int j = 0; j < matriz1[i].length; j++) {
                    matrizNueva[i][j] = (short) ((matriz1[i][j] + matriz2[i][j]) / k);
                }
            }
        }
        return matrizNueva;
    }

    public short[][] Resta(short[][] matriz1, short[][] matriz2) {
        short[][] matrizNueva = new short[matriz1.length][matriz1[0].length];
        if (matriz1.length == matriz2.length && matriz1[0].length == matriz2[0].length) {
            // System.out.println("Entro al iffff");
            for (int i = 0; i < matriz1.length; i++) {
                for (int j = 0; j < matriz1[i].length; j++) {
                    matrizNueva[i][j] = (short) Math.abs((matriz1[i][j] - matriz2[i][j]));
                }
            }
        }
        return matrizNueva;
    }

    public short[][] operadorAnd(short[][] matriz, short[][] matriz2) {
        short[][] matrizNueva = new short[matriz.length][matriz[0].length];
        boolean bandera1, bandera2;
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                bandera1 = (matriz[i][j] == 255) ? true : false;
                bandera2 = (matriz2[i][j] == 255) ? true : false;
                if (bandera1 == true && bandera2 == true) {
                    matrizNueva[i][j] = 255;
                    // System.out.println("Si entra al ifx151465152");
                } else {
                    matrizNueva[i][j] = 0;
                    //System.out.println("Puro pito papa");
                }
            }
        }
        return matrizNueva;
    }

    public short[][] opeadorOr(short[][] matriz, short[][] matriz2) {
        short[][] matrizNueva = new short[matriz.length][matriz[0].length];
        boolean bandera1, bandera2;
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                bandera1 = (matriz[i][j] == 255) ? true : false;
                bandera2 = (matriz2[i][j] == 255) ? true : false;
                if (bandera1 == false && bandera2 == false) {
                    matrizNueva[i][j] = 0;
                } else {
                    matrizNueva[i][j] = 255;
                }
            }
        }
        return matrizNueva;
    }

    public short[][] opeadorXor(short[][] matriz, short[][] matriz2) {
        short[][] matrizNueva = new short[matriz.length][matriz[0].length];
        boolean bandera1, bandera2;
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                bandera1 = (matriz[i][j] == 255) ? true : false;
                bandera2 = (matriz2[i][j] == 255) ? true : false;
                if ((bandera1 == true && bandera2 == true) || (bandera1 == false && bandera2 == false)) {
                    matrizNueva[i][j] = 0;
                } else {
                    matrizNueva[i][j] = 255;
                }
            }
        }
        return matrizNueva;
    }

    public short[][] opeadorNot(short[][] matriz, short[][] matriz2) {
        short[][] matrizNueva = new short[matriz.length][matriz[0].length];
        boolean bandera1, bandera2;
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                bandera1 = (matriz[i][j] == 255) ? true : false;
                bandera2 = (matriz2[i][j] == 255) ? true : false;
                if (bandera1 = !bandera2) {
                    matrizNueva[i][j] = 0;
                } else {
                    matrizNueva[i][j] = 255;
                }
            }
        }
        return matrizNueva;
    }

    public short[][] not(short[][] matriz) {
        short[][] matrizNueva = new short[matriz.length][matriz[0].length];
        boolean bandera1;
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                bandera1 = (matriz[i][j] == 255) ? true : false;
                if (bandera1 == true) {
                    matrizNueva[i][j] = 0;
                } else {
                    matrizNueva[i][j] = 255;
                }
            }
        }
        return matrizNueva;
    }

    // f+ 1
    /*public short[][] vecindad4(short[][] matriz) {
        short[][] matrizNueva = new short[matriz.length][matriz[0].length];
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                if (i - 1 > 0 && j - 1 > 0) {
                    if (j + 1 < matriz[0].length && i + 1 < matriz.length) {
                        matrizNueva[i - 1][j] = matriz[i - 1][j];
                        matrizNueva[i][j - 1] = matriz[i][j - 1];
                        matrizNueva[i + 1][j] = matriz[i + 1][j];
                        matrizNueva[i][j + 1] = matriz[i][j + 1];
                        //matrizNueva[i][j] = 0;
                    }
                }
            }
        }
        return matrizNueva;
    }

    public short[][] vecindad8(short[][] matriz) {
        short[][] matrizNueva = vecindad4(matriz);
        int j = 0, i = 0;
        short[][] matrizNueva2 = new short[matrizNueva.length][matrizNueva[0].length];
        for (i = 0; i < matrizNueva.length; i++) {
            for (j += 1; j < matrizNueva[i].length; j++) {

                if (i - 1 > 0 && j - 1 > 0) {
                    if (j + 1 < matrizNueva[0].length && i + 1 < matrizNueva.length) {
                        matrizNueva2[i - 1][j - 1] = matrizNueva[i - 1][j - 1];
                        matrizNueva2[i - 1][j + 1] = matrizNueva[i - 1][j + 1];
                        matrizNueva2[i + 1][j + 1] = matrizNueva[i + 1][j + 1];
                        matrizNueva2[i + 1][j - 1] = matrizNueva[i + 1][j - 1];
                    }
                }

            }
        }
        return matrizNueva2;
    }*/
    public short[][] promedioDelPunto(short[][] matriz) { //Zoom - Interpolacion 
        int numero = matriz.length * 2;
        int newWidth = numero, newHeight = numero;
        short[][] matrizNueva = new short[numero][numero];
        int width = matriz.length;
        int height = matriz[0].length;
        double jFactor = (double) width / (double) newWidth;
        double iFactor = (double) height / (double) newHeight;

        for (int i = 0; i < newHeight; i++) {

            int I = (int) (i * iFactor);
            int p;

            for (int j = 0; j < newWidth; j++) {

                int J = (int) (j * jFactor);
                p = matriz[I][J];
                matrizNueva[i][j] = (short) p;

            }
        }

        return matrizNueva;
    }

   /* public short[][] promedioDelPuntoEjemplo() {
        int[][] matriz = {{10, 4, 22}, {2, 18, 7}, {9, 14, 25}};
        int newWidth = 6, newHeight = 6;
        short[][] matrizNueva = new short[6][6];
        int width = matriz.length;
        int height = matriz[0].length;

        double jFactor = (double) width / (double) newWidth;
        double iFactor = (double) height / (double) newHeight;

        for (int i = 0; i < newHeight; i++) {

            int I = (int) (i * iFactor);
            int p;

            for (int j = 0; j < newWidth; j++) {

                int J = (int) (j * jFactor);
                p = matriz[I][J];
                matrizNueva[i][j] = (short) p;
            }
        }

        return matrizNueva;
    }*/

   /* public short[][] convolucion(short[][] matriz, short[][] mascara) {
        short[][] matrizNueva = new short[matriz.length][matriz[0].length];
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {

            }
        }
        return matrizNueva;
    }*/
    
    

    static short convolucionar(short[][] imagen, short[][] kernel, int fila, int columna) {

        int tope = kernel.length / 2; //variable que sirve de control para evitar que se desborde la mascara de la matriz
        short pixel = 0;
        short factor = 0;

        for (int i = 0; i < kernel.length; i++) {
            for (int j = 0; j < kernel[0].length; j++) {
                factor += kernel[i][j];
                pixel += (short) (kernel[i][j] * imagen[fila - tope + i][columna - tope + j]);
            }
        }

        if (factor > 0) {
            pixel /= factor;
        }

        return pixel;
    }
    
    
    
     public short [][] Convolucion(short [][] matrizGris, short [][] kernel){
       
        short[][] matrizNueva = new short[matrizGris.length][matrizGris[0].length];
        int tope = kernel.length/2;
        for(int i=tope; i < matrizGris.length-tope; i++){
            for(int j=tope; j< matrizGris[0].length-tope; j++){
               matrizNueva[i][j] = convolucionar(matrizGris, kernel, i, j);
               
            }
           
        }
        
        return matrizNueva;
        
    }

}
