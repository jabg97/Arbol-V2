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
public class ABB {

    private Nodo raiz;

    public ABB() {
        this.raiz = null;
    }

    public ABB(Nodo raiz) {
        this.raiz = raiz;
    }

    public Nodo getRaiz() {
        return raiz;
    }

    private void setRaiz(Nodo raiz) {
        this.raiz = raiz;
    }

    public static int getAltura(Nodo nodo) {
        if (nodo == null) {
            return -1;
        }
        return Math.max(getAltura(nodo.getIzquierda()), getAltura(nodo.getDerecha())) + 1;
    }

    public int contar() {
        return this.raiz.contar();
    }

    public Nodo buscar(int buscar) throws Exception {

        Nodo nodo = this.raiz;
        while (nodo != null && buscar != nodo.getValor()) {

            if (buscar < nodo.getValor()) {
                nodo = nodo.getIzquierda();
            } else {
                nodo = nodo.getDerecha();
            }
        }

        if (nodo == null) {
            throw new Exception("No existe el nodo");
        }
        return nodo;
    }

    public void añadir(int valor) {
        Nodo nodo = new Nodo(valor);
        Nodo nodo2 = null;
        Nodo temporal = this.raiz;

        //Se hace una busqueda desde la raiz,
        //para encontrar el lugar del nuevo nodo
        while (temporal != null) {

            nodo2 = temporal;

            if (valor <= temporal.getValor()) {

                temporal = temporal.getIzquierda();
            } else {

                temporal = temporal.getDerecha();
            }
        }
//se asigna el padre del nuevo nodo
        nodo.setPadre(nodo2);

        if (this.raiz == null) {
//si es el primero se asigna como raiz
            this.raiz = nodo;
        } else if (nodo.getValor() <= nodo2.getValor()) {
//sino se valida si es menor al nodo padre para ponerlo a la izquierda
            nodo2.setIzquierda(nodo);
        } else {
//sino entonces es mayor y se pone a la derecha
            nodo2.setDerecha(nodo);
        }
    }

    public void inorden(Nodo nodo) {
        if (nodo != null) {
            inorden(nodo.getIzquierda());
            System.out.println(nodo.getValor());
            inorden(nodo.getDerecha());
        }
    }

    public void preorden(Nodo nodo) {
        if (nodo != null) {
            System.out.println(nodo.getValor());
            preorden(nodo.getIzquierda());
            preorden(nodo.getDerecha());
        }
    }

    public void postorden(Nodo nodo) {
        if (nodo != null) {
            postorden(nodo.getIzquierda());
            postorden(nodo.getDerecha());
            System.out.println(nodo.getValor());
        }
    }

    private Nodo nodoAnterior(Nodo nodo) {

        if (nodo.getIzquierda() != null) {
            nodo = nodo.getIzquierda();
            //Se busca el nodo maximo o que este mas a la derecha
            while (nodo.getDerecha() != null) {

                nodo = nodo.getDerecha();
            }

            return nodo;
        }

        Nodo padre = nodo.getPadre();

        while (padre != null && padre.getValor() > nodo.getValor()) {

            padre = padre.getPadre();
        }

        return padre;
    }

    private void reemplazar(Nodo nodo, Nodo nodo2) {

        if (nodo.getPadre() == null) {

            this.raiz = nodo2;
        } else if (nodo.getPadre().getIzquierda() == nodo) {

            nodo.getPadre().setIzquierda(nodo2);
        } else {

            nodo.getPadre().setDerecha(nodo2);
        }

        if (nodo2 != null) {

            nodo2.setPadre(nodo.getPadre());
        }
    }

    public void eliminar(int buscar) throws Exception {

        Nodo borrar = buscar(buscar);

        if (borrar.getIzquierda() == null) {
            //si tiene hijos a la izquierda se reemplaza al otro lado
            reemplazar(borrar, borrar.getDerecha());
        } else if (borrar.getDerecha() == null) {
            //si tiene hijos a la derecha se reemplaza al otro lado
            reemplazar(borrar, borrar.getIzquierda());
        } else {

            //se busca el nodo anterior y se cambia el valor
            Nodo anterior = nodoAnterior(borrar);
            int temporal = borrar.getValor();
            borrar.setValor(anterior.getValor());
            anterior.setValor(temporal);

            //se obtiene el hijo del nodo anterior, para arreglar los punteros
            Nodo hijo = anterior.getIzquierda();
            if (anterior.getPadre().getIzquierda() == anterior) {

                anterior.getPadre().setIzquierda(hijo);
            } else {

                anterior.getPadre().setDerecha(hijo);
            }
        }
    }

}
