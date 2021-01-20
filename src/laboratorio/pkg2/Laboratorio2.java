/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laboratorio.pkg2;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import laboratorio.pkg2.vista.Arbol;

/**
 *
 * @author JAIVE
 */
public class Laboratorio2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        try {
            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
            UIManager.put("RootPane.setupButtonVisible", false);
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.getMessage(), "Error", 0);
        }
        Arbol ventana = new Arbol();
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);

    }

}
