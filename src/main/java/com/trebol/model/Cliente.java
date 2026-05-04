package com.trebol.model;

public class Cliente {
    private int idCliente;
    private String nombre;
    private String telefono;
    private String correo;
    private String direccion;

    // 1. Constructor Vacío
    public Cliente() {}

    // 2. Constructor Completo (Con ID) - Útil para listar y actualizar
    public Cliente(int idCliente, String nombre, String telefono, String correo, String direccion) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
        this.direccion = direccion;
    }

    // 3. Constructor de Registro (Sin ID) - Útil para el guardado inicial
    public Cliente(String nombre, String telefono, String correo, String direccion) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
        this.direccion = direccion;
    }

    // Getters y Setters
    public int getIdCliente() { return idCliente; }
    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
}