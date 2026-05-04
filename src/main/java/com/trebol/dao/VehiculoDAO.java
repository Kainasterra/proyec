package com.trebol.dao;

import com.trebol.model.Vehiculo;
import com.trebol.utils.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehiculoDAO {
    
    // Método para registrar un vehículo asociándolo a un cliente
    public boolean registrarVehiculo(Vehiculo vehiculo) {
        String sql = "INSERT INTO vehiculos (id_cliente, marca, modelo, anio, placas) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
             
            ps.setInt(1, vehiculo.getIdCliente());
            ps.setString(2, vehiculo.getMarca());
            ps.setString(3, vehiculo.getModelo());
            ps.setInt(4, vehiculo.getAnio());
            ps.setString(5, vehiculo.getPlacas());
            
            return ps.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al registrar vehículo: " + e.getMessage());
            return false;
        }
    }

    // Método para listar SOLAMENTE los vehículos de un cliente en específico
    public List<Vehiculo> listarVehiculosPorCliente(int idCliente) {
        List<Vehiculo> lista = new ArrayList<>();
        String sql = "SELECT * FROM vehiculos WHERE id_cliente = ?";
        
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
             
            ps.setInt(1, idCliente);
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Vehiculo v = new Vehiculo();
                    v.setIdVehiculo(rs.getInt("id_vehiculo"));
                    v.setIdCliente(rs.getInt("id_cliente"));
                    v.setMarca(rs.getString("marca"));
                    v.setModelo(rs.getString("modelo"));
                    v.setAnio(rs.getInt("anio"));
                    v.setPlacas(rs.getString("placas"));
                    lista.add(v);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al listar vehículos por cliente: " + e.getMessage());
        }
        return lista;
    }
}