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
     
    enum ESTADO{
    ACTIVO,
    INACTIVO,
    }
    
    private String IDEntrada;
    private cUsuario usuario;
    private cEvento evento;
    private ESTADO estado;
    
    public cEntrada(String pIDEntrada, cUsuario pusuario, cEvento pevento){
        this.IDEntrada = "TKT-" + pIDEntrada;
        this.usuario = pusuario;
        this.evento = pevento;
        this.estado = ESTADO.ACTIVO;
    }
    
    public String getIDEntrada() {
        return this.IDEntrada;
    }
    
    public cUsuario getUsuarioEntrada() {
        return this.usuario;
    }
    
    public cEvento getEventoEntrada() {
        return this.evento;
    }
    
    public void actualizarEstado(ESTADO nuevoEstado) {
        this.estado = nuevoEstado;
    }
}
