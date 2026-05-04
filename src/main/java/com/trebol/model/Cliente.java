package com.trebol.model;

public class Cliente {
    
    private int idCliente;
    private String nombre;
    private String telefono;
    private String correo;
    private String direccion; // <-- Aquí está el atributo que nos faltaba

    // Constructor vacío
    public Cliente() {
    }

    // Constructor con todos los parámetros (Ahora son 5)
    public Cliente(int idCliente, String nombre, String telefono, String correo, String direccion) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
        this.direccion = direccion;
    }

    // --- GETTERS Y SETTERS ---

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}