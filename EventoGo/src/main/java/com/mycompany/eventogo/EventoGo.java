/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.eventogo;
import javax.swing.JOptionPane;

public class EventoGo {
 
    public static void main(String[] args) {
        cGestion gestion = new cGestion();
        
        int opcion;
        do {
            opcion = Integer.parseInt(JOptionPane.showInputDialog(
                "Bienvenido(a) a EventosGo, seleccione una opción para continuar:\n" +
                "1. Registrar usuario\n" +
                "2. Iniciar sesión\n" +
                "3. Salir\n"
            ));
            switch (opcion) {
                case 1:
                    gestion.registarUsuario();
                    break;
                case 2:
                    int rol = gestion.iniciarSesion();
                    if (rol != -1) {
                        //Acciones para el usuario/comprador
                        if (rol == 1) {
                            int opcionUsuario;
                            do {
                                opcionUsuario = Integer.parseInt(JOptionPane.showInputDialog(
                                    "Seleccione una opción para continuar:\n" +
                                    "1. Ver eventos disponibles\n" +
                                    "2. Comprar entradas\n" +
                                    "3. Ver mis próximos eventos\n" +
                                    "4. Salir\n"
                                ));
                                switch (opcionUsuario) {
                                    case 1:      
                                        gestion.mostrarListaEventos();
                                        break;
                                    case 2:
                                        gestion.comprarEntrada();//pendiente
                                        break;
                                    case 3:
                                        String proximosEventos = gestion.mostrarProximosEventos(); 
                                        break;
                                    case 4: 
                                        break;
                               }
                            }while (opcionUsuario != 4);
                        //Acciones para el administrador    
                        } else if (rol == 2) {
                          int opcionAdmin;
                            do {
                                opcionAdmin = Integer.parseInt(JOptionPane.showInputDialog(
                                    "Seleccione una opción para continuar:\n" +
                                    "1. Registrar evento\n" +
                                    "2. Eliminar evento\n" +       
                                    "3. Verificar entrada\n" +
                                    "4. Módulo de reportes\n" +
                                    "5. Salir\n"
                                ));
                                switch (opcionAdmin) {
                                    case 1:
                                        gestion.registrarEvento();
                                        break;
                                    case 2:
                                        gestion.eliminarEvento();
                                        break;
                                    case 3:
                                        String codigoEntrada = JOptionPane.showInputDialog("Ingrese código de entrada (TKT-###):");
                                        cControlAcceso control = new cControlAcceso(gestion.getEntradas(), gestion.getTotalEntradas());
                                        control.validarEntrada(codigoEntrada);    
                                        break;
                                    case 4:
                                        int opcionReporte;
                                        do {
                                            opcionReporte = Integer.parseInt(JOptionPane.showInputDialog(
                                            "MÓDULO DE REPORTES\n" +
                                            "1. Eventos disponibles\n" +
                                            "2. Asistentes por evento\n" +
                                            "3. Reporte de ventas \n" +
                                            "4. Volver al módulo de administrador\n"
                                            ));
                                            switch (opcionReporte){
                                                case 1: // REPORTE DE EVENTOS DISPONIBLES
                                                    gestion.generarReporteEventosDisponibles();
                                                    break;
                                                case 2: // REPORTE DE ASISTENTES
                                                    gestion.generarReporteAsistentesPorEvento();
                                                    break;
                                                case 3: // REPORTE DE VENTAS
                                                    gestion.generarReporteVentas();
                                                    break;
                                                case 4: // VOLVER AL MENÚ
                                                    break;
                                            }
                                        }while (opcionReporte != 4);
                                        break;
                                      case 5: // SALIR DEL MENÚ DEL ADMINISTRADOR
                                          break;
                               }
                            }while (opcionAdmin != 5);
                        }
                    }
                    break;
                case 3: // SALIR DEL SISTEMA
                    break;
                default: // OPCION INVALIDA
                    JOptionPane.showMessageDialog(null, "Opción no válida. Intente de nuevo.");
                    break;
            }
        } while (opcion != 3); // REPETIR HASTA QUE EL USUARIO ELIJA SALIR
    }
}