/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

/**
 *
 * @author xochi
 */
public class VoltearMatriz {

    

    public short [][] gira90Grados(short[][] matriz){
        short[][] matrizNueva = new short[matriz.length][matriz[0].length];
        for (int i = 0; i < matriz[0].length; i++) {
            for (int j = 0; j < matriz.length; j++) {
               matrizNueva[i][j] = matriz[matriz.length-j-1][i];
            }
        }
        return matrizNueva;
    }
    
    public short [][] gira180Grados(short[][] matriz){
        short[][] matrizNueva = new short[matriz.length][matriz[0].length];
        for (int i = 0; i < matriz[0].length; i++) {
            for (int j = 0; j < matriz.length; j++) {
               matrizNueva[j][matriz.length-1-i] = matriz[matriz.length-j-1][i];
            }
        }
        return matrizNueva;
    }
     
     public short [][] gira270Grados(short[][] matriz){
        short[][] matrizNueva = new short[matriz.length][matriz[0].length];
         for (int i = 0; i < matriz.length; i++) {
             for (int j = 0; j < matriz[i].length; j++) {
                 matrizNueva[i][j] = matriz[j][i];
             }
         }
        return matrizNueva;
    }
    
    
    

}
