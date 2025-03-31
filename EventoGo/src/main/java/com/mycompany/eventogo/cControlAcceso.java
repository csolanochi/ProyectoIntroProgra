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
    private cEntrada[] entradas;
    private int totalEntradas;

    public cControlAcceso(cEntrada[] entradas, int totalEntradas) {
        this.entradas = entradas;
        this.totalEntradas = totalEntradas;
    }

    public void validarEntrada(String codigoEntrada) {
        for (int i = 0; i < totalEntradas; i++) {
            if (entradas[i] != null && entradas[i].getIDEntrada().equals(codigoEntrada)) {
                if (entradas[i].validacionEntrada()) {
                    entradas[i].marcarUtilizada();
                    JOptionPane.showMessageDialog(null, "El acceso es permitido: " + 
                        entradas[i].getEventoEntrada().getNombreEvento());
                } else {
                    JOptionPane.showMessageDialog(null, "Esta entrada ya ha sido utilizada.");
                }
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "El código de entrada es inexistente.");
    }
   
}
