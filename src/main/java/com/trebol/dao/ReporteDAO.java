package com.trebol.dao;

import com.trebol.utils.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReporteDAO {

    // Ejemplo: Sumar todos los pagos entre dos fechas
    public double obtenerIngresosTotales(String fechaInicio, String fechaFin) {
        String sql = "SELECT SUM(monto) as total FROM pagos WHERE fecha_pago BETWEEN ? AND ?";
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, fechaInicio);
            ps.setString(2, fechaFin);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble("total");
                }
            }
        } catch (SQLException e) {
            System.err.println("Error en reporte de ingresos: " + e.getMessage());
        }
        return 0;
    }
}