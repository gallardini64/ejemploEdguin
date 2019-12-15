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
public class Cliente extends Persona {
    private int idcliente;
    private double limite;
    private double saldo;
    
    public Cliente(){}
    
    public Cliente(int id, int dni, String nombre, String apellido){
        super(dni, nombre, apellido);
        this.idcliente = id;
    }

    public int getIdcliente() {
        return idcliente;
    }

    public double getLimite() {
        return limite;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setIdcliente(int idcliente) {
        this.idcliente = idcliente;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    @Override
    public String toString() {
        return "Cliente{" + "idcliente=" + idcliente + super.toString()+ ", limite=" + limite + ", saldo=" + saldo + '}';
    }
    
    
}
