package com.trebol.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PanelOrdenes extends JPanel {

    // Componentes del formulario
    private JComboBox<String> cbPlacaVehiculo;
    private JTextArea txtDescripcionProblema;
    private JTextField txtCostoManoObra;
    private JTextField txtCostoRefacciones;
    private JComboBox<String> cbEstatus;
    private JButton btnGenerarOrden;
    
    // Componentes de la tabla
    private JTable tablaOrdenes;
    private DefaultTableModel modeloTabla;

    public PanelOrdenes() {
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        setLayout(new BorderLayout(20, 20));
        setBackground(new Color(245, 245, 245));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel lblTitulo = new JLabel("Generación de Órdenes de Trabajo");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitulo.setForeground(new Color(46, 64, 83));
        add(lblTitulo, BorderLayout.NORTH);

        JPanel panelCentro = new JPanel(new BorderLayout(10, 20));
        panelCentro.setBackground(new Color(245, 245, 245));

        JPanel panelFormulario = new JPanel(new GridBagLayout());
        panelFormulario.setBackground(Color.WHITE);
        panelFormulario.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Fila 1: Vehículo y Estatus
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0.5;
        panelFormulario.add(new JLabel("Vehículo (Placa):"), gbc);
        
        gbc.gridx = 1; gbc.gridy = 0;
        panelFormulario.add(new JLabel("Estatus actual:"), gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        cbPlacaVehiculo = new JComboBox<>();
        panelFormulario.add(cbPlacaVehiculo, gbc);

        gbc.gridx = 1; gbc.gridy = 1;
        cbEstatus = new JComboBox<>(new String[]{"en proceso", "pendiente", "finalizado", "entregado"});
        panelFormulario.add(cbEstatus, gbc);

        // Fila 2: Descripción del problema
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        panelFormulario.add(new JLabel("Descripción del Problema / Servicio a realizar:"), gbc);

        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2; gbc.ipady = 40;
        txtDescripcionProblema = new JTextArea();
        txtDescripcionProblema.setLineWrap(true);
        txtDescripcionProblema.setWrapStyleWord(true);
        JScrollPane scrollDescripcion = new JScrollPane(txtDescripcionProblema);
        panelFormulario.add(scrollDescripcion, gbc);

        // Fila 3: Costos
        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 1; gbc.ipady = 0;
        panelFormulario.add(new JLabel("Costo Mano de Obra ($):"), gbc);

        gbc.gridx = 1; gbc.gridy = 4;
        panelFormulario.add(new JLabel("Costo Refacciones ($):"), gbc);

        gbc.gridx = 0; gbc.gridy = 5;
        txtCostoManoObra = new JTextField("0.00");
        panelFormulario.add(txtCostoManoObra, gbc);

        gbc.gridx = 1; gbc.gridy = 5;
        txtCostoRefacciones = new JTextField("0.00");
        panelFormulario.add(txtCostoRefacciones, gbc);

        // Fila 4: Botón
        gbc.gridx = 0; gbc.gridy = 6; gbc.gridwidth = 2;
        btnGenerarOrden = new JButton("Generar Orden de Trabajo");
        btnGenerarOrden.setBackground(new Color(46, 204, 113));
        btnGenerarOrden.setForeground(Color.WHITE);
        btnGenerarOrden.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnGenerarOrden.setFocusPainted(false);
        btnGenerarOrden.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panelFormulario.add(btnGenerarOrden, gbc);

        panelCentro.add(panelFormulario, BorderLayout.NORTH);

        // Tabla actualizada
        String[] columnas = {"ID Orden", "Placa", "Fecha Ingreso", "Estatus", "Mano de Obra", "Refacciones"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaOrdenes = new JTable(modeloTabla);
        tablaOrdenes.setRowHeight(25);
        tablaOrdenes.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        
        JScrollPane scrollTabla = new JScrollPane(tablaOrdenes);
        panelCentro.add(scrollTabla, BorderLayout.CENTER);

        add(panelCentro, BorderLayout.CENTER);
    }

    // --- GETTERS ---
    public JComboBox<String> getCbPlacaVehiculo() { return cbPlacaVehiculo; }
    public JTextArea getTxtDescripcionProblema() { return txtDescripcionProblema; }
    public JTextField getTxtCostoManoObra() { return txtCostoManoObra; }
    public JTextField getTxtCostoRefacciones() { return txtCostoRefacciones; }
    public JComboBox<String> getCbEstatus() { return cbEstatus; }
    public JButton getBtnGenerarOrden() { return btnGenerarOrden; }
    public DefaultTableModel getModeloTabla() { return modeloTabla; }
}