/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.Arrays;

/**
 *
 * @author xochi
 */
public class FiltrosRestauracion {

    short[] arreglo = new short[8];
    int tamAct;
    int n = 3;

    public short[][] mediaAritmetica(short[][] matriz) {
        //short[] arreglo = new short[8];
        short promedio2 = 0;
        short[][] matrizNueva = new short[matriz.length][matriz[0].length];
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                matrizNueva[i][j] = matriz[i][j];
            }
        }

        for (int i = 1; i < matriz.length - 1; i++) {
            for (int j = 1; j < matriz[i].length - 1; j++) {

                for (int k = 0; k < vecindad(i, j, matriz).length; k++) {
                    promedio2 += vecindad(i, j, matriz)[k];
                }
                promedio2 = (short) (promedio2 / 8);
                matrizNueva[i][j] = promedio2;
                promedio2 = 0;

            }
        }
        return matrizNueva;
    }

    public short[][] mediaGeometrica(short[][] matriz) {

        long media = 1;
        short[][] matrizNueva = new short[matriz.length][matriz[0].length];
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                matrizNueva[i][j] = matriz[i][j];
            }
        }

        for (int i = 1; i < matriz.length - 1; i++) {
            for (int j = 1; j < matriz[i].length - 1; j++) {
                for (int k = 0; k < vecindad(i, j, matriz).length; k++) {
                    media *= (vecindad(i, j, matriz)[k]);

                    //System.out.println(media);
                }

                //System.out.println("--------------");
                media = (long) (Math.pow((double) media, 0.125));
                //System.out.println(media);
                matrizNueva[i][j] = (short) media;
                media = 1;
            }
        }
        return matrizNueva;
    }

    public short[][] mediana(short[][] matriz) {

        int promedio = 0;
        short[] arreglo;
        short[][] matrizNueva = new short[matriz.length][matriz[0].length];
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                matrizNueva[i][j] = matriz[i][j];
            }
        }

        for (int i = 1; i < matriz.length - 1; i++) {
            for (int j = 1; j < matriz[i].length - 1; j++) {
                arreglo = vecindad(i, j, matriz);
                Arrays.sort(arreglo);
                promedio = (arreglo[3] + arreglo[4]) / 2;
                matrizNueva[i][j] = (short) promedio;
                promedio = 0;
            }
        }
        return matrizNueva;
    }

    public short[][] mayor(short[][] matriz) {

        short[] arreglo;
        short[][] matrizNueva = new short[matriz.length][matriz[0].length];
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                matrizNueva[i][j] = matriz[i][j];
            }
        }
        for (int i = 1; i < matriz.length - 1; i++) {
            for (int j = 1; j < matriz[i].length - 1; j++) {
                arreglo = vecindad(i, j, matriz);
                Arrays.sort(arreglo);
                matrizNueva[i][j] = arreglo[7];
            }
        }
        return matrizNueva;
    }

    public short[][] menor(short[][] matriz) {

        short[] arreglo;
        short[][] matrizNueva = new short[matriz.length][matriz[0].length];
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                matrizNueva[i][j] = matriz[i][j];
            }
        }

        for (int i = 1; i < matriz.length - 1; i++) {
            for (int j = 1; j < matriz[i].length - 1; j++) {
                arreglo = vecindad(i, j, matriz);
                Arrays.sort(arreglo);
                matrizNueva[i][j] = arreglo[0];
            }
        }
        return matrizNueva;
    }

    public short[][] puntoMedio(short[][] matriz) {
        short[] arreglo;
        short[][] matrizNueva = new short[matriz.length][matriz[0].length];
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                matrizNueva[i][j] = matriz[i][j];
            }
        }

        for (int i = 1; i < matriz.length - 1; i++) {
            for (int j = 1; j < matriz[i].length - 1; j++) {
                arreglo = vecindad(i, j, matriz);
                Arrays.sort(arreglo);
                matrizNueva[i][j] = (short) ((arreglo[0] + arreglo[7]) / 2);
            }
        }
        return matrizNueva;
    }

    public short[][] mediaSeccion(short[][] matriz) {
        short[] arreglo;
        short[][] matrizNueva = new short[matriz.length][matriz[0].length];
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                matrizNueva[i][j] = matriz[i][j];
            }
        }

        for (int i = 1; i < matriz.length - 1; i++) {
            for (int j = 1; j < matriz[i].length - 1; j++) {
                arreglo = vecindad(i, j, matriz);
                Arrays.sort(arreglo);
                matrizNueva[i][j] = (short) ((arreglo[2] + arreglo[3] + arreglo[4] + arreglo[5]) / 4);
            }
        }
        return matrizNueva;
    }

    /*public short[][] mediaAdaptivo(short[][] matriz) {

        int vMax = 7, zMin = 0, zMax = 0, zMed = 0;
        int zXy = 0;
        int n = 3;
        int tamañoAct = 0;
        short[] arreglo;

        short[][] matrizNueva = matriz;
        

        for (int i = 1; i < matriz.length - n; i++) {
            for (int j = 1; j < matriz[i].length - n; j++) {
                tamañoAct = n;
                arreglo = seccion(matriz, n, i, j);
                
                
                Arrays.sort(arreglo);
               
                
                zMin = arreglo[0];
                zMax = arreglo[arreglo.length - 1];
                zMed = arreglo[(short) (Math.ceil(arreglo.length / 2))];
                zXy = matriz[i][j];
                if (zMin < zMed && zMed < zMax) {
                    matrizNueva[i][j] = levelB(zMin, zMax, zMed, zXy);
                }else {
                    
                    n = tamañoVentana(n);
                    tamañoAct = n;
                }
                if (tamañoAct < vMax) {
                   matrizNueva =  mediaAdaptivo(matriz);
                }else{
                    matrizNueva[i][j] = (short)zMed;
                }

            }
        }
        return matrizNueva;
    }*/
    public short[][] mediaAdaptivo(short[][] matriz) {

        int vMax = 7, zMin = 0, zMax = 0, zMed = 0;
        int zXy = 0;

        int tamañoAct = 0;
        short[] arreglo;

        //short[][] matrizNueva = matriz;
        short[][] matrizNueva = new short[matriz.length][matriz[0].length];
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                matrizNueva[i][j] = matriz[i][j];
            }
        }

        for (int i = 7/2; i < matriz.length - 7; i++) {
            for (int j = 7/2; j < matriz[i].length - 7; j++) {
                tamañoAct = n;
                arreglo = seccion(matriz, n, i, j);

                Arrays.sort(arreglo);
                /* for (int k = 0; k < arreglo.length; k++) {
                    System.out.print("[" + arreglo[k] + "]");
                }*/
                //System.out.println("tamAct:" + tamañoAct);
                zMin = arreglo[0];
                zMax = arreglo[arreglo.length - 1];
                zMed = arreglo[(short) (Math.ceil(arreglo.length / 2))];
                zXy = matriz[i][j];
                matrizNueva[i][j] = levelA(zMin, zMax, zMed, zXy, tamañoAct, vMax);
                //System.out.println(matrizNueva[i][j]);

            }
        }
        return matrizNueva;

    }

    public short levelA(int zMin, int zMax, int zMed, int zXy, int tamañoAct, int vMax) {
        short pixel = 0;
         if (zMin < zMed && zMed < zMax) {
            return levelB(zMin, zMax, zMed, zXy);
        } else {
            this.n +=  2;
            //setTam(tamañoVentana(tamañoAct));
            //System.out.println("Tamaño:" + this.n);
        }
        if (n < vMax) {
            //System.out.println("---------------------------------------------------------------------");
            pixel = levelA(zMin, zMax, zMed, zXy, n, vMax);
            return pixel;
        } else {
            return (short) zMed;
        }
 /*int b1 = zXy - zMin;
        int b2 = zXy - zMax;
        if (b1 > 0 && b2 < 0) {
            return (short)zXy;
        }else{
            int a1 = zMed - zMin;
            int a2 = zMed - zMax;
            if (a1 > 0 && a2 < 0) {
                return (short)zMed;
            }else{
                if (n < vMax) {
                    //setTam(tamañoVentana(tamañoAct));
                    //System.out.println("Tamaño" + this.n);
                    n = n+2;
                    System.out.println("Tamaño:" + n);
                     pixel = levelA(zMin, zMax, zMed, zXy, n, vMax);
                     return pixel;
                }else{
                    return (short)zMed;
                }
            }
        }*/
       /* if (zMed > zMin && zMed < zMax) {
            if (zXy > zMin && zXy < zMax) {
                return (short)zXy;
            } else {
                return (short)zMed;
            }
        } else {
            this.n += 2;
            if (n <= vMax) {
                 pixel = levelA(zMin, zMax, zMed, zXy, n, vMax);
                 return pixel;
            } else {
                return (short)zMed;
            }
        }*/

        //return 0;

    }

    public void setTam(int tamAct) {
        this.n = tamAct;
    }

    public int tamañoVentana(int tamAct) {
        int nuevoTam = 3;
        if (tamAct == 3) {
            nuevoTam = 5;
            //System.out.println("--------------------------------------------------------------");
        }
        if (tamAct == 5) {
            nuevoTam = 7;
        }
        if (tamAct == 7) {
            nuevoTam = 9;
        }
        if (tamAct == 9) {
            nuevoTam = 11;
        }
        if (tamAct == 11) {
            nuevoTam = 13;
        }/*if (tamAct == 13) {
            nuevoTam = 15;
        }if (tamAct == 15) {
            nuevoTam = 17;
        }if (tamAct == 17) {
            nuevoTam = 19;
        }if (tamAct == 19) {
            nuevoTam = 21;
        }*/
        //System.out.println("NTam" + nuevoTam);
        return nuevoTam;
    }

    public short levelB(int zMin, int zMax, int zMed, int zXy) {
        if (zMin < zXy && zXy < zMax) {
            return (short) zXy;
        } else {
            return (short) zMed;
        }

    }

    public short[] seccion(short[][] matriz, int n, int i, int j) {
        short[][] seccionM = new short[n][n];
        short[] arreglo = new short[n * n];
        int cont = 0;
        for (int k = 0; k < seccionM.length; k++) {
            for (int l = 0; l < seccionM[k].length; l++) {
                seccionM[k][l] = matriz[i - 1 + k][j - 1 + l];
                // System.out.print("[" + seccionM[k][l] + "]");
                arreglo[cont] = seccionM[k][l];
                cont++;
            }
            //System.out.println(" ");
        }
        /*System.out.println("----------------------");
        for (int k = 0; k < arreglo.length; k++) {
            System.out.print( "[" + arreglo[k] + "]");
        }
        System.out.println("---------------------------------------");*/
        return arreglo;
    }

    public short[] vecindad(int i, int j, short[][] matriz) {
        short[] arreglo = new short[8];
        arreglo[0] = matriz[i - 1][j - 1];
        arreglo[1] = matriz[i - 1][j];
        arreglo[2] = matriz[i - 1][j + 1];
        arreglo[3] = matriz[i][j - 1];
        arreglo[4] = matriz[i][j + 1];
        arreglo[5] = matriz[i + 1][j - 1];
        arreglo[6] = matriz[i + 1][j];
        arreglo[7] = matriz[i + 1][j + 1];
        return arreglo;
    }

    public short vecindad4() {
        //short [][] matriz
        //short[][] matrizNueva = new short[matriz.length][matriz[0].length];
        //short [][] matriz = {{1, 2, 3}, {4, 7, 6}, {7, 8, 9}};
        int valor = 10;
        short[][] matriz2 = new short[9][9];
        short[][] matrizCopia = new short[matriz2.length][matriz2[0].length];
        for (int i = 0; i < matriz2.length; i++) {
            for (int j = 0; j < matriz2[i].length; j++) {
                matriz2[i][j] = (short) valor;
                matrizCopia[i][j] = (short) valor;
                System.out.print("[" + matriz2[i][j] + "]");
                valor++;
            }
            System.out.println(" ");
        }

        short promedio = 0;
        for (int i = 1; i < matriz2.length - 1; i++) {
            for (int j = 1; j < matriz2[i].length - 1; j++) {
                arreglo[0] = matriz2[i - 1][j - 1];
                arreglo[1] = matriz2[i - 1][j];
                arreglo[2] = matriz2[i - 1][j + 1];
                arreglo[3] = matriz2[i][j - 1];
                arreglo[4] = matriz2[i][j + 1];
                arreglo[5] = matriz2[i + 1][j - 1];
                arreglo[6] = matriz2[i + 1][j];
                arreglo[7] = matriz2[i + 1][j + 1];
                System.out.println("Esta en:" + matriz2[i][j]);
                for (int k = 0; k < arreglo.length; k++) {
                    promedio += (int) arreglo[k];

                    System.out.println("Hola: " + arreglo[k] + "[" + k + "]");
                    //System.out.println("Promedio " + promedio);
                }
                System.out.println("--------------------------------------");
                promedio = (short) (promedio / 8);
                System.out.println("Prom: " + promedio);
                matrizCopia[i][j] = promedio;
            }
        }

        for (int i = 0; i < matrizCopia.length; i++) {
            for (int j = 0; j < matrizCopia[i].length; j++) {
                System.out.print("[" + matrizCopia[i][j] + "]");
            }
            System.out.println(" ");
        }
        return 0;
        //return promedio;
    }

    /* public short[][] vecindad8(short[][] matriz) {
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
}
