/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.eventogo;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import java.util.Random;

public class cGestion {
    private cUsuario[] usuariosActivos = new cUsuario[200];
    private int cantidadUsuarios = 0;
    private cEvento[] eventosActivos = new cEvento[50];
    private int cantidadEventos = 0;
    private cUsuario usuarioEntrada;
    private cEntrada[] entradas = new cEntrada [500];
    private int contadorEntradas = 0;
    
    
      public void registarUsuario() {
        if (cantidadUsuarios < 200) {
            String cedulaUsuario = JOptionPane.showInputDialog("Ingrese su numero de cedula");
            String nombreUsuario = JOptionPane.showInputDialog("Ingrese su nombre");
            String correoUsuario = JOptionPane.showInputDialog("Ingrese su correo electronico");
            
            // Validar que el correo tenga @ y que no esté vacío
            
            if(!correoUsuario.contains("@")|| correoUsuario.contains(" ")){
            JOptionPane.showMessageDialog(null,"No es una cuenta de correo válida, intente de nuevo.");
            return;
            }

            // Validar que el ID no esté repetido
            boolean idRepetido = false;
            for (int i = 0; i < cantidadUsuarios; i++) {
                if (("USR-" + cedulaUsuario).equals(usuariosActivos[i].getIDUsuario())) {
                    idRepetido = true;
                    break;
                }
            }

            if (idRepetido == true) {
                JOptionPane.showMessageDialog(null, "El número de cédula ya está registrado.");
                return;
            }

            // Validar que el rol sea 1 o 2 y no otro numero
            int rolUsuario;
            do {
                rolUsuario = Integer.parseInt(JOptionPane.showInputDialog("Ingrese su rol \n1. Usuario \n2. Administrador"));
            } while (rolUsuario != 1 && rolUsuario != 2);

            // Registrar el usuario
            cUsuario usuario = new cUsuario(cedulaUsuario, nombreUsuario, correoUsuario, rolUsuario);
            usuariosActivos[cantidadUsuarios] = usuario;
            cantidadUsuarios++;

            JOptionPane.showMessageDialog(null, "El usuario ha sido registrado con éxito. ID: " + usuario.getIDUsuario());
        }else{
            JOptionPane.showMessageDialog(null, "No se pueden registrar más usuarios. Límite alcanzado.");
        }
    }

    public int iniciarSesion() {
        String idUsuario = JOptionPane.showInputDialog("Ingrese su numero de cedula");
        String correo = JOptionPane.showInputDialog("Ingrese su correo electronico");
        
        if(idUsuario == null || correo == null){
            JOptionPane.showMessageDialog(null, "Inicio de sesión cancelado por el usuario.");
            return -1;
        }
        
        String newidUsuario = "USR-" + idUsuario;

        
        for (int i = 0; i < cantidadUsuarios; i++) {
            if (newidUsuario.equals(usuariosActivos[i].getIDUsuario()) && correo.equals(usuariosActivos[i].getCorreoUsuario())) {
                JOptionPane.showMessageDialog(null, "Inicio de sesión exitoso.");
                usuarioEntrada = usuariosActivos[i];
                return usuariosActivos[i].getRol(); 
            }
        }
        
        JOptionPane.showMessageDialog(null, "El usuario no existe. Por favor regístrese.");
        return -1;
    }
    
    public void registrarEvento(){
        if (cantidadEventos < 50) {
           char[][] codigosEventos = generarCodigoEvento();
            String IDEvento = new String(codigosEventos[cantidadEventos]);
            String nombreEvento = JOptionPane.showInputDialog("Ingrese el nombre del evento");
            String ubicacionEvento = JOptionPane.showInputDialog("Ingrese la ubicacion");
            String fechaEvento = JOptionPane.showInputDialog("Ingrese la fecha del evento");
            String tipoEvento = JOptionPane.showInputDialog("Ingrese el tipo de evento");
            int capacidadMaximaEvento = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la capacidad maxima"));
            cEvento evento = new cEvento(IDEvento, nombreEvento, ubicacionEvento, fechaEvento, tipoEvento,capacidadMaximaEvento);
            eventosActivos[cantidadEventos] = evento;
            cantidadEventos++;
            JOptionPane.showMessageDialog(null, "El evento ha sido registrado con éxito. ID: " + evento.getIDEvento());
        }else{
            JOptionPane.showMessageDialog(null, "No se pueden registrar más eventos. Límite alcanzado.");
        }
    }
    
