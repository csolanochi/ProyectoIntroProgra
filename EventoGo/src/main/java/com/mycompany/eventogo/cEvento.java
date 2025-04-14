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

public class cEvento {
    private String IDEvento;
    private String nombre;
    private String ubicacion;
    private String fecha;
    private String tipoEvento;
    private int capacidadMaxima;
    private int entradasVendidas = 0;
    
    // Constructor
    
    public cEvento(String pIDEvento, String pNombre, String pubicacion, String pfecha, String ptipoEvento, int pcapacidadMaxima){
        this.IDEvento = "EVT-" + pIDEvento;
        this.nombre = pNombre;
        this.ubicacion = pubicacion;
        this.fecha = pfecha;
        this.tipoEvento = ptipoEvento;
        this.capacidadMaxima = pcapacidadMaxima;
    }
    
    // Getters
    
    public String getIDEvento() {
        return this.IDEvento;
    }
    
    public String getNombreEvento() {
        return this.nombre;
    }
     
    public String getUbicacionEvento() {
        return this.ubicacion;
    }       
    
    public String getFechaEvento() {
        return this.fecha;
    }       
    
    public String getTipoEvento() {
        return this.tipoEvento;
    }       
   
    public int getCapacidadMaxima(){
        return this.capacidadMaxima;
    }
    
    public int getentradasDisponibles() {
        return this.capacidadMaxima - entradasVendidas;
        
    }
    
    // Metodos
    
    public boolean hayDisponibilidad() {
        return entradasVendidas < capacidadMaxima;       
    }
    
    public boolean venderEntradas(int cantidad) {
        if (entradasVendidas + cantidad <= capacidadMaxima){
            entradasVendidas += cantidad;
            return true; // Venta exitosa
        }
        return false; // No hay disponibilidad
        }
    
    public boolean paraEliminar(){
        return this.entradasVendidas == 0;
    }
    }