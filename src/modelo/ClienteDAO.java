/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 *
 * @author sistemas
 */
public class ClienteDAO {
    private Cliente cliente;
    private Conexion conexion;
    
    public ClienteDAO(Cliente cliente, Conexion conexion){
        this.cliente = cliente;
        this.conexion = conexion;
    }

    public void agregar(){
        try{
            String sql = "INSERT INTO clientes SET dni='"+cliente.getDni()+"',"
                    + " apellido='"+cliente.getApellido()+ "', "
                    + "nombre='"+ cliente.getNombre()+ "',"
                    + "domicilio='"+cliente.getDomicilio()+"', "
                    + "telefono='"+cliente.getTelefono()+"',"
                    + "limite='"+cliente.getLimite()+"'";
            conexion.getConsulta().execute(sql);
        }
        catch(SQLException e){
            System.out.println("Error al agregar datos a la tabla");
        }
    }
    public void modificar(){
        try{
            String sql = "UPDATE clientes SET "
                    + " apellido='"+cliente.getApellido()+ "', "
                    + "nombre='"+ cliente.getNombre()+ "',"
                    + "domicilio='"+cliente.getDomicilio()+"', "
                    + "telefono='"+cliente.getTelefono()+"',"
                    + "limite='"+cliente.getLimite()+"'"
                    + " WHERE dni='"+cliente.getDni()+"'";
            conexion.getConsulta().execute(sql);
        }
        catch(SQLException e){
            System.out.println("Error al modificar datos a la tabla");
        }        
    }
    public void borrar(){
        try{
            String sql = "DELETE FORM clientes WHERE dni='"+cliente.getDni()+"'";
            conexion.getConsulta().execute(sql);
        }
        catch(SQLException e){
            System.out.println("Error al borrar datos a la tabla");
        }        
    }
    public ArrayList<Cliente> leer(){
        ArrayList<Cliente> lista = new ArrayList<Cliente>();
        try{
            String sql = "SELECT * FROM clientes";
            ResultSet fila = conexion.getConsulta().executeQuery(sql);
            while(fila.next()){
                Cliente tmp = new Cliente();
                tmp.setDni( fila.getInt("dni") );
                tmp.setApellido(fila.getString("apellido"));
                tmp.setNombre(fila.getString("nombre"));
                tmp.setDomicilio(fila.getString("domicilio"));
                tmp.setTelefono(fila.getString("telefono"));
                tmp.setLimite(fila.getDouble("limite"));
                lista.add(tmp);
            }
        }
        catch(SQLException e){
            System.out.println("Error al leer datos de la tabla");
        }        
        return lista;
    }
    public Cliente buscar(){
        try{
            String sql = "SELECT * FROM clientes WHERE dni='"+cliente.getDni()+"'";
            ResultSet fila = conexion.getConsulta().executeQuery(sql);
            if(fila.next()){
                Cliente tmp = new Cliente();
                tmp.setDni( fila.getInt("dni") );
                tmp.setApellido(fila.getString("apellido"));
                tmp.setNombre(fila.getString("nombre"));
                tmp.setDomicilio(fila.getString("domicilio"));
                tmp.setTelefono(fila.getString("telefono"));
                tmp.setLimite(fila.getDouble("limite"));
                return tmp;
            }
        }
        catch(SQLException e){
            System.out.println("Error al buscar datos de la tabla");
        }        
        return null;
    }
    
}
