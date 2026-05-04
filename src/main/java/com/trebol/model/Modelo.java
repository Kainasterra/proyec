package com.trebol.model;

public class Modelo {
    private int idModelo;
    private String nombre;

    public Modelo(int idModelo, String nombre) {
        this.idModelo = idModelo;
        this.nombre = nombre;
    }
    public String getNombre() { return nombre; }
    @Override
    public String toString() { return nombre; } // Importante para el ComboBox
}