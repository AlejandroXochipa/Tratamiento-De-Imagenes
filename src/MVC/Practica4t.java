/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MVC;

import Modelo.*;
import Vista.*;
import Controlador.*;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import mdlaf.*;

/**
 *
 * @author xochi
 */
public class Practica4t {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        try {
            UIManager.setLookAndFeel(new MaterialLookAndFeel());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        Ventana2 vent2 = new Ventana2();
        Imagen mod = new Imagen();
        //Ventana vent = new Ventana();
        Control crt = new Control(vent2,mod);
        crt.Iniciar();
        vent2.setVisible(true);
        vent2.datosImg2.setVisible(false);
        vent2.Panel3.setVisible(false);
        //System.out.println(vent2.getSize());
       
    }
    
}
