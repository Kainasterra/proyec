package com.trebol.model;

public class Vehiculo {
    private int idVehiculo;
    private int idCliente;
    private String placas; // Usamos plural para coincidir con tu DB
    private String marca;
    private String modelo;
    private int anio;

    // 1. Constructor Vacío
    public Vehiculo() {}

    // 2. Constructor Completo (Con ID) - Para listar y actualizar
    public Vehiculo(int idVehiculo, int idCliente, String placas, String marca, String modelo, int anio) {
        this.idVehiculo = idVehiculo;
        this.idCliente = idCliente;
        this.placas = placas;
        this.marca = marca;
        this.modelo = modelo;
        this.anio = anio;
    }

    // 3. Constructor de Registro (Sin ID) - Para nuevos vehículos
    public Vehiculo(int idCliente, String placas, String marca, String modelo, int anio) {
        this.idCliente = idCliente;
        this.placas = placas;
        this.marca = marca;
        this.modelo = modelo;
        this.anio = anio;
    }

    // Getters y Setters
    public int getIdVehiculo() { return idVehiculo; }
    public void setIdVehiculo(int idVehiculo) { this.idVehiculo = idVehiculo; }

    public int getIdCliente() { return idCliente; }
    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }

    public String getPlacas() { return placas; }
    public void setPlacas(String placas) { this.placas = placas; }

    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    public int getAnio() { return anio; }
    public void setAnio(int anio) { this.anio = anio; }
}