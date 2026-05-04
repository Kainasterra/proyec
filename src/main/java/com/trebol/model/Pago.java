package com.trebol.model;

public class Pago {
    
    private int idPago;
    private int idOrden;
    private String fechaPago;
    private double monto;
    private String metodoPago; // 'Efectivo', 'Tarjeta', 'Transferencia'

    public Pago() {
    }

    public Pago(int idPago, int idOrden, String fechaPago, double monto, String metodoPago) {
        this.idPago = idPago;
        this.idOrden = idOrden;
        this.fechaPago = fechaPago;
        this.monto = monto;
        this.metodoPago = metodoPago;
    }

    // --- GETTERS Y SETTERS ---

    public int getIdPago() { return idPago; }
    public void setIdPago(int idPago) { this.idPago = idPago; }

    public int getIdOrden() { return idOrden; }
    public void setIdOrden(int idOrden) { this.idOrden = idOrden; }

    public String getFechaPago() { return fechaPago; }
    public void setFechaPago(String fechaPago) { this.fechaPago = fechaPago; }

    public double getMonto() { return monto; }
    public void setMonto(double monto) { this.monto = monto; }

    public String getMetodoPago() { return metodoPago; }
    public void setMetodoPago(String metodoPago) { this.metodoPago = metodoPago; }
}