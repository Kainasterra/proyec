package com.trebol.dao;

import com.trebol.model.ArticuloInventario;
import com.trebol.utils.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InventarioDAO {

    // Método para registrar una nueva refacción en el inventario
    public boolean registrarArticulo(ArticuloInventario articulo) {
        String sql = "INSERT INTO inventario (nombre_refaccion, cantidad_stock, precio_unitario, alerta_minima) VALUES (?, ?, ?, ?)";
        
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
             
            ps.setString(1, articulo.getNombreRefaccion());
            ps.setInt(2, articulo.getCantidadStock());
            ps.setDouble(3, articulo.getPrecioUnitario());
            ps.setInt(4, articulo.getAlertaMinima());
            
            return ps.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al registrar artículo en inventario: " + e.getMessage());
            return false;
        }
    }

    // Método para listar todo el inventario (útil para reportes y ver existencias)
    public List<ArticuloInventario> listarInventario() {
        List<ArticuloInventario> lista = new ArrayList<>();
        String sql = "SELECT * FROM inventario";
        
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
             
            while (rs.next()) {
                ArticuloInventario a = new ArticuloInventario();
                a.setIdItem(rs.getInt("id_item"));
                a.setNombreRefaccion(rs.getString("nombre_refaccion"));
                a.setCantidadStock(rs.getInt("cantidad_stock"));
                a.setPrecioUnitario(rs.getDouble("precio_unitario"));
                a.setAlertaMinima(rs.getInt("alerta_minima"));
                lista.add(a);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar inventario: " + e.getMessage());
        }
        return lista;
    }

    // MÉTODO CRÍTICO: Descontar stock cuando se usa en un servicio
    public boolean descontarStock(int idItem, int cantidadAUsar) {
        // Primero verificamos si hay suficiente stock
        String sqlCheck = "SELECT cantidad_stock FROM inventario WHERE id_item = ?";
        String sqlUpdate = "UPDATE inventario SET cantidad_stock = cantidad_stock - ? WHERE id_item = ?";
        
        try (Connection con = Conexion.getConnection()) {
            
            // 1. Revisar stock actual
            int stockActual = 0;
            try (PreparedStatement psCheck = con.prepareStatement(sqlCheck)) {
                psCheck.setInt(1, idItem);
                ResultSet rs = psCheck.executeQuery();
                if (rs.next()) {
                    stockActual = rs.getInt("cantidad_stock");
                }
            }
            
            // 2. Si no hay suficiente, no hacemos el descuento y retornamos falso
            if (stockActual < cantidadAUsar) {
                System.out.println("Stock insuficiente. Solicitado: " + cantidadAUsar + ", Disponible: " + stockActual);
                return false;
            }
            
            // 3. Si hay suficiente, procedemos a descontar
            try (PreparedStatement psUpdate = con.prepareStatement(sqlUpdate)) {
                psUpdate.setInt(1, cantidadAUsar);
                psUpdate.setInt(2, idItem);
                return psUpdate.executeUpdate() > 0;
            }
            
        } catch (SQLException e) {
            System.err.println("Error al descontar stock: " + e.getMessage());
            return false;
        }
    }
}