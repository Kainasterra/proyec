package com.trebol.model;

public class Vehiculo {

    private int idVehiculo;
    private int idCliente;
    private String placa;
    private String marca;
    private String modelo;
    private int anio;

    public Vehiculo() {
    }

    // Constructor completo
    public Vehiculo(int idVehiculo, int idCliente, String placa, String marca, String modelo, int anio) {
        this.idVehiculo = idVehiculo;
        this.idCliente = idCliente;
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.anio = anio;
    }

    // Constructor sin ID (El que pide el error 106,33)
    public Vehiculo(String placa, int idCliente, String marca, String modelo, int anio) {
        this.placa = placa;
        this.idCliente = idCliente;
        this.marca = marca;
        this.modelo = modelo;
        this.anio = anio;
    }

    // Getters y Setters
    public int getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(int idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getPlaca() {
        return placa;
    } // ESTE ES EL QUE FALTA

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }
}
