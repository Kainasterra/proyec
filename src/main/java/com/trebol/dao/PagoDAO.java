package com.trebol.dao;

import com.trebol.model.Pago;
import com.trebol.utils.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PagoDAO {

    public boolean registrarPago(Pago pago) {
        String sql = "INSERT INTO pagos (id_orden, monto, metodo_pago) VALUES (?, ?, ?)";
        
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
             
            ps.setInt(1, pago.getIdOrden());
            ps.setDouble(2, pago.getMonto());
            ps.setString(3, pago.getMetodoPago());
            
            return ps.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al registrar pago: " + e.getMessage());
            return false;
        }
    }
}