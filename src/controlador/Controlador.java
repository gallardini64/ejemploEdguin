/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author sistemas
 */
import vista.*;
import modelo.*;

public class Controlador implements ActionListener {
    private VistaPrincipal vista;
    private VistaCliente vista1;
    private VistaListaClientes vista2;
    private VistaClienteBuscar vista3;
    private VistaClienteModificar vista4;
    private Cliente modelo;
    private Conexion conexion;
    
    public Controlador(Conexion conexion){
        vista = new VistaPrincipal();
        this.conexion=conexion;
    }
    public void ejecutar(){
        vista.setControlador(this);
        vista.ejecutar();
    }
    public void actionPerformed(ActionEvent evento ){
        if(evento.getActionCommand().equals(vista.MENU_CLIENTES)){
            vista1 = new VistaCliente(null, true);
            vista1.setControlador(this);
            vista1.ejecutar();
        }
        if(evento.getActionCommand().equals(vista.MENU_LISTA_CLIENTES)){
            vista2 = new VistaListaClientes(null, true);
            vista2.setControlador(this);
            ClienteDAO clientes = new ClienteDAO(modelo, conexion);
            ArrayList<String[]> lista = new ArrayList<String[]>();
            for(Cliente cli : clientes.leer() ){
                String linea[] = new String[4];
                linea[0] = ""+cli.getDni(); 
                linea[1] = cli.getApellido();
                linea[2] = cli.getNombre();
                linea[3] = cli.getTelefono();
                lista.add(linea);
            }
            vista2.cargarLista(lista);
            vista2.ejecutar();            
        }
        if(evento.getActionCommand().equals(vista.MENU_BUSCAR_CLIENTES)){
            vista3 = new VistaClienteBuscar(null, true);
            vista3.setControlador(this);
            vista3.ejecutar();
        }
        if(evento.getActionCommand().equals(vista.MENU_MODIFICAR_DATOS)){
            vista4 = new VistaClienteModificar(null, true);
            vista4.setControlador(this);
            vista4.ejecutar();
        }
        if(evento.getActionCommand().equals(VistaCliente.BTN_ACEPTAR)){
            modelo =  new Cliente(1, vista1.getDni(),vista1.getNombre(), vista1.getApellido());
            modelo.setDomicilio(vista1.getDomicilio());
            modelo.setTelefono(vista1.getTelefono());
            modelo.setLimite(vista1.getLimite());
            
            JOptionPane.showMessageDialog(null, modelo);
            
            ClienteDAO cliente = new ClienteDAO(modelo,conexion);
            cliente.agregar();
        }
        if(evento.getActionCommand().equals(vista3.BTN_BUSCAR)){
            modelo =  new Cliente(1, vista3.getDni(),"","");
            ClienteDAO cli = new ClienteDAO(modelo, conexion);
            Cliente tmp = cli.buscar();
            if( tmp != null){
                vista3.setApellido(tmp.getApellido());
                vista3.setNombre(tmp.getNombre());
                vista3.setDomicilio(tmp.getDomicilio());
                vista3.setTelefono(tmp.getTelefono());
                vista3.setLimite(tmp.getLimite());
                JOptionPane.showMessageDialog(null,"Encontrado");
            }
        }
//*******************************************************************
//      a modo de ejemplo: Se repite el codigo para buscar y luego poder
//      actualizar los datos del cliente
//*******************************************************************
        if(evento.getActionCommand().equals(vista4.BTN_BUSCAR_MODIFICAR)){
            modelo =  new Cliente(1, vista4.getDni(),"","");
            ClienteDAO cli = new ClienteDAO(modelo, conexion);
            Cliente tmp = cli.buscar();
            if( tmp != null){
                vista4.setApellido(tmp.getApellido());
                vista4.setNombre(tmp.getNombre());
                vista4.setDomicilio(tmp.getDomicilio());
                vista4.setTelefono(tmp.getTelefono());
                vista4.setLimite(tmp.getLimite());
                JOptionPane.showMessageDialog(null,"Encontrado");
            }
        }
//*******************************************************************
//      Aqui se actualizam los datos en la tabla clientes
//*******************************************************************
        if(evento.getActionCommand().equals(vista4.BTN_ACTUALIZAR_DATOS)){
            // a modo de ejemplo se actualiza el limite de credito 
            // sumando lo ingresado en el textfield, por eso se realiza tmp2.buscar();
            // sino no hace falta
            Cliente tmp = new Cliente(1,vista4.getDni(),"","");
            ClienteDAO tmp2 = new ClienteDAO(tmp,conexion);
            tmp = tmp2.buscar(); // utilizo la misma    variable por no usar otra
            if(tmp!=null) {
                modelo =  new Cliente(1, vista4.getDni(),vista4.getNombre(), vista4.getApellido());
                modelo.setDomicilio(vista4.getDomicilio());
                modelo.setTelefono(vista4.getTelefono());
                modelo.setLimite(tmp.getLimite()+vista4.getLimite());
                ClienteDAO cliente = new ClienteDAO(modelo,conexion);
                cliente.modificar();
                JOptionPane.showMessageDialog(null,"registro modificado");
            }
        }
//*******************************************************************
//*******************************************************************
    }
}
