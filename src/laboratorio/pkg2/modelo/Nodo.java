/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laboratorio.pkg2.modelo;

/**
 *
 * @author JAIVE
 */
public class Nodo {

    private Nodo izquierda;
    private Nodo derecha;
    private Nodo padre;
    private int valor;

    public Nodo(int valor) {
        this.valor = valor;
    }

    public int contar() {
        int num = 1;
        if (izquierda != null) {
            num += izquierda.contar();
        }
        if (derecha != null) {
            num += derecha.contar();
        }
        return num;
    }

    public Nodo getIzquierda() {
        return izquierda;
    }

    public void setIzquierda(Nodo izquierda) {
        this.izquierda = izquierda;
    }

    public Nodo getDerecha() {
        return derecha;
    }

    public void setDerecha(Nodo derecha) {
        this.derecha = derecha;
    }

    public Nodo getPadre() {
        return padre;
    }

    public void setPadre(Nodo padre) {
        this.padre = padre;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

}
