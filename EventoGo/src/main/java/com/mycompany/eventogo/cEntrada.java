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
public class cEntrada {
     
    public enum ESTADO{
    ACTIVO,
    INACTIVO,
    }
    
    private String IDEntrada;
    private cUsuario usuario;
    private cEvento evento;
    private ESTADO estado;
    
    // Constructor
    
    public cEntrada(String pIDEntrada, cUsuario pusuario, cEvento pevento){
        this.IDEntrada = "TKT-" + pIDEntrada;
        this.usuario = pusuario;
        this.evento = pevento;
        this.estado = ESTADO.ACTIVO;
    }
    
    // Getters
    
    public String getIDEntrada() {
        return this.IDEntrada;
    }
    
    public cUsuario getUsuarioEntrada() {
        return this.usuario;
    }
    
    public cEvento getEventoEntrada() {
        return this.evento;
    }
    
    // Métodos
    
    public void actualizarEstado(ESTADO nuevoEstado) {
        this.estado = nuevoEstado;
    }
    
    public boolean esValidad() {
        return estado == ESTADO.ACTIVO;       
    }
    
    public void marcarUtilizada() {
        this.estado = ESTADO.INACTIVO;
    }
}
