package com.trebol.model;

public class OrdenTrabajo {
    private int idOrden;
    private int idCliente;
    private int idVehiculo;
    private String placaVehiculo; // Auxiliar para la tabla
    private String fechaIngreso;
    private String fechaEntregaEstimada;
    private String estatus;
    private String diagnosticoTecnico;
    private String descripcionProblema;
    private double costoManoObra;
    private double costoRefacciones;

    // Constructor vacío
    public OrdenTrabajo() {}

    // Constructor para Listar (Todos los campos)
    public OrdenTrabajo(int idOrden, int idCliente, int idVehiculo, String placaVehiculo, String fechaIngreso, 
                        String fechaEntregaEstimada, String estatus, String diagnosticoTecnico, 
                        String descripcionProblema, double costoManoObra, double costoRefacciones) {
        this.idOrden = idOrden;
        this.idCliente = idCliente;
        this.idVehiculo = idVehiculo;
        this.placaVehiculo = placaVehiculo;
        this.fechaIngreso = fechaIngreso;
        this.fechaEntregaEstimada = fechaEntregaEstimada;
        this.estatus = estatus;
        this.diagnosticoTecnico = diagnosticoTecnico;
        this.descripcionProblema = descripcionProblema;
        this.costoManoObra = costoManoObra;
        this.costoRefacciones = costoRefacciones;
    }

    // Getters y Setters
    public int getIdOrden() { return idOrden; }
    public void setIdOrden(int idOrden) { this.idOrden = idOrden; }

    public int getIdCliente() { return idCliente; }
    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }

    public int getIdVehiculo() { return idVehiculo; }
    public void setIdVehiculo(int idVehiculo) { this.idVehiculo = idVehiculo; }

    public String getPlacaVehiculo() { return placaVehiculo; }
    public void setPlacaVehiculo(String placaVehiculo) { this.placaVehiculo = placaVehiculo; }

    public String getFechaIngreso() { return fechaIngreso; }
    public void setFechaIngreso(String fechaIngreso) { this.fechaIngreso = fechaIngreso; }

    public String getFechaEntregaEstimada() { return fechaEntregaEstimada; }
    public void setFechaEntregaEstimada(String fechaEntregaEstimada) { this.fechaEntregaEstimada = fechaEntregaEstimada; }

    public String getEstatus() { return estatus; }
    public void setEstatus(String estatus) { this.estatus = estatus; }

    public String getDiagnosticoTecnico() { return diagnosticoTecnico; }
    public void setDiagnosticoTecnico(String diagnosticoTecnico) { this.diagnosticoTecnico = diagnosticoTecnico; }

    public String getDescripcionProblema() { return descripcionProblema; }
    public void setDescripcionProblema(String descripcionProblema) { this.descripcionProblema = descripcionProblema; }

    public double getCostoManoObra() { return costoManoObra; }
    public void setCostoManoObra(double costoManoObra) { this.costoManoObra = costoManoObra; }

    public double getCostoRefacciones() { return costoRefacciones; }
    public void setCostoRefacciones(double costoRefacciones) { this.costoRefacciones = costoRefacciones; }
}