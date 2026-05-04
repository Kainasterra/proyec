package com.trebol.dao;

import com.trebol.model.Vehiculo;
import com.trebol.utils.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehiculoDAO {

    public boolean registrarVehiculo(Vehiculo vehiculo) {
        String sql = "INSERT INTO vehiculos (id_cliente, placas, marca, modelo, anio) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = Conexion.getConnection(); 
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, vehiculo.getIdCliente());
            ps.setString(2, vehiculo.getPlacas());
            ps.setString(3, vehiculo.getMarca());
            ps.setString(4, vehiculo.getModelo());
            ps.setInt(5, vehiculo.getAnio());
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al registrar vehículo: " + e.getMessage());
            return false;
        }
    }

    public List<Vehiculo> listarVehiculos() {
        List<Vehiculo> lista = new ArrayList<>();
        String sql = "SELECT * FROM vehiculos";
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                Vehiculo v = new Vehiculo(
                    rs.getInt("id_vehiculo"),
                    rs.getInt("id_cliente"),
                    rs.getString("placas"), // Coincidiendo con tu DB
                    rs.getString("marca"),
                    rs.getString("modelo"),
                    rs.getInt("anio")
                );
                lista.add(v);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar vehículos: " + e.getMessage());
        }
        return lista;
    }

    // --- NUEVO: Actualizar Vehículo ---
    public boolean actualizarVehiculo(Vehiculo vehiculo) {
        String sql = "UPDATE vehiculos SET id_cliente=?, placas=?, marca=?, modelo=?, anio=? WHERE id_vehiculo=?";
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, vehiculo.getIdCliente());
            ps.setString(2, vehiculo.getPlacas());
            ps.setString(3, vehiculo.getMarca());
            ps.setString(4, vehiculo.getModelo());
            ps.setInt(5, vehiculo.getAnio());
            ps.setInt(6, vehiculo.getIdVehiculo());
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar vehículo: " + e.getMessage());
            return false;
        }
    }

    // --- NUEVO: Eliminar Vehículo ---
    public boolean eliminarVehiculo(int idVehiculo) {
        String sql = "DELETE FROM vehiculos WHERE id_vehiculo = ?";
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, idVehiculo);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar vehículo: " + e.getMessage());
            return false;
        }
    }
}