package com.trebol.dao;

import com.trebol.model.Marca;
import com.trebol.model.Modelo;
import com.trebol.utils.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CatalogosDAO {

    public List<Marca> listarMarcas() {
        List<Marca> lista = new ArrayList<>();
        String sql = "SELECT * FROM marcas ORDER BY nombre";
        try (Connection con = Conexion.getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(new Marca(rs.getInt("id_marca"), rs.getString("nombre")));
            }
        } catch (SQLException e) { System.err.println("Error marcas: " + e.getMessage()); }
        return lista;
    }

    public List<Modelo> listarModelos(int idMarca) {
        List<Modelo> lista = new ArrayList<>();
        String sql = "SELECT * FROM modelos WHERE id_marca = ? ORDER BY nombre";
        try (Connection con = Conexion.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idMarca);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(new Modelo(rs.getInt("id_modelo"), rs.getString("nombre")));
                }
            }
        } catch (SQLException e) { System.err.println("Error modelos: " + e.getMessage()); }
        return lista;
    }
}