/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.IndexColorModel;
import java.awt.image.WritableRaster;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author xochi
 */
public class Imagen implements Cloneable {

    private File archivoImagen;
    private String nombre;
    public String formato;
    private int filas;
    private int columnas;
    private int nivelIntensidad;
    private short matrizGris[][];
    private short matrizR[][];
    private short matrizG[][];
    private short matrizB[][];
    private String datos;
    private BufferedReader br;
    // private short matriz8bits[][];

    private BufferedImage bufferImagen;
    //factores de conversion a escala de grises
    private final float Alfa = 0.299f;
    private final float Beta = 0.587f;
    private final float Gama = 0.114f;

    /**
     * Metodo constructor que tiene como argumento la ruta del archivo que ayuda
     * a inicializar los atributos: archivoImagen, columnas, filas,
     * nivelIntensidad = 255, nombre y bufferImagen
     *
     * @param File rutaImagen
     * @param rutaImagen
     */
    public Imagen() {

    }

    public Imagen clone() throws CloneNotSupportedException {
        return (Imagen) super.clone();
    }

    public Imagen(File rutaImagen) throws IOException {
        this.archivoImagen = rutaImagen;
        this.bufferImagen = ImageIO.read(archivoImagen);
        this.columnas = bufferImagen.getWidth();
        this.filas = bufferImagen.getHeight();
        this.nombre = archivoImagen.getName();
        this.formato = formatoI(nombre);
        // this.nivelIntensidad = 255;
        inicializaMatrices(bufferImagen);
        this.br = new BufferedReader(new FileReader(archivoImagen));
        this.datos = br.readLine();
        while (datos.charAt(0) == '#') {
            datos = br.readLine();
        }
        this.datos = br.readLine();
        // this.nivelIntensidad = Integer.parseInt(datos);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getFilas() {
        return filas;
    }

    public void setFilas(int filas) {
        this.filas = filas;
    }

    public int getColumnas() {
        return columnas;
    }

    public void setColumnas(int columnas) {
        this.columnas = columnas;
    }

    public File getArchivoImagen() {
        return archivoImagen;
    }

