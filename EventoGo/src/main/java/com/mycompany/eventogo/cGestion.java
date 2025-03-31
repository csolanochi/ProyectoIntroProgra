/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.eventogo;
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
            String fechaEvento = JOptionPane.showInputDialog("Ingrese su la fecha del evento");
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
    
    public void mostrarListaEventos() {
        if (cantidadEventos == 0) {
            JOptionPane.showMessageDialog(null, "No hay eventos registrados.");
            return;
        }
        String listaEventos = "Eventos registrados:\n";
        for (int i = 0; i < cantidadEventos; i++) {
            listaEventos += (i + 1) + ". " + eventosActivos[i].getNombreEvento() + "\n";
        }
        JOptionPane.showMessageDialog(null, listaEventos);
    }
    
    public void comprarEntrada(){ 

        mostrarListaEventos();

        if (cantidadEventos > 0) {
            int seleccionEvento = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número del evento"));
            cEvento eventoSeleccionado = eventosActivos[seleccionEvento - 1]; 
            int cantidadEntradas = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad de entradas (máximo 5):"));
            // Validar cantidad máxima
            if (cantidadEntradas > 5 || cantidadEntradas <= 0) {
                JOptionPane.showMessageDialog(null, "Cantidad no permitida.");
                return;
            }else{
                for (int i = 0; i < cantidadEntradas; i++){
                    char[][] codigosEntradas = generarCodigoEntrada();
                    String IDEntrada = new String(codigosEntradas[cantidadEntradas]);
                    cEntrada entrada = new cEntrada(IDEntrada, usuarioEntrada, eventoSeleccionado);
                    JOptionPane.showMessageDialog(null, "TKT-" + IDEntrada + "|" + usuarioEntrada.getIDUsuario() + "|" + eventoSeleccionado.getNombreEvento());
                }
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
       
}

    