    public void eliminarEvento() { 
            List<String> eventosSeleccion = new ArrayList<>();
            int count = 0;
            for (cEvento evento : eventosActivos){
                if(evento != null){
                  eventosSeleccion.add(eventosActivos[count].getNombreEvento());
                }
                count++;
            } 

            String seleccion = mostrarDropDownEventos(eventosSeleccion, "Seleccione el evento que desea eliminar:");

            if (cantidadEventos > 0) {


            cEvento eventoSeleccionado = null;
            if (seleccion != null) {
                for (int j = 0; j < eventosSeleccion.size(); j++) {
                    if (eventosSeleccion.get(j).equals(seleccion)) {
                        eventoSeleccionado = eventosActivos[j];
                        break;
                    }
                }
            }

            // Si no hay eventos registrados, salir del método
            if (cantidadEventos == 0){
                return;
            } 


            for (int i = 0; i < cantidadEventos; i++) {
                if (eventosActivos[i].getIDEvento().equals(eventoSeleccionado.getIDEvento())) { //Verificar entrada
                    if (eventosActivos[i].paraEliminar()){ //Verificar eventos sin entradas vendidas
                        for (int j = i; j < cantidadEventos - 1; j++) {
                            eventosActivos[j] = eventosActivos[j + 1];
                        }
                        // Limpia el último espacio que quedó duplicado
                        eventosActivos[cantidadEventos - 1] = null;
                        // Disminuye el contador total de eventos
                        cantidadEventos--;
                        JOptionPane.showMessageDialog(null, "Evento eliminado correctamente");
                        return;
                    }else{
                    // Si tiene entradas vendidas, no se puede eliminar
                    JOptionPane.showMessageDialog(null, "No se puede eliminar, este evento ya tiene entradas vendidas");
                    return;
                    }
                }
            } JOptionPane.showMessageDialog(null, "No se encontró el evento con ID: " + eventoSeleccionado.getIDEvento());
        }
    }
    
public void mostrarListaEventos() {
        if (cantidadEventos == 0) {
            JOptionPane.showMessageDialog(null, "No hay eventos registrados.");
            return;
        }
        String listaEventos = "Eventos registrados:\n";
        for (int i = 0; i < cantidadEventos; i++) {
            listaEventos += (i + 1) + ". " + eventosActivos[i].getIDEvento() + " - " + eventosActivos[i].getNombreEvento() + "\n";
        }
        JOptionPane.showMessageDialog(null, listaEventos);
    }

    public String mostrarDropDownEventos( List<String> eventosSeleccion, String mensaje) {
        String seleccion = "";
        if (cantidadEventos == 0) {
            JOptionPane.showMessageDialog(null, "No hay eventos registrados.");
            return seleccion;
        }
       
            seleccion = (String) JOptionPane.showInputDialog(
            null,
            mensaje,
            "Selección de Evento",
            JOptionPane.QUESTION_MESSAGE,
            null,
            eventosSeleccion.toArray(),
            eventosSeleccion.getFirst()
            );
            
       return seleccion;
    }
    
    public void comprarEntrada(){ 

        List<String> eventosSeleccion = new ArrayList<>();
        int count = 0;
            for (cEvento evento : eventosActivos){
                if(evento != null){
                  eventosSeleccion.add(eventosActivos[count].getNombreEvento());
                }
                count++;
            } 
            
        String seleccion = mostrarDropDownEventos(eventosSeleccion, "Seleccione el evento:");

        if (cantidadEventos > 0) {
            cEvento eventoSeleccionado = null;
            if (seleccion != null) {
                for (int j = 0; j < eventosSeleccion.size(); j++) {
                    if (eventosSeleccion.get(j).equals(seleccion)) {
                        eventoSeleccionado = eventosActivos[j];
                        break;
                    }
                }
            }
            int cantidadEntradas = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad de entradas (máximo 5):"));
            // Validar cantidad máxima
            if (cantidadEntradas > 5 || cantidadEntradas <= 0) {
                JOptionPane.showMessageDialog(null, "Cantidad no permitida.");
                return;
            }
                // Validacion de disponibilidad y venta
            if (eventoSeleccionado.venderEntradas(cantidadEntradas)){
                for (int i = 0; i < cantidadEntradas; i++){    
                     char[][] codigosEntradas = generarCodigoEntrada();
                     String IDEntrada = new String(codigosEntradas[contadorEntradas]); // Se utiliza contadorEntradas como índice
                     cEntrada entrada = new cEntrada(IDEntrada, usuarioEntrada, eventoSeleccionado);
                     entradas[contadorEntradas] = entrada;
                     contadorEntradas++;
                    JOptionPane.showMessageDialog(null, "TKT-" + IDEntrada + "|" + usuarioEntrada.getIDUsuario() + "|" + eventoSeleccionado.getNombreEvento());
                    }
                    } else {
                    JOptionPane.showMessageDialog(null, "No hay suficientes entradas disponibles para efectuar la compra");                  
                    
                }                              
            }      
    }
    
    public cEntrada[] getEntradas() {
        return this.entradas;
    }

    public int getTotalEntradas() {
        return this.contadorEntradas;
    }
    
