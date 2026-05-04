package com.trebol.dao;

import com.trebol.model.ServicioMuelle;
import com.trebol.utils.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServicioMuelleDAO {

    // Método para registrar el trabajo realizado por el técnico
    public boolean registrarServicio(ServicioMuelle servicio) {
        String sql = "INSERT INTO servicios_muelles (id_orden, tipo_reparacion, detalles_ajuste, piezas_especializadas, tecnico_responsable) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
             
            ps.setInt(1, servicio.getIdOrden());
            ps.setString(2, servicio.getTipoReparacion());
            ps.setString(3, servicio.getDetallesAjuste());
            ps.setString(4, servicio.getPiezasEspecializadas());
            ps.setString(5, servicio.getTecnicoResponsable());
            
            return ps.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al registrar servicio de muelle: " + e.getMessage());
            return false;
        }
    }

    // Método para listar todos los trabajos que pertenecen a una misma orden
    public List<ServicioMuelle> listarServiciosPorOrden(int idOrden) {
        List<ServicioMuelle> lista = new ArrayList<>();
        String sql = "SELECT * FROM servicios_muelles WHERE id_orden = ?";
        
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
             
            ps.setInt(1, idOrden);
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ServicioMuelle sm = new ServicioMuelle();
                    sm.setIdMuelle(rs.getInt("id_muelle"));
                    sm.setIdOrden(rs.getInt("id_orden"));
                    sm.setTipoReparacion(rs.getString("tipo_reparacion"));
                    sm.setDetallesAjuste(rs.getString("detalles_ajuste"));
                    sm.setPiezasEspecializadas(rs.getString("piezas_especializadas"));
                    sm.setTecnicoResponsable(rs.getString("tecnico_responsable"));
                    lista.add(sm);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al listar servicios por orden: " + e.getMessage());
        }
        return lista;
    }
    // Método para listar TODOS los servicios (útil para el historial general de la tabla)
    public List<ServicioMuelle> listarTodos() {
        List<ServicioMuelle> lista = new ArrayList<>();
        String sql = "SELECT * FROM servicios_muelles";
        
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
             
            while (rs.next()) {
                ServicioMuelle sm = new ServicioMuelle();
                sm.setIdMuelle(rs.getInt("id_muelle"));
                sm.setIdOrden(rs.getInt("id_orden"));
                sm.setTipoReparacion(rs.getString("tipo_reparacion"));
                sm.setDetallesAjuste(rs.getString("detalles_ajuste"));
                sm.setPiezasEspecializadas(rs.getString("piezas_especializadas"));
                sm.setTecnicoResponsable(rs.getString("tecnico_responsable"));
                lista.add(sm);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar todos los servicios: " + e.getMessage());
        }
        return lista;
    }
}