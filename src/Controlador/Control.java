/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.*;
import Vista.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.event.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

/**
 *
 * @author xochi
 */
public class Control implements ActionListener {

    //Ventana ventana1 = new Ventana();
    private Ventana2 view;
    // private Zoom zoom = new Zoom();
    private HSI dis_HSI = new HSI();
    // private Ventana view;
    private Imagen model;
    private FiltrosUmbra filtro = new FiltrosUmbra();
    private Imagen img = new Imagen();
    private Imagen img2 = new Imagen();
    private Imagen img3 = new Imagen();
    BufferedImage imagenActual3 = img.getBufferImagen();

    private short[][] matrizAct = new short[img.getFilas()][img.getColumnas()];
    private Operaciones op = new Operaciones();
    private VoltearMatriz vm = new VoltearMatriz();
    private FiltrosRestauracion fR = new FiltrosRestauracion();
    //private ImagePlus imp;

    public Control(Ventana2 view, Imagen model) {
        this.view = view;
        this.model = model;
        this.dis_HSI = dis_HSI;
        this.view.abreImagen.addActionListener(this);
        this.view.filtroGris.addActionListener(this);
        this.view.aplicaFiltro.addActionListener(this);
        this.view.imagenOriginal.addActionListener(this);
        this.view.noventa.addActionListener(this);
        this.view.cientoO.addActionListener(this);
        this.view.dosC.addActionListener(this);
        this.view.opA.addActionListener(this);
        this.view.opO.addActionListener(this);
        this.view.opX.addActionListener(this);
        this.view.opN.addActionListener(this);
        this.view.enviarKernel.addActionListener(this);
        this.view.mediaArit.addActionListener(this);
        this.view.mediaGeo.addActionListener(this);
        this.view.mediana.addActionListener(this);
        this.view.max.addActionListener(this);
        this.view.min.addActionListener(this);
        this.view.puntoMedio.addActionListener(this);
        this.view.mediaSeccion.addActionListener(this);
        this.view.mediaAdaptive.addActionListener(this);
        this.view.ILPF.addActionListener(this);
        this.view.BLPF.addActionListener(this);
        this.view.IHPF.addActionListener(this);
        this.view.BHPF.addActionListener(this);
        this.view.IBPF.addActionListener(this);
        this.view.IBSF.addActionListener(this);
        this.view.BBPF.addActionListener(this);
        this.view.BBSF.addActionListener(this);
        this.view.HSI.addActionListener(this);
        this.view.gaussian.addActionListener(this);
        this.view.zoom.addActionListener(this);
        this.view.histograma.addActionListener(this);
        this.view.cmy.addActionListener(this);
        this.view.espectro.addActionListener(this);
        this.view.erosion.addActionListener(this);
        this.view.dilatacion.addActionListener(this);
        this.view.apertura.addActionListener(this);
        this.view.cierre.addActionListener(this);

    }

    public void Iniciar() {
        view.setTitle("Proyecto Tratamiento De Imagenes");
        dis_HSI.setTitle("Modelo HSI");
        dis_HSI.setLocation(0, 0);
        view.setLocation(0, 0);
    }

