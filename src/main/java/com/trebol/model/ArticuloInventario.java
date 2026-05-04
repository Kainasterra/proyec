package com.trebol.model;

public class ArticuloInventario {
    
    private int idItem;
    private String nombreRefaccion;
    private int cantidadStock;
    private double precioUnitario;
    private int alertaMinima;

    // Constructor vacío
    public ArticuloInventario() {
    }

    // Constructor con parámetros
    public ArticuloInventario(int idItem, String nombreRefaccion, int cantidadStock, double precioUnitario, int alertaMinima) {
        this.idItem = idItem;
        this.nombreRefaccion = nombreRefaccion;
        this.cantidadStock = cantidadStock;
        this.precioUnitario = precioUnitario;
        this.alertaMinima = alertaMinima;
    }

    // --- GETTERS Y SETTERS ---

    public int getIdItem() {
        return idItem;
    }

    public void setIdItem(int idItem) {
        this.idItem = idItem;
    }

    public String getNombreRefaccion() {
        return nombreRefaccion;
    }

    public void setNombreRefaccion(String nombreRefaccion) {
        this.nombreRefaccion = nombreRefaccion;
    }

    public int getCantidadStock() {
        return cantidadStock;
    }

    public void setCantidadStock(int cantidadStock) {
        this.cantidadStock = cantidadStock;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public int getAlertaMinima() {
        return alertaMinima;
    }

    public void setAlertaMinima(int alertaMinima) {
        this.alertaMinima = alertaMinima;
    }
}