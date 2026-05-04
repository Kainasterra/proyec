package com.trebol.view;

import javax.swing.*;
import java.awt.*;

public class MenuPrincipal {

    private JFrame frame;
    private JPanel panelContenido; // El panel derecho que cambiará de vistas
    private CardLayout cardLayout; // El controlador de nuestras "cartas" (pantallas)

    public MenuPrincipal() {
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        // 1. Configuración de la Ventana Principal
        frame = new JFrame("Multiservicios El Trébol - Sistema de Gestión");
        frame.setSize(1000, 700); // Ventana un poco más grande
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        // 2. PANEL LATERAL (MENÚ - Lado Izquierdo)
        JPanel panelMenu = new JPanel();
        panelMenu.setBackground(new Color(46, 64, 83)); // Azul oscuro
        panelMenu.setPreferredSize(new Dimension(250, 700));
        panelMenu.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20)); // Acomoda hacia abajo con margen

        // Título del menú lateral
        JLabel lblMenu = new JLabel("<html><center>MULTISERVICIOS<br>EL TRÉBOL</center></html>");
        lblMenu.setForeground(Color.WHITE);
        lblMenu.setFont(new Font("Segoe UI", Font.BOLD, 20));
        panelMenu.add(lblMenu);

        // Separador visual
        JSeparator separador = new JSeparator();
        separador.setPreferredSize(new Dimension(200, 10));
        panelMenu.add(separador);

        // Botones del menú lateral
        JButton btnInicio = crearBotonLateral("Inicio");
        JButton btnClientes = crearBotonLateral("Clientes");
        JButton btnVehiculos = crearBotonLateral("Vehículos");
        JButton btnOrdenes = crearBotonLateral("Órdenes de Trabajo");
        JButton btnMuelles = crearBotonLateral("Módulo Muelles");

        panelMenu.add(btnInicio);
        panelMenu.add(btnClientes);
        panelMenu.add(btnVehiculos);
        panelMenu.add(btnOrdenes);
        panelMenu.add(btnMuelles);

        // 3. PANEL CENTRAL (CONTENIDO - Lado Derecho)
        cardLayout = new CardLayout();
        panelContenido = new JPanel(cardLayout);
        panelContenido.setBackground(new Color(245, 245, 245));

        // 4. CREAR LAS "CARTAS" (PANTALLAS) TEMPORALES
        panelContenido.add(crearPanelDemo("Pantalla de Inicio", "Bienvenido al Sistema"), "Inicio");
        panelContenido.add(crearPanelDemo("Gestión de Clientes", "Aquí irá el buscador de clientes"), "Clientes");
        panelContenido.add(crearPanelDemo("Gestión de Vehículos", "Aquí irá la tabla de vehículos"), "Vehiculos");
        panelContenido.add(crearPanelDemo("Órdenes de Trabajo", "Aquí gestionaremos los servicios"), "Ordenes");
        panelContenido.add(crearPanelDemo("Módulo de Muelles", "Aquí irá el módulo principal que desarrollaremos"), "Muelles");

        // 5. AGREGAR ACCIONES A LOS BOTONES (La Magia)
        // Al hacer clic, le decimos al CardLayout que muestre la tarjeta con ese nombre exacto
        btnInicio.addActionListener(e -> cardLayout.show(panelContenido, "Inicio"));
        btnClientes.addActionListener(e -> cardLayout.show(panelContenido, "Clientes"));
        btnVehiculos.addActionListener(e -> cardLayout.show(panelContenido, "Vehiculos"));
        btnOrdenes.addActionListener(e -> cardLayout.show(panelContenido, "Ordenes"));
        btnMuelles.addActionListener(e -> cardLayout.show(panelContenido, "Muelles"));

        // 6. Ensamblar todo en la ventana
        frame.add(panelMenu, BorderLayout.WEST); // Menú a la izquierda
        frame.add(panelContenido, BorderLayout.CENTER); // Contenido al centro/derecha
    }

    // Método auxiliar para diseñar los botones del menú de forma uniforme
    private JButton crearBotonLateral(String texto) {
        JButton boton = new JButton(texto);
        boton.setPreferredSize(new Dimension(220, 40));
        boton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        boton.setBackground(new Color(52, 152, 219)); // Azul vibrante
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setBorderPainted(false);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return boton;
    }

    // Método auxiliar TEMPORAL para generar paneles de relleno demostrativos
    private JPanel crearPanelDemo(String titulo, String subtitulo) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout()); // Para centrar el texto fácilmente
        panel.setBackground(new Color(245, 245, 245));

        JLabel lblTitulo = new JLabel("<html><center><h1>" + titulo + "</h1><h3>" + subtitulo + "</h3></center></html>");
        lblTitulo.setForeground(Color.DARK_GRAY);
        panel.add(lblTitulo);

        return panel;
    }

    public void mostrar() {
        frame.setVisible(true);
    }
}