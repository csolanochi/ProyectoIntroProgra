/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.eventogo;
import javax.swing.JOptionPane;

/**
 *
 * @author carolinasolano
 */
public class cControlAcceso {
     private cGestion gestion;
     // Constructor
     
     public cContolAcceso(cGestion gestion) {
         this.gestion = gestion;
     }
     
    public void validacionEntrada(codigoEntrada) {
        for (cEntrada entrada : gestion.) {
            if (entrada.esValidad()) {
                entrada.marcarUtilizada();
                JOptionPane.showMessageDialog(null, "Acceso permitido.");
            } else {
                JOptionPane.showMessageDialog(null, "La entrada ya ha sido utilizada.");
            }
            return;
        }
        JOptionPane.showMessageDialog(null, "La entrada no se encuentra en el sistema.");    

    }
}


  