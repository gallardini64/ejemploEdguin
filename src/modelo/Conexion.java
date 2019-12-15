/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author sistemas
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexion {
    //private String servidor="172.20.68.123";
    private String servidor="localhost";
    private String usuario = "root";
    private String clave="";
    private String basededatos="paradigmas";
    private String url;
    private Connection con;
    private Statement consulta;
    
    public Conexion(){
    }
    public int conectar(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            url = "jdbc:mysql://"+servidor+"/"+basededatos;
            con = DriverManager.getConnection(url, usuario,clave);
            consulta = con.createStatement();
        }
        catch(ClassNotFoundException e){
            System.out.println("Error en Driver");
            return -1;
        }
        catch(SQLException e){
            System.out.println("Error en base de datos");
            return -2;
        }
        return 1;
    }

    public String getServidor() {
        return servidor;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getClave() {
        return clave;
    }

    public String getBasededatos() {
        return basededatos;
    }

    public String getUrl() {
        return url;
    }

    public Connection getCon() {
        return con;
    }

    public Statement getConsulta() {
        return consulta;
    }

    public void setServidor(String servidor) {
        this.servidor = servidor;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public void setBasededatos(String basededatos) {
        this.basededatos = basededatos;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setCon(Connection con) {
        this.con = con;
    }

    public void setConsulta(Statement consulta) {
        this.consulta = consulta;
    }
    
}
