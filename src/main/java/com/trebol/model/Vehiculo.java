package com.trebol.model;

public class Vehiculo {
    
    private int idVehiculo;
    private int idCliente; // Llave foránea para vincularlo a su dueño
    private String marca;
    private String modelo;
    private String placas;
    private int anio;

    // Constructor vacío
    public Vehiculo() {
    }

    // Constructor con parámetros
    public Vehiculo(int idVehiculo, int idCliente, String marca, String modelo, String placas, int anio) {
        this.idVehiculo = idVehiculo;
        this.idCliente = idCliente;
        this.marca = marca;
        this.modelo = modelo;
        this.placas = placas;
        this.anio = anio;
    }

    // --- GETTERS Y SETTERS ---

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

    public String getPlacas() {
        return placas;
    }

    public void setPlacas(String placas) {
        this.placas = placas;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }
}