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
public class cUsuario {
    private String IDUsuario;
    private String nombre;
    private String correoElectronico;
    private int rol;
    private cEntrada[] entradas = new cEntrada[5];
    private int contadorEntradas = 0;
    
    public cUsuario(String pIDUsuario, String pNombre, String pcorreoElectronico, int prol){
        this.IDUsuario = "USR-" + pIDUsuario;
        this.nombre = pNombre;
        this.correoElectronico = pcorreoElectronico;
        this.rol = prol;
    }
    
    public String getIDUsuario() {
        return this.IDUsuario;
    }
    
    public String getNombreUsuario() {
        return this.nombre;
    }
    
    public String getCorreoUsuario() {
        return this.correoElectronico;
    }
    
    // public void setCorreoUsuario(String correoElectronico) {
       // if(!correoElectronico.contains("@")|| correoElectronico.contains(" ")){
         //   JOptionPane.showMessageDialog(null,"No es una cuenta de correo válida, intente de nuevo.");
        //} else {
         //   this.correoElectronico = correoElectronico;
           
    public int getRol(){
        return this.rol;
    }
    // Validación de límite de entradas
    public boolean agregarEntrada(cEntrada entrada) {
      if (contadorEntradas < 5) {
            entradas[contadorEntradas] = entrada;
            contadorEntradas++;
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "El límite es de 5 entradas por usuario.");
            return false;
        }
    
    }
}