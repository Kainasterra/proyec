package com.trebol.dao;

import com.trebol.model.OrdenTrabajo;
import com.trebol.utils.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrdenTrabajoDAO {

    // Método para crear una nueva orden de trabajo
    public boolean registrarOrden(OrdenTrabajo orden) {
        // Asumimos que la base de datos maneja la fecha de ingreso (CURRENT_TIMESTAMP) si la enviamos nula o la maneja por defecto.
        // Si tienes problemas de fechas, ajustaremos esto.
        String sql = "INSERT INTO ordenes_trabajo (id_cliente, id_vehiculo, estatus, descripcion_problema, costo_mano_obra, costo_refacciones) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
             
            ps.setInt(1, orden.getIdCliente());
            ps.setInt(2, orden.getIdVehiculo());
            ps.setString(3, "pendiente"); // Toda orden inicia como pendiente
            ps.setString(4, orden.getDescripcionProblema());
            ps.setDouble(5, orden.getCostoManoObra());
            ps.setDouble(6, orden.getCostoRefacciones());
            
            return ps.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al registrar orden: " + e.getMessage());
            return false;
        }
    }

    // Método para actualizar los costos (Vital para la regla de negocio)
    public boolean actualizarCostos(int idOrden, double costoManoObra, double costoRefacciones) {
        String sql = "UPDATE ordenes_trabajo SET costo_mano_obra = ?, costo_refacciones = ? WHERE id_orden = ?";
        
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
             
            ps.setDouble(1, costoManoObra);
            ps.setDouble(2, costoRefacciones);
            ps.setInt(3, idOrden);
            
            return ps.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al actualizar costos: " + e.getMessage());
            return false;
        }
    }

    // Método para listar órdenes (útil para ver qué hay pendiente)
    public List<OrdenTrabajo> listarOrdenes() {
        List<OrdenTrabajo> lista = new ArrayList<>();
        String sql = "SELECT * FROM ordenes_trabajo";
        
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
             
            while (rs.next()) {
                OrdenTrabajo o = new OrdenTrabajo();
                o.setIdOrden(rs.getInt("id_orden"));
                o.setIdCliente(rs.getInt("id_cliente"));
                o.setIdVehiculo(rs.getInt("id_vehiculo"));
                // Solo obtenemos algunos campos para el listado general
                o.setEstatus(rs.getString("estatus"));
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
}