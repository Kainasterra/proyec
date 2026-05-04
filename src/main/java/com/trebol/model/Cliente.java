package com.trebol.model;

public class Cliente {
    
    // Atributos que coinciden con la información requerida
    private int idCliente;
    private String nombre;
    private String telefono;
    private String correo;

    // Constructor vacío (obligatorio para muchos frameworks y operaciones)
    public Cliente() {
    }

    // Constructor con parámetros (para crear objetos Cliente rápidamente)
    public Cliente(int idCliente, String nombre, String telefono, String correo) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
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
}