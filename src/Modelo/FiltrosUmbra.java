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
public class FiltrosUmbra {

    public FiltrosUmbra() {

    }

    public short[][] calculaInverso(short[][] matrizGris) {
        short[][] matrizNueva = new short[matrizGris.length][matrizGris[0].length];
        for (int i = 0; i < matrizGris.length; i++) {
            for (int j = 0; j < matrizGris[i].length; j++) {
                matrizNueva[i][j] = (short) (255 - matrizGris[i][j]);

            }
        }
        return matrizNueva;
    }

    public short[][] operadorUmbralInverso(short[][] matriz, int u) {
        short[][] matrizNueva = new short[matriz.length][matriz[0].length];
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                if (matriz[i][j] < u) {
                    matrizNueva[i][j] = 255;
                } else {
                    matrizNueva[i][j] = 0;
                }
            }
        }
        return matrizNueva;
    }

    public short[][] operadorUmbral(short[][] matriz, int u) {
        short[][] matrizNueva = new short[matriz.length][matriz[0].length];
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                if (matriz[i][j] < u) {
                    matrizNueva[i][j] = 0;
                } else {
                    matrizNueva[i][j] = 255;
                }
            }
        }
        return matrizNueva;
    }

    public short[][] operadorUmbralBinario(short[][] matriz, int u1, int u2) {
        short[][] matrizNueva = new short[matriz.length][matriz[0].length];
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                if (matriz[i][j] < u1 || matriz[i][j] > u2) {
                    matrizNueva[i][j] = 0;
                } else {
                    matrizNueva[i][j] = 255;
                }
            }
        }
        return matrizNueva;
    }

    public short[][] operadorUmbralBinarioInverso(short[][] matriz, int u1, int u2) {
        short[][] matrizNueva = new short[matriz.length][matriz[0].length];
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                if (matriz[i][j] < u1 || matriz[i][j] > u2) {
                    matrizNueva[i][j] = 255;
                } else {
                    matrizNueva[i][j] = 0;
                }
            }
        }
        return matrizNueva;
    }

    public short[][] operadorUmbralGris(short[][] matriz, int u1, int u2) {
        short[][] matrizNueva = new short[matriz.length][matriz[0].length];
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                if (matriz[i][j] < u1 || matriz[i][j] > u2) {
                    matrizNueva[i][j] = 255;
                } else {
                    matrizNueva[i][j] = matriz[i][j];
                }
            }
        }
        return matrizNueva;
    }

    public short[][] operadorUmbralGrisInverso(short[][] matriz, int u1, int u2) {
        short[][] matrizNueva = new short[matriz.length][matriz[0].length];
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
               
                if (matriz[i][j] < u1 || matriz[i][j] > u2) {
                    matrizNueva[i][j] = matriz[i][j];
                }
                 else {
                    matrizNueva[i][j] = 255;
                }
            }
        }
        return matrizNueva;
    }

    public short[][] operadorExtension(short[][] matriz, int u1, int u2) {
        short[][] matrizNueva = new short[matriz.length][matriz[0].length];
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                if (matriz[i][j] < u1 || matriz[i][j] > u2) {
                    matrizNueva[i][j] = 255;
                }
                else {
                    matrizNueva[i][j] = (short) ((255 * (matriz[i][j] - u1)) / (u2 - u1));
                    //System.out.println("Entra");
                }
            }
        }
        return matrizNueva;
    }

    public short[][] reduccionNivelesDeGris(short[][] matriz) {
        short[][] matrizNueva = new short[matriz.length][matriz[0].length];
        short[] umbral = new short[10];
        int u = 25;
        //int valor_u = 0;
        for (int k = 0; k < umbral.length; k++) {
            umbral[k] = (short) u;
            u += 25;
            //System.out.println("[" + umbral[k] + "]" + "[" + k + "]");
        }

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                // int pixelGris = matriz[i][j];
                if (matriz[i][j] < umbral[0]) {
                    matrizNueva[i][j] = 0;
                }
                if (matriz[i][j] > umbral[0] && matriz[i][j] < umbral[1]) {
                    matrizNueva[i][j] = 37;
                }
                if (matriz[i][j] > umbral[1] && matriz[i][j] < umbral[2]) {
                    matrizNueva[i][j] = 62;
                }
                if (matriz[i][j] > umbral[2] && matriz[i][j] < umbral[3]) {
                    matrizNueva[i][j] = 87;
                }
                if (matriz[i][j] > umbral[3] && matriz[i][j] < umbral[4]) {
                    matrizNueva[i][j] = 112;
                }
                if (matriz[i][j] > umbral[4] && matriz[i][j] < umbral[5]) {
                    matrizNueva[i][j] = 137;
                }
                if (matriz[i][j] > umbral[5] && matriz[i][j] < umbral[6]) {
                    matrizNueva[i][j] = 162;
                }
                if (matriz[i][j] > umbral[6] && matriz[i][j] < umbral[7]) {
                    matrizNueva[i][j] = 187;
                }
                if (matriz[i][j] > umbral[7] && matriz[i][j] < umbral[8]) {
                    matrizNueva[i][j] = 212;
                } else {
                    matrizNueva[i][j] = 255;
                }
            }
        }
        return matrizNueva;
    }

}
