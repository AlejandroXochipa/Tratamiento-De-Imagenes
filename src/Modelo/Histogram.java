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
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Paint;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.DefaultDrawingSupplier;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYBarPainter;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.data.statistics.HistogramDataset;
import Modelo.*;
import java.io.FileInputStream;

/**
 * @see https://stackoverflow.com/q/40537278/230513
 * @see https://stackoverflow.com/q/11870416/230513
 * @see https://stackoverflow.com/a/28519356/230513
 */
public class Histogram {

   
   // private Imagen img = new Imagen();
//    private File archivoImagen = img.getArchivoImagen();
   // File ruta = new File("C:\\Users\\xochi\\OneDrive\\Escritorio\\Tratamiento de imagenes\\rick-and-morty-en-pornhub-1 (1).jpg");
    private  BufferedImage image = null;
     private static final int BINS = 256;
   
    private HistogramDataset dataset;
    private XYBarRenderer renderer;
   
    //File ruta = new File("C:\\Users\\xochi\\OneDrive\\Escritorio\\Tratamiento de imagenes\\rick-and-morty-en-pornhub-1 (1).jpg");
    //String ruta= "C:\\Users\\xochi\\OneDrive\\Escritorio\\Tratamiento de imagenes\\NebOrion.jpg";

    public Histogram(File ruta) throws IOException {
        this.image = ImageIO.read(new FileInputStream(ruta));
    }


    private ChartPanel createChartPanel() {
        // dataset
       
        dataset = new HistogramDataset();
        Raster raster = image.getRaster();
        final int w = image.getWidth();
        final int h = image.getHeight();
        double[] r = new double[w * h];
        r = raster.getSamples(0, 0, w, h, 0, r);
        dataset.addSeries("Red", r, BINS);
        r = raster.getSamples(0, 0, w, h, 1, r);
        dataset.addSeries("Green", r, BINS);
        r = raster.getSamples(0, 0, w, h, 2, r);
        dataset.addSeries("Blue", r, BINS);
        // chart
        JFreeChart chart = ChartFactory.createHistogram("Histograma", "Value",
            "Count", dataset, PlotOrientation.VERTICAL, true, true, false);
        XYPlot plot = (XYPlot) chart.getPlot();
        renderer = (XYBarRenderer) plot.getRenderer();
        renderer.setBarPainter(new StandardXYBarPainter());
        // translucent red, green & blue
        Paint[] paintArray = {
            new Color(0x80ff0000, true),
            new Color(0x8000ff00, true),
            new Color(0x800000ff, true)
        };
        plot.setDrawingSupplier(new DefaultDrawingSupplier(
            paintArray,
            DefaultDrawingSupplier.DEFAULT_FILL_PAINT_SEQUENCE,
            DefaultDrawingSupplier.DEFAULT_OUTLINE_PAINT_SEQUENCE,
            DefaultDrawingSupplier.DEFAULT_STROKE_SEQUENCE,
            DefaultDrawingSupplier.DEFAULT_OUTLINE_STROKE_SEQUENCE,
            DefaultDrawingSupplier.DEFAULT_SHAPE_SEQUENCE));
        ChartPanel panel = new ChartPanel(chart);
        panel.setMouseWheelEnabled(true);
        return panel;
    }

    private JPanel createControlPanel() {
        JPanel panel = new JPanel();
        panel.add(new JCheckBox(new VisibleAction(0)));
        panel.add(new JCheckBox(new VisibleAction(1)));
        panel.add(new JCheckBox(new VisibleAction(2)));
        return panel;
    }

    private class VisibleAction extends AbstractAction {

        private final int i;

        public VisibleAction(int i) {
            this.i = i;
            this.putValue(NAME, (String) dataset.getSeriesKey(i));
            this.putValue(SELECTED_KEY, true);
            renderer.setSeriesVisible(i, true);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            renderer.setSeriesVisible(i, !renderer.getSeriesVisible(i));
        }
    }

    public void display() {
        JFrame f = new JFrame("Histograma");
        //f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setDefaultCloseOperation(f.DISPOSE_ON_CLOSE);
        f.add(createChartPanel());
        f.add(createControlPanel(), BorderLayout.SOUTH);
        //f.add(new JLabel(new ImageIcon(image)), BorderLayout.WEST);
        f.pack();
        f.setSize(new Dimension(500,500));
        System.out.println(f.getSize());
       // this.setSize(new Dimension(300, 300));
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
    
    
    
   
}