    public void setArchivoImagen(File archivoImagen) {
        this.archivoImagen = archivoImagen;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public int getNivelIntensidad() {
        return nivelIntensidad;
    }

    public void setNivelIntensidad(int nivelIntensidad) {
        this.nivelIntensidad = nivelIntensidad;
    }

    public short[][] getMatrizGris() {
        return matrizGris;
    }

    public void setMatrizGris(short[][] matrizGris) {
        this.matrizGris = matrizGris;
    }

    public short[][] getMatrizR() {
        return matrizR;
    }

    public void setMatrizR(short[][] matrizR) {
        this.matrizR = matrizR;
    }

    public short[][] getMatrizG() {
        return matrizG;
    }

    public void setMatrizG(short[][] matrizG) {
        this.matrizG = matrizG;
    }

    public short[][] getMatrizB() {
        return matrizB;
    }

    public void setMatrizB(short[][] matrizB) {
        this.matrizB = matrizB;
    }

    /*public short[][] getMatriz8bits() {
        return matriz8bits;
    }

    public void setMatriz8bits(short[][] matriz8bits) {
        this.matriz8bits = matriz8bits;
    }*/
    public BufferedImage getBufferImagen() {
        return bufferImagen;
    }

    public void setBufferImagen(BufferedImage bufferImagen) {
        this.bufferImagen = bufferImagen;
    }

    private void inicializaMatrices(BufferedImage bufferImagen) {
        this.matrizR = new short[filas][columnas];
        this.matrizG = new short[filas][columnas];
        this.matrizB = new short[filas][columnas];
        this.matrizGris = new short[filas][columnas];
        //this.matriz8bits = new short[filas][columnas];
        for (int i = 0; i < this.filas; i++) {
            for (int j = 0; j < this.columnas; j++) {
                Color auxColor = new Color(bufferImagen.getRGB(j, i));
                this.matrizR[i][j] = (short) auxColor.getRed();
                this.matrizG[i][j] = (short) auxColor.getGreen();
                this.matrizB[i][j] = (short) auxColor.getBlue();
                this.matrizGris[i][j] = (short) ((this.Alfa * auxColor.getRed()) + (this.Beta * auxColor.getGreen()) + (this.Gama * auxColor.getBlue()));
                //this.matriz8bits[i][j] = (short) ((auxColor.getRed() + auxColor.getGreen() + auxColor.getRed()) / 3);
                // this.matriz8bits[i][j] = (short)((auxColor.getRGB()) / 3);

            }
        }
        System.out.println("Original: " + bufferImagen.getHeight() + " " + bufferImagen.getWidth());
        System.out.println("Gris filas" + matrizGris.length + " " + matrizGris[0].length);
    }

    public void imprimirMatriz(short matriz[][]) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.print("[" + matriz[i][j] + "]");
            }
            System.out.println("");
        }
    }

    public BufferedImage convierteMatrizEnBufferedImage(short[][] matriz) {
        BufferedImage imagenGris = new BufferedImage(matriz.length, matriz[0].length, BufferedImage.TYPE_INT_RGB);
        int value = 0, j;
        for (int i = 0; i < matriz.length; i++) {
            for (j = 0; j < matriz[0].length; j++) {
                int pixelGris = matriz[i][j];
                value = pixelGris << 16 | pixelGris << 8 | pixelGris;
                imagenGris.setRGB(j, i, value);

            }

        }
        return imagenGris;
    }

    public BufferedImage convierteMatrizEnBufferedImage3(short[][] matriz) {
        BufferedImage imagenGris = new BufferedImage(matriz.length, matriz[0].length, BufferedImage.TYPE_INT_RGB);
        System.out.println(matriz[0].length);
        System.out.println(matriz.length);
        int value = 0, j;
        for (int i = 0; i < matriz[0].length; i++) {
            for (j = 0; j < matriz.length; j++) {
                int pixelGris = matriz[j][i];
                value = pixelGris << 16 | pixelGris << 8 | pixelGris;
                imagenGris.setRGB(j, i, value);

            }

        }
        return imagenGris;
    }

    // private BufferedImage imageActual;
    public File abrirImagen() {
        //Creamos la variable que será devuelta (la creamos como null
        // BufferedImage bmp = null;
        File imagenSeleccionada = null;
        //Creamos un nuevo cuadro de diálogo para seleccionar imagen
        JFileChooser selector = new JFileChooser();
        //Le damos un título
        selector.setDialogTitle("Seleccione una imagen");
        //Filtramos los tipos de archivos
        FileNameExtensionFilter filtroImagen = new FileNameExtensionFilter("JPG & GIF & BMP & PNG & TIF", "jpg", "gif", "bmp", "png", "tif");
        selector.setFileFilter(filtroImagen);
        //Abrimos el cuadro de diálog
        int flag = selector.showOpenDialog(null);
        //Comprobamos que pulse en aceptar
        if (flag == JFileChooser.APPROVE_OPTION) {
            try {
                //Devuelve el fichero seleccionado
                imagenSeleccionada = selector.getSelectedFile();

                //Asignamos a la variable bmp la imagen leida
                // bmp = ImageIO.read(imagenSeleccionada);
            } catch (Exception e) {
            }

        }
        //Asignamos la imagen cargada a la propiedad imageActual

        //imageActual=bmp;
        //Retornamos el valor
        return imagenSeleccionada;
        //return file;
    }

    public String formatoI(String nombre) {

        String[] sub = nombre.split("[.]");
        String carac = "";
        for (int i = 0; i < sub.length; i++) {
            if (i == 1) {
                carac = sub[i];
            }
        }
        return carac;
    }

    public BufferedImage convertRGBAToIndexed(BufferedImage src) {
        BufferedImage dest = new BufferedImage(src.getWidth(), src.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
        Graphics g = dest.getGraphics();
        g.setColor(new Color(231, 20, 189));

        // fill with a hideous color and make it transparent
        g.fillRect(0, 0, dest.getWidth(), dest.getHeight());
        dest = makeTransparent(dest, 0, 0);

        dest.createGraphics().drawImage(src, 0, 0, null);
        return dest;
    }

    public BufferedImage makeTransparent(BufferedImage image, int x, int y) {
        ColorModel cm = image.getColorModel();
        if (!(cm instanceof IndexColorModel)) {
            return image; // sorry...
        }
        IndexColorModel icm = (IndexColorModel) cm;
        WritableRaster raster = image.getRaster();
        int pixel = raster.getSample(x, y, 0); // pixel is offset in ICM's palette
        int size = icm.getMapSize();
        byte[] reds = new byte[size];
        byte[] greens = new byte[size];
        byte[] blues = new byte[size];
        icm.getReds(reds);
        icm.getGreens(greens);
        icm.getBlues(blues);
        IndexColorModel icm2 = new IndexColorModel(8, size, reds, greens, blues, pixel);
        return new BufferedImage(icm2, raster, image.isAlphaPremultiplied(), null);
    }

    public BufferedImage rgbACmy(short[][] matrizR, short[][] matrizG, short[][] matrizB) {
        BufferedImage imagenCMY = new BufferedImage(matrizR.length, matrizR[0].length, BufferedImage.TYPE_INT_RGB);
        short[][] matrizC = new short[this.filas][this.columnas];
        short[][] matrizM = new short[this.filas][this.columnas];
        short[][] matrizY = new short[this.filas][this.columnas];
        for (int i = 0; i < this.filas; i++) {
            for (int j = 0; j < this.columnas; j++) {
                matrizC[i][j] = (short) (255 - matrizR[i][j]);
                matrizM[i][j] = (short) (255 - matrizG[i][j]);
                matrizY[i][j] = (short) (255 - matrizB[i][j]);
                int valorCMY = (matrizC[i][j] << 16) | (matrizM[i][j] << 8) | (matrizY[i][j]);
                imagenCMY.setRGB(j, i, valorCMY);
            }
        }
        return imagenCMY;
    }

    public short[][] saturacion() {
        short[][] matrizS = new short[this.filas][this.columnas];
        float conv = 0, color_RGB = 0;
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                conv = (float) (3 * ((Math.min((double) matrizR[i][j], Math.min((double) matrizG[i][j], (double) matrizB[i][j]))) / (matrizR[i][j] + matrizG[i][j] + matrizB[i][j]))); 
                color_RGB = conv * 255;
                matrizS[i][j] = (short) (255 - color_RGB);
            }
        }
        return matrizS;
    }

    public short[][] intensidad() {
        short[][] matrizI = new short[this.filas][this.columnas];
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                matrizI[i][j] = (short) ((matrizR[i][j] + matrizG[i][j] + matrizB[i][j]) / 3);
            }
        }
        return matrizI;

    }

    public short[][] matiz() {
        short[][] matrizM = new short[this.filas][this.columnas];
        float r = 0, g = 0, b = 0;
        float suma_RGB = 0, h = 0, total = 0, division = 0, arriba = 0, abajo = 0;
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                suma_RGB = matrizR[i][j] + matrizG[i][j] + matrizB[i][j];
                if (suma_RGB == 0) {
                    r = 0;
                    g = 0;
                    b = 0;
                } else {
                    r = matrizR[i][j] / suma_RGB;
                    g = matrizG[i][j] / suma_RGB;
                    b = matrizB[i][j] / suma_RGB;
                }
                arriba = (float) (0.5 * ((r - g) + (r - b)));
                abajo = (float) Math.sqrt((((r - g) * (r - g)) + ((r - b) * (g - b))));
                if (abajo == 0) {
                    division = 0;
                } else {
                    division = (arriba / abajo);
                }
                total = (float) (Math.acos(division));  // Radianes

                h = (float) Math.toDegrees(total);

                if (b > g) {
                    h = 360 - h;
                    if ((short) h == 90) {
                        h = 0;
                    } else {
                        h = h;
                    }
                } else {
                    h = h;
                    if ((short) h == 90) {
                        h = 0;
                    } else {
                        h = h;
                    }
                }
                matrizM[i][j] = (short) h;
            }
        }
        return matrizM;
    }



    public short[][] erosionImagenBinaria(boolean erodeForegroundPixel, short[][] imagenBinaria) {
        /**
         * Dimension of the image img.
         */

        short[][] matrizErosion = new short[imagenBinaria.length][imagenBinaria[0].length];
        /**
         * This will hold the erosion result which will be copied to image img.
         */
        int output[] = new int[imagenBinaria.length * imagenBinaria[0].length];

        /**
         * If erosion is to be performed on BLACK pixels then targetValue = 0
         * else targetValue = 255; //for WHITE pixels
         */
        int targetValue = (erodeForegroundPixel == true) ? 0 : 255;

        /**
         * If the target pixel value is WHITE (255) then the reverse pixel value
         * will be BLACK (0) and vice-versa.
         */
        int reverseValue = (targetValue == 255) ? 0 : 255;

        //perform erosion
        for (int y = 0; y < imagenBinaria.length; y++) {
            for (int x = 0; x < imagenBinaria[y].length; x++) {
                //For BLACK pixel RGB all are set to 0 and for WHITE pixel all are set to 255.
                if (imagenBinaria[y][x] == targetValue) {
                    /**
                     * We are using a 3x3 kernel [1, 1, 1 1, 1, 1 1, 1, 1]
                     */
                    boolean flag = false;   //this will be set if a pixel of reverse value is found in the mask
                    for (int ty = y - 1; ty <= y + 1 && flag == false; ty++) {
                        for (int tx = x - 1; tx <= x + 1 && flag == false; tx++) {
                            if (ty >= 0 && ty < imagenBinaria[0].length && tx >= 0 && tx < imagenBinaria.length) {
                                //origin of the mask is on the image pixels
                                if (imagenBinaria[ty][tx] != targetValue) {
                                    flag = true;
                                    output[x + y * imagenBinaria.length] = reverseValue;
                                }
                            }
                        }
                    }
                    if (flag == false) {
                        //all pixels inside the mask [i.e., kernel] were of targetValue
                        output[x + y * imagenBinaria.length] = targetValue;
                    }
                } else {
                    output[x + y * imagenBinaria.length] = reverseValue;
                }
            }
        }

        /**
         * Save the erosion value in image img.
         */
        for (int y = 0; y < imagenBinaria.length; y++) {
            for (int x = 0; x < imagenBinaria[0].length; x++) {
                short s = (short) output[x + y * imagenBinaria.length];
                matrizErosion[y][x] = s;
            }
        }
        return matrizErosion;
    }

    public BufferedImage erosionImagenBinaria(boolean erodeForegroundPixel, BufferedImage imagenBinaria) {
        /**
         * Dimension of the image img.
         */

        BufferedImage imagenGris = imagenBinaria;
        int width = imagenBinaria.getWidth();
        int height = imagenBinaria.getHeight();

        // short[][] matrizErosion = new short[imagenBinaria.length][imagenBinaria[0].length];
        /**
         * This will hold the erosion result which will be copied to image img.
         */
        int output[] = new int[width * height];

        /**
         * If erosion is to be performed on BLACK pixels then targetValue = 0
         * else targetValue = 255; //for WHITE pixels
         */
        int targetValue = (erodeForegroundPixel == true) ? 0 : 255;

        /**
         * If the target pixel value is WHITE (255) then the reverse pixel value
         * will be BLACK (0) and vice-versa.
         */
        int reverseValue = (targetValue == 255) ? 0 : 255;

        //perform erosion
        for (int y = 0; y < width; y++) {
            for (int x = 0; x < height; x++) {
                Color auxColor = new Color(imagenBinaria.getRGB(x, y));
                //For BLACK pixel RGB all are set to 0 and for WHITE pixel all are set to 255.
                if (auxColor.getRed() == targetValue) {
                    /**
                     * We are using a 3x3 kernel [1, 1, 1 1, 1, 1 1, 1, 1]
                     */
                    boolean flag = false;   //this will be set if a pixel of reverse value is found in the mask
                    for (int ty = y - 1; ty <= y + 1 && flag == false; ty++) {
                        for (int tx = x - 1; tx <= x + 1 && flag == false; tx++) {
                            if (ty >= 0 && ty < height && tx >= 0 && tx < width) {
                                Color auxColor2 = new Color(imagenBinaria.getRGB(tx, ty));
                                //origin of the mask is on the image pixels
                                if (auxColor.getRed() != targetValue) {
                                    flag = true;
                                    output[x + y * width] = reverseValue;
                                }
                            }
                        }
                    }
                    if (flag == false) {
                        //all pixels inside the mask [i.e., kernel] were of targetValue
                        output[x + y * width] = targetValue;
                    }
                } else {
                    output[x + y * width] = reverseValue;
                }
            }
        }

        /**
         * Save the erosion value in image img.
         */
        for (int y = 0; y < width; y++) {
            for (int x = 0; x < width; x++) {
                short s = (short) output[x + y * width];
                imagenGris.setRGB(x, y, s);
            }
        }
        return imagenGris;
    }

    public BufferedImage erosion(BufferedImage img, int mask[], int maskSize) {
        BufferedImage imagenErosion = img;
        int width = img.getWidth();
        int height = img.getHeight();
        int buff[];
        int output[] = new int[width * height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                buff = new int[maskSize * maskSize];
                int i = 0;
                for (int ty = y - maskSize / 2, mr = 0; ty <= y + maskSize / 2; ty++, mr++) {
                    for (int tx = x - maskSize / 2, mc = 0; tx <= x + maskSize / 2; tx++, mc++) {
                        if (ty >= 0 && ty < height && tx >= 0 && tx < width) {
                            Color auxColor = new Color(img.getRGB(tx, ty));
                            if (mask[mr + mc / maskSize] != 1) {
                                continue;
                            }

                            buff[i] = auxColor.getRGB();
                            i++;

                        }
                    }
                }
                java.util.Arrays.sort(buff);
                output[x + y * width] = buff[(maskSize * maskSize) - i];
            }
        }
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int v = output[x + y * width];
                imagenErosion.setRGB(x, y, v);
            }
        }
        return imagenErosion;
    }

    public BufferedImage dilatacion(BufferedImage img, int mask[], int maskSize) {   //ESTE ES EL BUENO
        BufferedImage imagenGris = img;
        int width = img.getWidth();
        int height = img.getHeight();
        int buff[];
        int output[] = new int[width * height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                buff = new int[maskSize * maskSize];
                int i = 0;
                for (int ty = y - maskSize / 2, mr = 0; ty <= y + maskSize / 2; ty++, mr++) {
                    for (int tx = x - maskSize / 2, mc = 0; tx <= x + maskSize / 2; tx++, mc++) {
                        if (ty >= 0 && ty < height && tx >= 0 && tx < width) {
                            Color auxColor = new Color(img.getRGB(tx, ty));
                            if (mask[mr + mc / maskSize] != 1) {
                                continue;
                            }

                            buff[i] = auxColor.getRGB();
                            i++;

                        }
                    }
                }
                java.util.Arrays.sort(buff);
                output[x + y * width] = buff[(maskSize * maskSize) - i];
            }
        }
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int v = output[x + y * width];
                imagenGris.setRGB(x, y, v);
            }
        }
        return imagenGris;
    }


    /*Recibira la matriz original en caso de que esta tenga umbralizacion, en caso contrario este llamara al metodp umbralizar*/
    public BufferedImage erosionImagen(BufferedImage img, int mask[], int maskSize, boolean erodeForegroundPixel) {
        /**
         * Dimension of the image img.
         */
        BufferedImage imagenGris = img;
        int width = img.getWidth();
        int height = img.getHeight();
        int targetValue = (erodeForegroundPixel == true) ? 0 : 255;
        int reverseValue = (targetValue == 255) ? 0 : 255;

        //buff
        int buff[];

        //output of erosion
        int output[] = new int[width * height];

        //perform erosion
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                buff = new int[maskSize * maskSize];
                int i = 0;
                Color auxColor = new Color(img.getRGB(x, y));
                //  System.out.println(auxColor.getRGB());
                if (auxColor.getRed() == targetValue) {
                    //System.out.println("Si entro");
                    boolean flag = false;
                    for (int ty = y - maskSize / 2, mr = 0; ty <= y + maskSize / 2 && flag == false; ty++, mr++) {
                        for (int tx = x - maskSize / 2, mc = 0; tx <= x + maskSize / 2 && flag == false; tx++, mc++) {

                            /**
                             * Sample 3x3 mask [kernel or structuring element]
                             * [0, 1, 0 1, 1, 1 0, 1, 0]
                             *
                             * Only those pixels of the image img that are under
                             * the mask element 1 are considered.
                             */
                            if (ty >= 0 && ty < height && tx >= 0 && tx < width) {
                                Color auxColor2 = new Color(img.getRGB(tx, ty));
                                //pixel under the mask
                                // System.out.println(mc);
                                // System.out.println(mr);
                                //System.out.println(mc+mr*maskSize);
                                if (mask[mr + mc / maskSize] != 1) {
                                    continue;
                                }
                                if (auxColor2.getRed() != targetValue) {
                                    flag = true;
                                    //output[x + y * imagenBinaria.length] = reverseValue;
                                    buff[i] = reverseValue;

                                    i++;
                                }

                            }
                        }
                    }
                    if (flag == false) {
                        //all pixels inside the mask [i.e., kernel] were of targetValue
                        buff[i] = targetValue;
                        i++;

                    }
                } else {
                    buff[i] = reverseValue;
                    i++;
                    // System.out.println("Si entro");
                }
                //sort buff
                java.util.Arrays.sort(buff);
                // System.out.println(i);
                //save lowest value
                output[x + y * width] = buff[(maskSize * maskSize) - i];
            }
        }

        /**
         * Save the erosion value in image img.
         */
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int v = output[x + y * width];
                /*if (v==29) {
                    v = 255;
                }*/
                //System.out.println(v);
                imagenGris.setRGB(x, y, v);
                //Color auxColor = new Color(imagenGris.getRGB(x, y));
                //System.out.println(auxColor);
            }
        }
        return imagenGris;
    }

    public short[][] erosionImagenBinaria2(boolean erodeForegroundPixel, short[][] imagenBinaria, int[] mascara, int tam_Mascara) {
        /**
         * Dimension of the image img.
         */

        short[][] matrizErosion = new short[imagenBinaria.length][imagenBinaria[0].length];
        /**
         * This will hold the erosion result which will be copied to image img.
         */
        int output[] = new int[imagenBinaria.length * imagenBinaria[0].length];
        int buff[];
        /**
         * If erosion is to be performed on BLACK pixels then targetValue = 0
         * else targetValue = 255; //for WHITE pixels
         */
        int targetValue = (erodeForegroundPixel == true) ? 0 : 255;

        /**
         * If the target pixel value is WHITE (255) then the reverse pixel value
         * will be BLACK (0) and vice-versa.
         */
        int reverseValue = (targetValue == 255) ? 0 : 255;

        //perform erosion
        for (int y = 0; y < imagenBinaria.length; y++) {
            for (int x = 0; x < imagenBinaria[y].length; x++) {
                //For BLACK pixel RGB all are set to 0 and for WHITE pixel all are set to 255.
                if (imagenBinaria[y][x] == targetValue) {
                    /**
                     * We are using a 3x3 kernel [1, 1, 1 1, 1, 1 1, 1, 1]
                     */
                    boolean flag = false;   //this will be set if a pixel of reverse value is found in the mask
                    for (int ty = y - 1; ty <= y + 1 && flag == false; ty++) {
                        for (int tx = x - 1; tx <= x + 1 && flag == false; tx++) {
                            if (ty >= 0 && ty < imagenBinaria[0].length && tx >= 0 && tx < imagenBinaria.length) {
                                //origin of the mask is on the image pixels
                                if (imagenBinaria[ty][tx] != targetValue) {
                                    flag = true;
                                    output[x + y * imagenBinaria.length] = reverseValue;
                                }
                            }
                        }
                    }
                    if (flag == false) {
                        //all pixels inside the mask [i.e., kernel] were of targetValue
                        output[x + y * imagenBinaria.length] = targetValue;
                    }
                } else {
                    output[x + y * imagenBinaria.length] = reverseValue;
                }
            }
        }

        /**
         * Save the erosion value in image img.
         */
        for (int y = 0; y < imagenBinaria.length; y++) {
            for (int x = 0; x < imagenBinaria[0].length; x++) {
                short s = (short) output[x + y * imagenBinaria.length];
                matrizErosion[y][x] = s;
            }
        }
        return matrizErosion;
    }

    public short[][] convierteBFtoMatriz(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        short[][] result = new short[height][width];

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                result[row][col] = (short) (image.getRGB(col, row));
            }
        }

        return result;
    }

    private int histogramaGris[];
    private int maxNumPixelesGris;
    private int nivelDominanteGris;

    public void calcularHistogramaGris(short[][] matrizGris) {
        histogramaGris = new int[255 + 1];
        //realiza el conteo de numero de pixeles por nivel de intensidad
        for (int i = 0; i < matrizGris.length; i++) {
            for (int j = 0; j < matrizGris[0].length; j++) {
                int nivelGris = matrizGris[i][j];
                if (nivelGris < 256) {
                    histogramaGris[nivelGris]++;
                }
            }
        }
        //busca el maximo numero de pixeles para el nivel de intensidad dominante
        nivelDominanteGris = 0;
        maxNumPixelesGris = 0;
        for (int i = 0; i < histogramaGris.length; i++) {
            if (histogramaGris[i] > maxNumPixelesGris) {
                maxNumPixelesGris = histogramaGris[i];
                nivelDominanteGris = i;
            }
        }
    }

    public short[][] getImagenHistogramaGris() {
        //Imagen imagenHistograma = new Imagen();
        //imagenHistograma.setFormato("P2");
        //imagenHistograma.setM((short)256);
        //imagenHistograma.setN((short)270);
        //imagenHistograma.setNivelIntensidad((short)255);
        //matriz para dibujar el histograma
        short matrizGris[][] = new short[270][256];
        //todos los elementos de la matriz estan en blanco
        for (int i = 0; i < 270; i++) {
            for (int j = 0; j < 256; j++) {
                matrizGris[i][j] = 255;
            }
        }
        //crea la matriz con los datos del histograma
        for (int j = 0; j < histogramaGris.length; j++) {
            int numPixeles = histogramaGris[j];
            numPixeles = (255 * numPixeles) / this.maxNumPixelesGris;
            for (int n = 0; n < numPixeles; n++) {
                int i = 255 - n;
                matrizGris[i][j] = 0;
            }
        }
        for (int i = 260; i < 270; i++) {
            for (int j = 0; j < 256; j++) {
                matrizGris[i][j] = (short) j;
            }
        }

        return matrizGris;
    }
    
    
    
      public short [][] ecualizarGris(short [][] matrizG) {
        //Imagen nuevaImagen = new Imagen();
       // Histograma histograma = new Histograma(objImagen);
        //histograma de la imagen
        int histogramaGris[] = this.histogramaGris;
        int ancho = matrizG.length;
        int alto = matrizG[0].length;
        int L = 255;
        //histograma normalizado
        int histogramaNormal[] = new int[L+1];
        //calcula el nuevo histograma
        for(int i = 1; i < L+1; i++) {
            histogramaNormal[i] = histogramaNormal[i-1]+histogramaGris[i];
        }
        //crea vector LUT
        int LUT[] = new int[L+1];
        for(int i = 0; i < L; i++) {
            LUT[i] = (int)Math.floor(((L-1)*histogramaNormal[i])/(ancho*alto));
        }
        //crea la nueva imagen
       // nuevaImagen.setN(alto);
      //  nuevaImagen.setM(ancho);
       // nuevaImagen.setNivelIntensidad(L);
       // nuevaImagen.setFormato(objImagen.getFormato());
        //matriz escala de grises
        //short matrizGris[][] = objImagen.getMatrizGris();
        short matrizGris[][] = new short[alto][ancho];
        for(int i = 0; i < alto; i++) {
            for(int j = 0; j < ancho; j++) {
                matrizGris[i][j] = (short)LUT[matrizG[i][j]];
            }
        }
        //asigna la nueva matriz
       // nuevaImagen.setMatrizGris(matrizGris);
        return matrizGris;
    }

}
