package com.trebol.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    
    // Credenciales basadas en tu exportación SQL
    private static final String URL = "jdbc:mariadb://127.0.0.1:3307/multiservicios_trebol";
    private static final String USER = "root"; 
    private static final String PASSWORD = ""; 

    public static Connection getConnection() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("¡Conexión exitosa a Multiservicios Trebol!");
        } catch (SQLException e) {
            System.err.println("Error al conectar: " + e.getMessage());
        }
        return con;
    }
}