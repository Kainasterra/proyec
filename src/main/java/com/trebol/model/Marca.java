package com.trebol.model;

public class Marca {
    private int idMarca;
    private String nombre;

    public Marca(int idMarca, String nombre) {
        this.idMarca = idMarca;
        this.nombre = nombre;
    }
    public int getIdMarca() { return idMarca; }
    public String getNombre() { return nombre; }
    @Override
    public String toString() { return nombre; } // Importante para el ComboBox
}