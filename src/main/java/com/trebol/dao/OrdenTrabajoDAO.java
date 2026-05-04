package com.trebol.dao;

import com.trebol.model.OrdenTrabajo;
import com.trebol.utils.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrdenTrabajoDAO {

    // 1. Registrar una nueva orden
    public boolean registrarOrden(OrdenTrabajo orden) {
        String sql = "INSERT INTO ordenes_trabajo (id_cliente, id_vehiculo, estatus, descripcion_problema, "
                   + "costo_mano_obra, costo_refacciones, fecha_entrega_estimada, diagnostico_tecnico) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
             
            ps.setInt(1, orden.getIdCliente());
            ps.setInt(2, orden.getIdVehiculo());
            ps.setString(3, orden.getEstatus());
            ps.setString(4, orden.getDescripcionProblema());
            ps.setDouble(5, orden.getCostoManoObra());
            ps.setDouble(6, orden.getCostoRefacciones());
            ps.setString(7, orden.getFechaEntregaEstimada());
            ps.setString(8, orden.getDiagnosticoTecnico());
            
            return ps.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al registrar orden: " + e.getMessage());
            return false;
        }
    }

    // 2. Listar todas las órdenes (con JOIN para traer las placas)
    public List<OrdenTrabajo> listarOrdenes() {
        List<OrdenTrabajo> lista = new ArrayList<>();
        String sql = "SELECT o.*, v.placas FROM ordenes_trabajo o "
                   + "INNER JOIN vehiculos v ON o.id_vehiculo = v.id_vehiculo "
                   + "ORDER BY o.id_orden DESC"; 
        
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
             
            while (rs.next()) {
                OrdenTrabajo o = new OrdenTrabajo();
                o.setIdOrden(rs.getInt("id_orden"));
                o.setIdCliente(rs.getInt("id_cliente"));
                o.setIdVehiculo(rs.getInt("id_vehiculo"));
                o.setPlacaVehiculo(rs.getString("placas")); 
                o.setFechaIngreso(rs.getString("fecha_ingreso"));
                o.setFechaEntregaEstimada(rs.getString("fecha_entrega_estimada"));
                o.setEstatus(rs.getString("estatus"));
                o.setDiagnosticoTecnico(rs.getString("diagnostico_tecnico"));
                o.setDescripcionProblema(rs.getString("descripcion_problema"));
                o.setCostoManoObra(rs.getDouble("costo_mano_obra"));
                o.setCostoRefacciones(rs.getDouble("costo_refacciones"));
                
                lista.add(o);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar órdenes: " + e.getMessage());
        }
        return lista;
    }

    // 3. Actualizar una orden existente
    public boolean actualizarOrden(OrdenTrabajo orden) {
        String sql = "UPDATE ordenes_trabajo SET id_cliente=?, id_vehiculo=?, estatus=?, "
                   + "descripcion_problema=?, costo_mano_obra=?, costo_refacciones=?, "
                   + "fecha_entrega_estimada=?, diagnostico_tecnico=? WHERE id_orden=?";
        
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, orden.getIdCliente());
            ps.setInt(2, orden.getIdVehiculo());
            ps.setString(3, orden.getEstatus());
            ps.setString(4, orden.getDescripcionProblema());
            ps.setDouble(5, orden.getCostoManoObra());
            ps.setDouble(6, orden.getCostoRefacciones());
            ps.setString(7, orden.getFechaEntregaEstimada());
            ps.setString(8, orden.getDiagnosticoTecnico());
            ps.setInt(9, orden.getIdOrden());
            
            return ps.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al actualizar orden: " + e.getMessage());
            return false;
        }
    }

    // 4. Eliminar una orden
    public boolean eliminarOrden(int id) {
        String sql = "DELETE FROM ordenes_trabajo WHERE id_orden = ?";
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar orden: " + e.getMessage());
            return false;
        }
    }
}