    private char[][] generarCodigoEvento(){
        String caracteres = "1234567890";
        char[][] codigos = new char[50][3]; // Almacenar 50 códigos de 3 caracteres
        Random random = new Random();
                
        for (int contador = 0; contador < 50; contador++) {
            char[] codigo = new char[3];
            for (int i = 0; i < 3; i++) {
                codigo[i] = caracteres.charAt(random.nextInt(caracteres.length()));
            }
            codigos[contador] = codigo; // Almacenar el código generado
            }
            return codigos;
    }   
    
    private char[][] generarCodigoEntrada(){
        String caracteres = "1234567890";
        char[][] codigos = new char[500][3]; 
        Random random = new Random();
                
        for (int contador = 0; contador < 50; contador++) {
            char[] codigo = new char[3];
            for (int i = 0; i < 3; i++) {
                codigo[i] = caracteres.charAt(random.nextInt(caracteres.length()));
            }
            codigos[contador] = codigo; // Almacenar el código generado
            }
            return codigos;
    }   
    
    public  String mostrarProximosEventos(){
        if (contadorEntradas == 0) {
            JOptionPane.showMessageDialog(null, "No hay entradas compradas aún.");
            return "";
        }
        
        String proximosEventos = "Proximos eventos:\n";
        for (int i = 0; i < contadorEntradas; i++) {
            proximosEventos += (i + 1) + ". Evento: " + entradas[i].getEventoEntrada().getNombreEvento() + 
                                 ", Fecha: " + entradas[i].getEventoEntrada().getFechaEvento() + 
                                 ", Código de entrada: " + entradas[i].getIDEntrada() + "\n";
        }
        JOptionPane.showMessageDialog(null, proximosEventos);
        
        return proximosEventos;
    }
    public void generarReporteAsistentesPorEvento() {
        if(cantidadEventos == 0){
            JOptionPane.showMessageDialog(null, "No existen eventos registrados.");
            return;
        }

        StringBuilder reporteAsistentes = new StringBuilder(" _____ ASISTENTES POR EVENTO ______\n");
        for (cEvento evento : eventosActivos) {
            if (evento == null) continue;
            reporteAsistentes.append("\nEvento: ").append(evento.getNombreEvento()).append("(").append(evento.getIDEvento()).append(")\n");
        int contadorAsistentes = 0;
        for (cEntrada entrada : entradas) {
            if (entrada !=null && entrada.getEventoEntrada().equals(evento)){
                contadorAsistentes++;
                reporteAsistentes.append(" - ").append(entrada.getUsuarioEntrada().getNombreUsuario()).append(" (ID: ").append(entrada.getUsuarioEntrada().getIDUsuario()).append (")\n");                
            }
        }
        reporteAsistentes.append("Total de entradas vendidas: ").append(contadorAsistentes).append("\n");
        }
        JOptionPane.showMessageDialog(null, reporteAsistentes.toString());
    }
    
    public void generarReporteVentas(){
        if (cantidadEventos == 0){
            JOptionPane.showMessageDialog(null, "No existen eventos registrados.");
            return;
        }
        StringBuilder reporte = new StringBuilder (" ______ REPORTE DE VENTAS ______\n");
        for (cEvento evento : eventosActivos) {
            if (evento == null) continue;
            
            int entradasVendidas = 0;
            for (cEntrada entrada : entradas) {
                if(entrada != null && entrada.getEventoEntrada().equals(evento)) {
                    entradasVendidas++;
                }
            }
            reporte.append("\nEvento: ").append(evento.getNombreEvento()).append("\nEntradas vendidas: ").append(entradasVendidas).append(" / ").append(evento.getCapacidadMaxima()).append("\nDisponibles: ").append(evento.getentradasDisponibles()).append("\n");            
        }
        JOptionPane.showMessageDialog(null, reporte.toString());
    }
    public void generarReporteEventosDisponibles(){
        if (cantidadEventos == 0) {
        JOptionPane.showMessageDialog(null, "No existen eventos registrados.");
        return;
    }
        StringBuilder reporteEventos = new StringBuilder (" ______ REPORTE DE EVENTOS ______\n");
        for (cEvento evento : eventosActivos) {
            if (evento == null) continue;
            reporteEventos.append("\nEvento: ").append(evento.getNombreEvento()).append(" (").append(evento.getIDEvento()).append(")\n");
            reporteEventos.append("\nFecha del Evento: ").append(evento.getFechaEvento()).append("\nTipo del evento: ").append(evento.getTipoEvento()).append("\nUbicación del evento: ").append(evento.getUbicacionEvento());
            int entradasVendidas = 0;
            for (cEntrada entrada : entradas) {
                if (entrada != null && entrada.getEventoEntrada().equals(evento)) {
                    entradasVendidas++;
                }
        }
        reporteEventos.append("\nEvento: ").append(evento.getNombreEvento()).append("\n");

    }
        JOptionPane.showMessageDialog(null, reporteEventos.toString());
}    
}