    /* public void abreImagenactionPerformed(ActionEvent e) throws IOException{
        Imagen img = new Imagen(model.abrirImagen());
    }*/
    //@Override
    public void actionPerformed(ActionEvent e) {
        // Imagen img = new Imagen();
        try {
            if (e.getSource() == view.abreImagen) {
                img = new Imagen(model.abrirImagen());
                ImageIcon imagen3 = new ImageIcon(img.getBufferImagen());
                Icon imagen2 = new ImageIcon(imagen3.getImage().getScaledInstance(view.contenedorImagen.getWidth(), view.contenedorImagen.getHeight(), Image.SCALE_DEFAULT));
                // Icon imagen2 = new ImageIcon(imagen3.getImage());
                view.contenedorImagen.setIcon(imagen2);
                view.nombreImagen.setText(img.getNombre());
                view.formatoImagen.setText(img.getFormato());
                view.alto.setText(String.valueOf(img.getFilas()));
                view.ancho.setText(String.valueOf(img.getColumnas()));
                img2 = img.clone();
            }
            if (e.getSource() == view.filtroGris) {

                BufferedImage imagenActual = img2.convierteMatrizEnBufferedImage(img2.getMatrizGris());
                matrizAct = img2.getMatrizGris();
                ImageIcon img1 = new ImageIcon(imagenActual);
                Icon iconoI1 = new ImageIcon(img1.getImage().getScaledInstance(view.contenedorImagen.getWidth(), view.contenedorImagen.getHeight(), Image.SCALE_DEFAULT));
                System.out.println("Matriz gris " + img2.getMatrizGris().length + " " + img2.getMatrizGris()[0].length);
                view.contenedorImagen.setIcon(iconoI1);
            }
            if (e.getSource() == view.aplicaFiltro) {
                if (view.filtros.getSelectedItem().toString() == "Inverso o Negativo") {
                    BufferedImage imagenActual2 = model.convierteMatrizEnBufferedImage(filtro.calculaInverso(img2.getMatrizGris()));
                    matrizAct = filtro.calculaInverso(img2.getMatrizGris());
                    ImageIcon img12 = new ImageIcon(imagenActual2);
                    Icon iconoI2 = new ImageIcon(img12.getImage().getScaledInstance(view.contenedorImagen.getWidth(), view.contenedorImagen.getHeight(), Image.SCALE_DEFAULT));
                    view.contenedorImagen.setIcon(iconoI2);

                } if (view.filtros.getSelectedItem().toString() == "Umbral") {
                    String umbral = JOptionPane.showInputDialog("Ingrese el umbral que desea");
                    BufferedImage imagenActual3 = img2.convierteMatrizEnBufferedImage(filtro.operadorUmbral(img2.getMatrizGris(), Integer.parseInt(umbral)));
                    matrizAct = filtro.operadorUmbral(img2.getMatrizGris(), Integer.parseInt(umbral));
                    ImageIcon img13 = new ImageIcon(imagenActual3);
                    Icon iconoI3 = new ImageIcon(img13.getImage().getScaledInstance(view.contenedorImagen.getWidth(), view.contenedorImagen.getHeight(), Image.SCALE_DEFAULT));
                    view.contenedorImagen.setIcon(iconoI3);
                }
                if (view.filtros.getSelectedItem().toString() == "Umbral Inverso") {
                    String umbral = JOptionPane.showInputDialog("Ingrese el umbral que desea");
                    BufferedImage imagenActual3 = img2.convierteMatrizEnBufferedImage(filtro.operadorUmbralInverso(img2.getMatrizGris(), Integer.parseInt(umbral)));
                    matrizAct = filtro.operadorUmbralInverso(img2.getMatrizGris(), Integer.parseInt(umbral));
                    ImageIcon img13 = new ImageIcon(imagenActual3);
                    Icon iconoI3 = new ImageIcon(img13.getImage().getScaledInstance(view.contenedorImagen.getWidth(), view.contenedorImagen.getHeight(), Image.SCALE_DEFAULT));
                    view.contenedorImagen.setIcon(iconoI3);
                }
                if (view.filtros.getSelectedItem().toString() == "Umbral Binario") {
                    String ul = JOptionPane.showInputDialog("Ingrese el umbral 1");
                    String u2 = JOptionPane.showInputDialog("Ingrese el umbral 2");
                    //short [][] nuevaM = filtro.operadorUmbralBinario(img2.getMatrizGris(), Integer.parseInt(ul), Integer.parseInt(u2));
                    BufferedImage imagenActual4 = model.convierteMatrizEnBufferedImage(filtro.operadorUmbralBinario(img2.getMatrizGris(), Integer.parseInt(ul), Integer.parseInt(u2)));
                    matrizAct = filtro.operadorUmbralBinario(img2.getMatrizGris(), Integer.parseInt(ul), Integer.parseInt(u2));
                    ImageIcon img14 = new ImageIcon(imagenActual4);
                    Icon iconoI4 = new ImageIcon(img14.getImage().getScaledInstance(view.contenedorImagen.getWidth(), view.contenedorImagen.getHeight(), Image.SCALE_DEFAULT));
                    view.contenedorImagen.setIcon(iconoI4);
                    //model.imprimirMatriz(nuevaM);
                }
                if (view.filtros.getSelectedItem().toString() == "Umbral Binario Inverso") {
                    String ul = JOptionPane.showInputDialog("Ingrese el umbral 1");
                    String u2 = JOptionPane.showInputDialog("Ingrese el umbral 2");
                    //short [][] nuevaM = filtro.operadorUmbralBinario(img2.getMatrizGris(), Integer.parseInt(ul), Integer.parseInt(u2));
                    BufferedImage imagenActual4 = model.convierteMatrizEnBufferedImage(filtro.operadorUmbralBinarioInverso(img2.getMatrizGris(), Integer.parseInt(ul), Integer.parseInt(u2)));
                    matrizAct = filtro.operadorUmbralBinarioInverso(img2.getMatrizGris(), Integer.parseInt(ul), Integer.parseInt(u2));
                    ImageIcon img14 = new ImageIcon(imagenActual4);
                    Icon iconoI4 = new ImageIcon(img14.getImage().getScaledInstance(view.contenedorImagen.getWidth(), view.contenedorImagen.getHeight(), Image.SCALE_DEFAULT));
                    view.contenedorImagen.setIcon(iconoI4);
                    //model.imprimirMatriz(nuevaM);
                }
                if (view.filtros.getSelectedItem().toString() == "Umbral en Gris") {
                    String um1 = JOptionPane.showInputDialog("Ingrese el umbral 1");
                    String um2 = JOptionPane.showInputDialog("Ingrese el umbral 2");
                    BufferedImage imagenActual5 = model.convierteMatrizEnBufferedImage(filtro.operadorUmbralGris(img2.getMatrizGris(), Integer.parseInt(um1), Integer.parseInt(um2)));
                    matrizAct = filtro.operadorUmbralGris(img2.getMatrizGris(), Integer.parseInt(um1), Integer.parseInt(um2));
                    ImageIcon img15 = new ImageIcon(imagenActual5);
                    Icon iconoI5 = new ImageIcon(img15.getImage().getScaledInstance(view.contenedorImagen.getWidth(), view.contenedorImagen.getHeight(), Image.SCALE_DEFAULT));
                    view.contenedorImagen.setIcon(iconoI5);

                }
                if (view.filtros.getSelectedItem().toString() == "Umbral en Gris Inverso") {
                    String um1 = JOptionPane.showInputDialog("Ingrese el umbral 1");
                    String um2 = JOptionPane.showInputDialog("Ingrese el umbral 2");
                    BufferedImage imagenActual5 = model.convierteMatrizEnBufferedImage(filtro.operadorUmbralGrisInverso(img2.getMatrizGris(), Integer.parseInt(um1), Integer.parseInt(um2)));
                    matrizAct = filtro.operadorUmbralGrisInverso(img2.getMatrizGris(), Integer.parseInt(um1), Integer.parseInt(um2));
                    ImageIcon img15 = new ImageIcon(imagenActual5);
                    Icon iconoI5 = new ImageIcon(img15.getImage().getScaledInstance(view.contenedorImagen.getWidth(), view.contenedorImagen.getHeight(), Image.SCALE_DEFAULT));
                    view.contenedorImagen.setIcon(iconoI5);

                }
                if (view.filtros.getSelectedItem().toString() == "Extension") {
                    String umb1 = JOptionPane.showInputDialog("Ingrese el umbral 1");
                    String umb2 = JOptionPane.showInputDialog("Ingrese el umbral 2");
                    BufferedImage imagenActual6 = model.convierteMatrizEnBufferedImage(filtro.operadorExtension(img2.getMatrizGris(), Integer.parseInt(umb1), Integer.parseInt(umb2)));
                    matrizAct = filtro.operadorExtension(img2.getMatrizGris(), Integer.parseInt(umb1), Integer.parseInt(umb2));
                    ImageIcon img16 = new ImageIcon(imagenActual6);
                    Icon iconoI6 = new ImageIcon(img16.getImage().getScaledInstance(view.contenedorImagen.getWidth(), view.contenedorImagen.getHeight(), Image.SCALE_DEFAULT));
                    view.contenedorImagen.setIcon(iconoI6);

                }
                if (view.filtros.getSelectedItem().toString() == "Reduccion de Niveles de Gris") {
                    BufferedImage imagenActual7 = model.convierteMatrizEnBufferedImage(filtro.reduccionNivelesDeGris(img2.getMatrizGris()));
                    matrizAct = filtro.reduccionNivelesDeGris(img2.getMatrizGris());
                    ImageIcon img17 = new ImageIcon(imagenActual7);
                    Icon iconoI7 = new ImageIcon(img17.getImage().getScaledInstance(view.contenedorImagen.getWidth(), view.contenedorImagen.getHeight(), Image.SCALE_DEFAULT));
                    view.contenedorImagen.setIcon(iconoI7);
                }
                if (view.filtros.getSelectedItem().toString() == "Suma") {
                    JOptionPane.showMessageDialog(view, "Ingrese su segunda Imagen");
                    img3 = new Imagen(model.abrirImagen());
                    view.nom_2.setText(img3.getNombre());
                    view.fort_2.setText(img3.getFormato());
                    view.alt_2.setText(String.valueOf(img3.getFilas()));
                    view.anch_2.setText(String.valueOf(img3.getColumnas()));
                    view.datosImg2.setVisible(true);
                    ImageIcon img18 = new ImageIcon(model.convierteMatrizEnBufferedImage(op.Suma(img2.getMatrizGris(), img3.getMatrizGris())));
                    matrizAct = op.Suma(img2.getMatrizGris(), img3.getMatrizGris());
                    Icon IconoI8 = new ImageIcon(img18.getImage().getScaledInstance(view.contenedorImagen.getWidth(), view.contenedorImagen.getHeight(), Image.SCALE_DEFAULT));
                    view.contenedorImagen.setIcon(IconoI8);
                }
                if (view.filtros.getSelectedItem().toString() == "Resta") {
                    JOptionPane.showMessageDialog(view, "Ingrese su segunda Imagen");
                    img3 = new Imagen(model.abrirImagen());
                    view.nom_2.setText(img3.getNombre());
                    view.fort_2.setText(img3.getFormato());
                    view.alt_2.setText(String.valueOf(img3.getFilas()));
                    view.anch_2.setText(String.valueOf(img3.getColumnas()));
                    view.datosImg2.setVisible(true);
                    ImageIcon img18 = new ImageIcon(model.convierteMatrizEnBufferedImage(op.Resta(img2.getMatrizGris(), img3.getMatrizGris())));
                    matrizAct = op.Resta(img2.getMatrizGris(), img3.getMatrizGris());
                    Icon IconoI8 = new ImageIcon(img18.getImage().getScaledInstance(view.contenedorImagen.getWidth(), view.contenedorImagen.getHeight(), Image.SCALE_DEFAULT));
                    view.contenedorImagen.setIcon(IconoI8);
                }
              
                if (view.filtros.getSelectedItem().toString() == "Convolucion") {
                    JOptionPane.showMessageDialog(view, "Ingrese los valores de la mascara");
                    view.Panel3.setVisible(true);

                }
            }
            if (view.enviarKernel == e.getSource()) {
                short[][] kernel = {{Short.valueOf(view.valor_00.getText()), Short.valueOf(view.valor_01.getText()), Short.valueOf(view.valor_02.getText())}, {Short.valueOf(view.valor_10.getText()), Short.valueOf(view.valor_11.getText()), Short.valueOf(view.valor_12.getText())}, {Short.valueOf(view.valor_20.getText()), Short.valueOf(view.valor_21.getText()), Short.valueOf(view.valor_22.getText())}};
                //model.imprimirMatriz(kernel);
                BufferedImage imagenActual2 = model.convierteMatrizEnBufferedImage(op.Convolucion(img2.getMatrizGris(), kernel));
                matrizAct = op.Convolucion(img2.getMatrizGris(), kernel);
                ImageIcon img12 = new ImageIcon(imagenActual2);
                Icon iconoI2 = new ImageIcon(img12.getImage().getScaledInstance(view.contenedorImagen.getWidth(), view.contenedorImagen.getHeight(), Image.SCALE_DEFAULT));
                view.contenedorImagen.setIcon(iconoI2);

            }
            if (e.getSource() == view.imagenOriginal) {
                ImageIcon imagen8 = new ImageIcon(img.getBufferImagen());
                Icon icon8 = new ImageIcon(imagen8.getImage().getScaledInstance(view.contenedorImagen.getWidth(), view.contenedorImagen.getHeight(), Image.SCALE_DEFAULT));
                view.contenedorImagen.setIcon(icon8);
                view.datosImg2.setVisible(false);
                view.Panel3.setVisible(false);

            }
            if (e.getSource() == view.noventa) {
                ImageIcon img18 = new ImageIcon(model.convierteMatrizEnBufferedImage(vm.gira90Grados(matrizAct)));
                Icon IconoI8 = new ImageIcon(img18.getImage().getScaledInstance(view.contenedorImagen.getWidth(), view.contenedorImagen.getHeight(), Image.SCALE_DEFAULT));
                view.contenedorImagen.setIcon(IconoI8);
            }
            if (e.getSource() == view.cientoO) {
                ImageIcon img18 = new ImageIcon(model.convierteMatrizEnBufferedImage(vm.gira180Grados(matrizAct)));
                Icon IconoI8 = new ImageIcon(img18.getImage().getScaledInstance(view.contenedorImagen.getWidth(), view.contenedorImagen.getHeight(), Image.SCALE_DEFAULT));
                view.contenedorImagen.setIcon(IconoI8);
            }
            if (e.getSource() == view.dosC) {
                ImageIcon img18 = new ImageIcon(model.convierteMatrizEnBufferedImage(vm.gira270Grados(matrizAct)));
                Icon IconoI8 = new ImageIcon(img18.getImage().getScaledInstance(view.contenedorImagen.getWidth(), view.contenedorImagen.getHeight(), Image.SCALE_DEFAULT));
                view.contenedorImagen.setIcon(IconoI8);
            }
            if (e.getSource() == view.opA) {
                JOptionPane.showMessageDialog(view, "Ingrese su segunda Imagen");
                img3 = new Imagen(model.abrirImagen());
                view.nom_2.setText(img3.getNombre());
                view.fort_2.setText(img3.getFormato());
                view.alt_2.setText(String.valueOf(img3.getFilas()));
                view.anch_2.setText(String.valueOf(img3.getColumnas()));
                view.datosImg2.setVisible(true);
                ImageIcon img18 = new ImageIcon(model.convierteMatrizEnBufferedImage(op.operadorAnd(filtro.operadorUmbralBinario(img2.getMatrizGris(), 1, 150), filtro.operadorUmbralBinario(img3.getMatrizGris(), 1, 127))));
                matrizAct = op.operadorAnd(filtro.operadorUmbralBinario(img2.getMatrizGris(), 1, 150), filtro.operadorUmbralBinario(img3.getMatrizGris(), 1, 127));
                Icon IconoI8 = new ImageIcon(img18.getImage().getScaledInstance(view.contenedorImagen.getWidth(), view.contenedorImagen.getHeight(), Image.SCALE_DEFAULT));
                view.contenedorImagen.setIcon(IconoI8);
            }
            if (e.getSource() == view.opO) {
                JOptionPane.showMessageDialog(view, "Ingrese su segunda Imagen");
                img3 = new Imagen(model.abrirImagen());
                view.nom_2.setText(img3.getNombre());
                view.fort_2.setText(img3.getFormato());
                view.alt_2.setText(String.valueOf(img3.getFilas()));
                view.anch_2.setText(String.valueOf(img3.getColumnas()));
                view.datosImg2.setVisible(true);
                ImageIcon img18 = new ImageIcon(model.convierteMatrizEnBufferedImage(op.opeadorOr(filtro.operadorUmbralBinario(img2.getMatrizGris(), 1, 150), filtro.operadorUmbralBinario(img3.getMatrizGris(), 1, 127))));
                matrizAct = op.opeadorOr(filtro.operadorUmbralBinario(img2.getMatrizGris(), 1, 150), filtro.operadorUmbralBinario(img3.getMatrizGris(), 1, 127));
                Icon IconoI8 = new ImageIcon(img18.getImage().getScaledInstance(view.contenedorImagen.getWidth(), view.contenedorImagen.getHeight(), Image.SCALE_DEFAULT));
                view.contenedorImagen.setIcon(IconoI8);
            }
            if (e.getSource() == view.opX) {
                JOptionPane.showMessageDialog(view, "Ingrese su segunda Imagen");
                view.nom_2.setText(img3.getNombre());
                view.fort_2.setText(img3.getFormato());
                view.alt_2.setText(String.valueOf(img3.getFilas()));
                view.anch_2.setText(String.valueOf(img3.getColumnas()));
                img3 = new Imagen(model.abrirImagen());
                view.datosImg2.setVisible(true);
                ImageIcon img18 = new ImageIcon(model.convierteMatrizEnBufferedImage(op.opeadorXor(filtro.operadorUmbralBinario(img2.getMatrizGris(), 1, 150), filtro.operadorUmbralBinario(img3.getMatrizGris(), 1, 127))));
                matrizAct = op.opeadorXor(filtro.operadorUmbralBinario(img2.getMatrizGris(), 1, 150), filtro.operadorUmbralBinario(img3.getMatrizGris(), 1, 127));
                Icon IconoI8 = new ImageIcon(img18.getImage().getScaledInstance(view.contenedorImagen.getWidth(), view.contenedorImagen.getHeight(), Image.SCALE_DEFAULT));
                view.contenedorImagen.setIcon(IconoI8);
            }
            if (e.getSource() == view.opN) {
                ImageIcon img18 = new ImageIcon(model.convierteMatrizEnBufferedImage(op.not(filtro.operadorUmbralBinario(img2.getMatrizGris(), 1, 150))));
                view.datosImg2.setVisible(false);
                matrizAct = op.not(filtro.operadorUmbralBinario(img2.getMatrizGris(), 1, 150));
                Icon IconoI8 = new ImageIcon(img18.getImage().getScaledInstance(view.contenedorImagen.getWidth(), view.contenedorImagen.getHeight(), Image.SCALE_DEFAULT));
                view.contenedorImagen.setIcon(IconoI8);
            }
            if (e.getSource() == view.mediaArit) {
                BufferedImage imagenActual2 = model.convierteMatrizEnBufferedImage(fR.mediaAritmetica(img2.getMatrizGris()));
                matrizAct = fR.mediaAritmetica(img2.getMatrizGris());
                ImageIcon img12 = new ImageIcon(imagenActual2);
                Icon iconoI2 = new ImageIcon(img12.getImage().getScaledInstance(view.contenedorImagen.getWidth(), view.contenedorImagen.getHeight(), Image.SCALE_DEFAULT));
                view.contenedorImagen.setIcon(iconoI2);
            }
            if (e.getSource() == view.mediaGeo) {
                BufferedImage imagenActual2 = model.convierteMatrizEnBufferedImage(fR.mediaGeometrica(img2.getMatrizGris()));
                matrizAct = fR.mediaGeometrica(img2.getMatrizGris());
                ImageIcon img12 = new ImageIcon(imagenActual2);
                Icon iconoI2 = new ImageIcon(img12.getImage().getScaledInstance(view.contenedorImagen.getWidth(), view.contenedorImagen.getHeight(), Image.SCALE_DEFAULT));
                view.contenedorImagen.setIcon(iconoI2);
            }
            if (e.getSource() == view.max) {
                BufferedImage imagenActual2 = model.convierteMatrizEnBufferedImage(fR.mayor(img2.getMatrizGris()));
                matrizAct = fR.mayor(img2.getMatrizGris());
                ImageIcon img12 = new ImageIcon(imagenActual2);
                Icon iconoI2 = new ImageIcon(img12.getImage().getScaledInstance(view.contenedorImagen.getWidth(), view.contenedorImagen.getHeight(), Image.SCALE_DEFAULT));
                view.contenedorImagen.setIcon(iconoI2);
            }
            if (e.getSource() == view.mediana) {
                BufferedImage imagenActual2 = model.convierteMatrizEnBufferedImage(fR.mediana(img2.getMatrizGris()));
                matrizAct = fR.mediana(img2.getMatrizGris());
                ImageIcon img12 = new ImageIcon(imagenActual2);
                Icon iconoI2 = new ImageIcon(img12.getImage().getScaledInstance(view.contenedorImagen.getWidth(), view.contenedorImagen.getHeight(), Image.SCALE_DEFAULT));
                view.contenedorImagen.setIcon(iconoI2);
            }
            if (e.getSource() == view.min) {
                BufferedImage imagenActual2 = model.convierteMatrizEnBufferedImage(fR.menor(img2.getMatrizGris()));
                matrizAct = fR.menor(img2.getMatrizGris());
                ImageIcon img12 = new ImageIcon(imagenActual2);
                Icon iconoI2 = new ImageIcon(img12.getImage().getScaledInstance(view.contenedorImagen.getWidth(), view.contenedorImagen.getHeight(), Image.SCALE_DEFAULT));
                view.contenedorImagen.setIcon(iconoI2);
            }
            if (e.getSource() == view.mediaSeccion) {
                BufferedImage imagenActual2 = model.convierteMatrizEnBufferedImage(fR.mediaSeccion(img2.getMatrizGris()));
                matrizAct = fR.mediaSeccion(img2.getMatrizGris());
                ImageIcon img12 = new ImageIcon(imagenActual2);
                Icon iconoI2 = new ImageIcon(img12.getImage().getScaledInstance(view.contenedorImagen.getWidth(), view.contenedorImagen.getHeight(), Image.SCALE_DEFAULT));
                view.contenedorImagen.setIcon(iconoI2);
            }
            if (e.getSource() == view.puntoMedio) {
                BufferedImage imagenActual2 = model.convierteMatrizEnBufferedImage(fR.puntoMedio(img2.getMatrizGris()));
                matrizAct = fR.puntoMedio(img2.getMatrizGris());
                ImageIcon img12 = new ImageIcon(imagenActual2);
                Icon iconoI2 = new ImageIcon(img12.getImage().getScaledInstance(view.contenedorImagen.getWidth(), view.contenedorImagen.getHeight(), Image.SCALE_DEFAULT));
                view.contenedorImagen.setIcon(iconoI2);

            }
            if (e.getSource() == view.mediaAdaptive) {
                BufferedImage imagenActual2 = model.convierteMatrizEnBufferedImage(fR.mediaAdaptivo(img2.getMatrizGris()));
                matrizAct = fR.mediaAdaptivo(img2.getMatrizGris());
                ImageIcon img12 = new ImageIcon(imagenActual2);
                Icon iconoI2 = new ImageIcon(img12.getImage().getScaledInstance(view.contenedorImagen.getWidth(), view.contenedorImagen.getHeight(), Image.SCALE_DEFAULT));
                view.contenedorImagen.setIcon(iconoI2);

            }
            if (e.getSource() == view.ILPF) {
                String[] opciones = {"10", "30", "60", "90", "160", "460"};
                String n = (String) (JOptionPane.showInputDialog(null, "Selecciona un grado", "Elegir", JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]));
                ImageFFT fft = new ImageFFT(model.convertRGBAToIndexed(img2.getBufferImagen()));
                fft.transform();
                fft.idealLowPassFilter(fft.conversion(Integer.parseInt(n)));
                fft.transform();
                ImageIcon img12 = new ImageIcon(fft.toImage(null));
                Icon iconoI2 = new ImageIcon(img12.getImage().getScaledInstance(view.contenedorImagen.getWidth(), view.contenedorImagen.getHeight(), Image.SCALE_DEFAULT));
                view.contenedorImagen.setIcon(iconoI2);
                //zoom.setVisible(true);
                //zoom.verZoom.setIcon(iconoI2);
            }
            if (e.getSource() == view.BLPF) {
                String[] opciones = {"10", "30", "60", "90", "160", "460"};
                String n = (String) (JOptionPane.showInputDialog(null, "Selecciona un grado", "Elegir", JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]));
                ImageFFT fft = new ImageFFT(model.convertRGBAToIndexed(img2.getBufferImagen()));
                fft.transform();
                fft.butterworthLowPassFilter(3, fft.conversion(Integer.parseInt(n)));
                fft.transform();
                ImageIcon img12 = new ImageIcon(fft.toImage(null));
                Icon iconoI2 = new ImageIcon(img12.getImage().getScaledInstance(view.contenedorImagen.getWidth(), view.contenedorImagen.getHeight(), Image.SCALE_DEFAULT));
                view.contenedorImagen.setIcon(iconoI2);
            }
            if (e.getSource() == view.IHPF) {
                String[] opciones = {"10", "30", "60", "160", "460"};
                String n = (String) (JOptionPane.showInputDialog(null, "Selecciona un grado", "Elegir", JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]));
                ImageFFT fft = new ImageFFT(model.convertRGBAToIndexed(img2.getBufferImagen()));
                fft.transform();
                fft.idealHighPassFilter(fft.conversion(Integer.parseInt(n)));
                fft.transform();
                ImageIcon img12 = new ImageIcon(fft.toImage(null));
                Icon iconoI2 = new ImageIcon(img12.getImage().getScaledInstance(view.contenedorImagen.getWidth(), view.contenedorImagen.getHeight(), Image.SCALE_DEFAULT));
                view.contenedorImagen.setIcon(iconoI2);
            }
            if (e.getSource() == view.BHPF) {
                String[] opciones = {"10", "15", "30", "60", "80", "160", "460"};
                String n = (String) (JOptionPane.showInputDialog(null, "Selecciona un grado", "Elegir", JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]));
                ImageFFT fft = new ImageFFT(model.convertRGBAToIndexed(img2.getBufferImagen()));
                fft.transform();
                fft.butterworthHighPassFilter(2, fft.conversion(Integer.parseInt(n)));
                fft.transform();
                ImageIcon img12 = new ImageIcon(fft.toImage(null));
                Icon iconoI2 = new ImageIcon(img12.getImage().getScaledInstance(view.contenedorImagen.getWidth(), view.contenedorImagen.getHeight(), Image.SCALE_DEFAULT));
                view.contenedorImagen.setIcon(iconoI2);
            }
            if (e.getSource() == view.IBPF) {
                String[] opciones = {"10", "30", "60", "160", "460"};
                String n = (String) (JOptionPane.showInputDialog(null, "Selecciona un grado", "Elegir", JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]));
                ImageFFT fft = new ImageFFT(model.convertRGBAToIndexed(img2.getBufferImagen()));
                fft.transform();
                fft.idealBandPassFilter(fft.conversion(Integer.parseInt(n)), 0.020);
                fft.transform();
                ImageIcon img12 = new ImageIcon(fft.toImage(null));
                Icon iconoI2 = new ImageIcon(img12.getImage().getScaledInstance(view.contenedorImagen.getWidth(), view.contenedorImagen.getHeight(), Image.SCALE_DEFAULT));
                view.contenedorImagen.setIcon(iconoI2);
            }
            if (e.getSource() == view.IBSF) {
                String[] opciones = {"10", "30", "60", "160", "460"};
                String n = (String) (JOptionPane.showInputDialog(null, "Selecciona un grado", "Elegir", JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]));
                ImageFFT fft = new ImageFFT(model.convertRGBAToIndexed(img2.getBufferImagen()));
                fft.transform();
                fft.idealBandStopFilter(fft.conversion(Integer.parseInt(n)), 0.020);
                fft.transform();
                ImageIcon img12 = new ImageIcon(fft.toImage(null));
                Icon iconoI2 = new ImageIcon(img12.getImage().getScaledInstance(view.contenedorImagen.getWidth(), view.contenedorImagen.getHeight(), Image.SCALE_DEFAULT));
                view.contenedorImagen.setIcon(iconoI2);
            }
            if (e.getSource() == view.BBPF) {
                String[] opciones = {"10", "30", "60", "160", "460"};
                String n = (String) (JOptionPane.showInputDialog(null, "Selecciona un grado", "Elegir", JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]));
                ImageFFT fft = new ImageFFT(model.convertRGBAToIndexed(img2.getBufferImagen()));
                fft.transform();
                fft.butterworthBandPassFilter(fft.conversion(Integer.parseInt(n)), 0.020);
                fft.transform();
                ImageIcon img12 = new ImageIcon(fft.toImage(null));
                Icon iconoI2 = new ImageIcon(img12.getImage().getScaledInstance(view.contenedorImagen.getWidth(), view.contenedorImagen.getHeight(), Image.SCALE_DEFAULT));
                view.contenedorImagen.setIcon(iconoI2);
            }
            if (e.getSource() == view.BBSF) {
                String[] opciones = {"10", "30", "60", "160", "460"};
                String n = (String) (JOptionPane.showInputDialog(null, "Selecciona un grado", "Elegir", JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]));
                ImageFFT fft = new ImageFFT(model.convertRGBAToIndexed(img2.getBufferImagen()));
                fft.transform();
                fft.butterworthBandStopFilter(fft.conversion(Integer.parseInt(n)), 0.020);
                fft.transform();
                ImageIcon img12 = new ImageIcon(fft.toImage(null));
                Icon iconoI2 = new ImageIcon(img12.getImage().getScaledInstance(view.contenedorImagen.getWidth(), view.contenedorImagen.getHeight(), Image.SCALE_DEFAULT));
                view.contenedorImagen.setIcon(iconoI2);
            }
            if (e.getSource() == view.HSI) {
                ImageIcon img12 = new ImageIcon(img.convierteMatrizEnBufferedImage(img.matiz()));
                ImageIcon img13 = new ImageIcon(img.convierteMatrizEnBufferedImage(img.saturacion()));
                ImageIcon img14 = new ImageIcon(img.convierteMatrizEnBufferedImage(img.intensidad()));
                dis_HSI.setVisible(true);
                Icon iconoI2 = new ImageIcon(img12.getImage().getScaledInstance(dis_HSI.Matiz.getWidth(), dis_HSI.Matiz.getHeight(), Image.SCALE_DEFAULT));
                Icon iconoI3 = new ImageIcon(img13.getImage().getScaledInstance(dis_HSI.Saturacion.getWidth(), dis_HSI.Saturacion.getHeight(), Image.SCALE_DEFAULT));
                Icon iconoI4 = new ImageIcon(img14.getImage().getScaledInstance(dis_HSI.Intensidad.getWidth(), dis_HSI.Intensidad.getHeight(), Image.SCALE_DEFAULT));
                dis_HSI.Matiz.setIcon(iconoI2);
                dis_HSI.Intensidad.setIcon(iconoI3);
                dis_HSI.Saturacion.setIcon(iconoI4);
                dis_HSI.setDefaultCloseOperation(dis_HSI.DISPOSE_ON_CLOSE);
            }
            if (e.getSource() == view.gaussian) {
                String[] opciones = {"10", "15", "30", "60", "80", "160", "460"};
                String n = (String) (JOptionPane.showInputDialog(null, "Selecciona un grado", "Elegir", JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]));
                ImageFFT fft = new ImageFFT(model.convertRGBAToIndexed(img2.getBufferImagen()));
                fft.transform();

                fft.frequencyEmphashysFilter(2, fft.conversion(Integer.parseInt(n)));
                //fft.gaussianHighPassFilter(2, fft.conversion(Integer.parseInt(n)));
                fft.transform();
                //fft.equalize(fft.toImage(null))
                // BufferedImage imagen_Nueva = fft.getGrayscaleImage(fft.toImage(null));
                img.setBufferImagen(fft.toImage(null));
                img.calcularHistogramaGris(img.getMatrizGris());
                ImageIcon img12 = new ImageIcon(img.convierteMatrizEnBufferedImage(img.ecualizarGris(img.getMatrizGris())));
                //ImageIcon img12 = new ImageIcon(fft.toImage(null));
                Icon iconoI2 = new ImageIcon(img12.getImage().getScaledInstance(view.contenedorImagen.getWidth(), view.contenedorImagen.getHeight(), Image.SCALE_DEFAULT));
                view.contenedorImagen.setIcon(iconoI2);
            }
            if (e.getSource() == view.histograma) {
                EventQueue.invokeLater(() -> {
                    try {

                        new Histogram(img.getArchivoImagen()).display();
                    } catch (IOException ex) {
                        Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
            }
            if (e.getSource() == view.zoom) {
                BufferedImage imagenActual6 = model.convierteMatrizEnBufferedImage(op.promedioDelPunto(img2.getMatrizGris()));
                ImageIcon img16 = new ImageIcon(imagenActual6);
                Icon iconoI6 = new ImageIcon(img16.getImage().getScaledInstance(op.promedioDelPunto(img2.getMatrizGris()).length, op.promedioDelPunto(img2.getMatrizGris())[0].length, Image.SCALE_DEFAULT));
                JFrame ventana = new JFrame("Zoom");
                JScrollPane scroll = new JScrollPane();
                JLabel etiqueta = new JLabel();
                etiqueta.setIcon(iconoI6);
                ventana.getContentPane().add(scroll);
                scroll.setViewportView(etiqueta);
                ventana.pack();
                ventana.setDefaultCloseOperation(ventana.DISPOSE_ON_CLOSE);
                ventana.setVisible(true);
            }
            if (e.getSource() == view.cmy) {
                ImageIcon img12 = new ImageIcon(img.rgbACmy(img.getMatrizR(), img.getMatrizG(), img.getMatrizB()));
                Icon iconoI2 = new ImageIcon(img12.getImage().getScaledInstance(view.contenedorImagen.getWidth(), view.contenedorImagen.getHeight(), Image.SCALE_DEFAULT));
                view.contenedorImagen.setIcon(iconoI2);
            }
            if (e.getSource() == view.espectro) {
                ImageFFT fft = new ImageFFT(model.convertRGBAToIndexed(img2.getBufferImagen()));
                fft.transform();
                ImageIcon img12 = new ImageIcon(fft.getSpectrum());
                Icon iconoI2 = new ImageIcon(img12.getImage().getScaledInstance(view.contenedorImagen.getWidth(), view.contenedorImagen.getHeight(), Image.SCALE_DEFAULT));
                view.contenedorImagen.setIcon(iconoI2);
            }
            if (e.getSource() == view.erosion) {
                int[] mascara = {1, 1, 1, 1, 1, 1, 1, 1, 1};
                ImageIcon img13 = new ImageIcon(img.erosion(img.getBufferImagen(), mascara, mascara.length));
                Icon iconoI3 = new ImageIcon(img13.getImage().getScaledInstance(view.contenedorImagen.getWidth(), view.contenedorImagen.getHeight(), Image.SCALE_DEFAULT));
                view.contenedorImagen.setIcon(iconoI3);
            }
            if (e.getSource() == view.dilatacion) {
                int[] mascara = {0, 1, 0, 1, 1, 1, 0, 1, 0};
                ImageIcon img13 = new ImageIcon(img.dilatacion(img.getBufferImagen(), mascara, mascara.length));
                Icon iconoI3 = new ImageIcon(img13.getImage().getScaledInstance(view.contenedorImagen.getWidth(), view.contenedorImagen.getHeight(), Image.SCALE_DEFAULT));
                view.contenedorImagen.setIcon(iconoI3);
            }
            if (e.getSource() == view.apertura) {
                int[] mascaraErosion = {1, 1, 1, 1, 1, 1, 1, 1, 1};
                int[] mascaraDilatacion = {0, 1, 0, 1, 1, 1, 0, 1, 0};
                BufferedImage ero = img.erosion(img.getBufferImagen(), mascaraErosion, mascaraErosion.length);
                BufferedImage dila = img.dilatacion(ero, mascaraDilatacion, mascaraDilatacion.length);
                ImageIcon img13 = new ImageIcon(dila);
                Icon iconoI3 = new ImageIcon(img13.getImage().getScaledInstance(view.contenedorImagen.getWidth(), view.contenedorImagen.getHeight(), Image.SCALE_DEFAULT));
                view.contenedorImagen.setIcon(iconoI3);
            }
            if (e.getSource() == view.cierre) {
                int[] mascaraErosion = {1, 1, 1, 1, 1, 1, 1, 1, 1};
                int[] mascaraDilatacion = {0, 1, 0, 1, 1, 1, 0, 1, 0};
                BufferedImage dila = img.dilatacion(img.getBufferImagen(), mascaraDilatacion, mascaraDilatacion.length);
                BufferedImage ero = img.erosion(dila, mascaraErosion, mascaraErosion.length);
                ImageIcon img13 = new ImageIcon(ero);
                Icon iconoI3 = new ImageIcon(img13.getImage().getScaledInstance(view.contenedorImagen.getWidth(), view.contenedorImagen.getHeight(), Image.SCALE_DEFAULT));
                view.contenedorImagen.setIcon(iconoI3);
            } 

        } catch (IOException ex) {
            Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FFTException ex) {
            Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
