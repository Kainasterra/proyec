package com.trebol.model;

public class ServicioMuelle {
    
    private int idMuelle;
    private int idOrden; // Vinculación con el "núcleo" del sistema
    private String tipoReparacion;
    private String detallesAjuste;
    private String piezasEspecializadas;
    private String tecnicoResponsable;

    // Constructor vacío
    public ServicioMuelle() {
    }

    // Constructor con parámetros
    public ServicioMuelle(int idMuelle, int idOrden, String tipoReparacion, String detallesAjuste, 
                          String piezasEspecializadas, String tecnicoResponsable) {
        this.idMuelle = idMuelle;
        this.idOrden = idOrden;
        this.tipoReparacion = tipoReparacion;
        this.detallesAjuste = detallesAjuste;
        this.piezasEspecializadas = piezasEspecializadas;
        this.tecnicoResponsable = tecnicoResponsable;
    }

    // --- GETTERS Y SETTERS ---

    public int getIdMuelle() {
        return idMuelle;
    }

    public void setIdMuelle(int idMuelle) {
        this.idMuelle = idMuelle;
    }

    public int getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(int idOrden) {
        this.idOrden = idOrden;
    }

    public String getTipoReparacion() {
        return tipoReparacion;
    }

    public void setTipoReparacion(String tipoReparacion) {
        this.tipoReparacion = tipoReparacion;
    }

    public String getDetallesAjuste() {
        return detallesAjuste;
    }

    public void setDetallesAjuste(String detallesAjuste) {
        this.detallesAjuste = detallesAjuste;
    }

    public String getPiezasEspecializadas() {
        return piezasEspecializadas;
    }

    public void setPiezasEspecializadas(String piezasEspecializadas) {
        this.piezasEspecializadas = piezasEspecializadas;
    }

    public String getTecnicoResponsable() {
        return tecnicoResponsable;
    }

    public void setTecnicoResponsable(String tecnicoResponsable) {
        this.tecnicoResponsable = tecnicoResponsable;
    }
}