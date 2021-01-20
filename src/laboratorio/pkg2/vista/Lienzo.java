/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laboratorio.pkg2.vista;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import javax.swing.JPanel;
import laboratorio.pkg2.modelo.ABB;
import laboratorio.pkg2.modelo.Nodo;

/**
 *
 * @author JAIVE
 */
public class Lienzo extends JPanel implements MouseWheelListener {

    double zoom = 2;

    private int gx;
    private int gy;
    private int bus;
    private String lista;
    private ABB arbol;
    private Nodo nodoBusqueda;
    private Nodo nodoCamino;

    public Lienzo() {
        setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
        setOpaque(false);
        this.zoom = 0.5;

        this.gx = 0;
        this.gy = 0;

        this.setToolTipText("Use el mouse para moverse y  hacer zoom");

        addMouseWheelListener((MouseWheelListener) this);
        addMouseMotionListener(new Adaptador_Mouse());
        this.lista="| ";
        this.arbol = new ABB();
        this.arbol.añadir(4);
        this.arbol.añadir(6);
        this.arbol.añadir(2);
        /*this.arbol.añadir(97);
        this.arbol.añadir(45);
        this.arbol.añadir(7);
        this.arbol.añadir(16);
        this.arbol.añadir(55);
        this.arbol.añadir(85);
        this.arbol.añadir(1);
        this.arbol.añadir(0);
        
        //this.arbol.inorden(this.arbol.getRaiz());
        /* ArrayList l = this.arbol.getLista();
        for (int i = 0; i < l.size()-1; i++) {
            System.out.println(i+":"+l.get(i)+"("+log2(i));
        }*/
    }

    public ABB getArbol() {
        return arbol;
    }

    private void setArbol(ABB arbol) {
        this.arbol = arbol;
    }

    private Nodo getNodoBusqueda() {
        return nodoBusqueda;
    }

    public void setNodoBusqueda(Nodo nodoBusqueda) {
        this.nodoBusqueda = nodoBusqueda;
    }

    private Nodo getnodoCamino() {
        return nodoCamino;
    }

    public void setnodoCamino(Nodo nodoCamino) {
        this.nodoCamino = nodoCamino;
    }

    private String getLista() {
        return lista;
    }

    public void setLista(String lista) {
        this.lista = lista;
    }
    
     public void addLista(int data) {
        this.lista += data+" | ";
    }

    public void dibujoRecursivo(Graphics g2, int minX, int maxX,
            int y, int Saltoy, int altura, Nodo nodo) {

        String valor = String.valueOf(nodo.getValor());

        
        FontMetrics fm = g2.getFontMetrics();
        int width = fm.stringWidth(valor);
        int height = fm.getHeight();

        int xSep = Math.min((maxX - minX) / 8, 10);

        if (nodo == nodoCamino) {
            g2.setColor(Color.red);
        } else if (nodo == nodoBusqueda) {
            g2.setColor(Color.blue);
        } else {
            g2.setColor(Color.black);
        }
        //si la altura es mayor a 5 no dibujar el circulo, por falta de espacio
        if(altura < 5){
        g2.fillOval(this.gx + ((minX + maxX) / 2) - 15,
                this.gy + (y + Saltoy / 2) - 20, 30, 30);
        g2.setColor(Color.white);
        }
        g2.drawString(valor, this.gx + ((minX + maxX) / 2 - width / 2),
                this.gy + (y + Saltoy / 2));
        g2.setColor(Color.black);
        if (nodo.getIzquierda() != null) {
            g2.drawLine(this.gx + ((minX + maxX) / 2 - xSep),
                    this.gy + (y + Saltoy / 2 + 5),
                    this.gx + ((minX + (minX + maxX) / 2) / 2),
                    this.gy + (y + Saltoy + Saltoy / 2 - height));
            dibujoRecursivo(g2, minX, (minX + maxX) / 2, y + Saltoy, Saltoy, altura+1, nodo.getIzquierda());
        }
        if (nodo.getDerecha() != null) {
            g2.drawLine(this.gx + ((minX + maxX) / 2 + xSep),
                    this.gy + (y + Saltoy / 2 + 5),
                    this.gx + ((maxX + (minX + maxX) / 2) / 2),
                    this.gy + (y + Saltoy + Saltoy / 2 - height));
            dibujoRecursivo(g2, (minX + maxX) / 2, maxX, y + Saltoy, Saltoy, altura+1, nodo.getDerecha());
        }
    }

    @Override
    protected void paintComponent(Graphics g2d) {
        super.paintComponent(g2d);

        Graphics2D g2 = (Graphics2D) g2d;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setBackground(Color.white);

        int width = getWidth();
        int height = getHeight();

        double zoomWidth = width * this.zoom;
        double zoomHeight = height * this.zoom;

        double anchorx = (width - zoomWidth) / 2;
        double anchory = (height - zoomHeight) / 2;

        g2.translate(anchorx, anchory);
        g2.scale(this.zoom, this.zoom);
        g2.translate(-100, -100);

 
        if(this.arbol.getRaiz() != null){
        int altura = ABB.getAltura(this.arbol.getRaiz());
g2.setFont(new Font("Roman", 3, 14));
g2.setColor(Color.red);
g2.drawString(this.lista,this.gx,this.gy-20);
        dibujoRecursivo(g2, 0, width, 0, height / (altura + 1), 0, this.arbol.getRaiz());
        repaint();
    }
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {

        if (e.getPreciseWheelRotation() < 0) {
            this.zoom += 0.1;
        } else {
            this.zoom -= 0.1;
        }
//                  zoom += e.getPreciseWheelRotation();
        if (this.zoom < 0.01) {
            this.zoom = 0.01;
        }

        repaint();
    }

    private class Adaptador_Mouse extends MouseAdapter {

        int previousX;
        int previousY;

        @Override
        public void mousePressed(MouseEvent e) {
            this.previousX = e.getX();
            this.previousY = e.getY();
            gx = this.previousX;
            gy = this.previousY;
            repaint();
        }

        @Override
        public void mouseDragged(MouseEvent e) {

            int y = e.getY();
            int x = e.getX();
            if (x < this.previousX) {
                gx -= 5;
            } else if (x > this.previousX) {
                gx += 5;
            }
            if (y < this.previousY) {
                gy -= 5;
            } else if (y > this.previousY) {
                gy += 5;
            }
            this.previousX = x;
            this.previousY = y;
            repaint();
        }
    }

}
