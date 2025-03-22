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
    private cEntrada listaEntradas;
    
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
    
    public int getRol(){
        return this.rol;
    }
    
    public cEntrada getEventosComprados() {
        return this.listaEntradas;
    }
    
    public void agregarEntrada() {
      
    }
    
}
