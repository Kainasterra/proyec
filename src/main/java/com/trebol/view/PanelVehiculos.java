package com.trebol.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PanelVehiculos extends JPanel {

    // Componentes del formulario
    private JTextField txtPlaca;
    private JComboBox<String> cbMarca;   // Cambiado a ComboBox
    private JComboBox<String> cbModelo;  // Cambiado a ComboBox
    private JComboBox<String> cbAnio;    // Cambiado a ComboBox
    private JTextField txtIdCliente;
    private JButton btnGuardar;
    
    // Componentes de la tabla
    private JTable tablaVehiculos;
    private DefaultTableModel modeloTabla;

    public PanelVehiculos() {
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        setLayout(new BorderLayout(20, 20));
        setBackground(new Color(245, 245, 245));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // 1. Encabezado
        JLabel lblTitulo = new JLabel("Gestión de Vehículos");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitulo.setForeground(new Color(46, 64, 83));
        add(lblTitulo, BorderLayout.NORTH);

        JPanel panelCentro = new JPanel(new BorderLayout(10, 20));
        panelCentro.setBackground(new Color(245, 245, 245));

        // 2. Formulario
        JPanel panelFormulario = new JPanel(new GridLayout(6, 2, 10, 15));
        panelFormulario.setBackground(Color.WHITE);
        panelFormulario.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));

        panelFormulario.add(new JLabel("Placas del Vehículo:"));
        txtPlaca = new JTextField();
        panelFormulario.add(txtPlaca);

        panelFormulario.add(new JLabel("Marca:"));
        cbMarca = new JComboBox<>(); // Inicializamos vacío
        panelFormulario.add(cbMarca);

        panelFormulario.add(new JLabel("Modelo:"));
        cbModelo = new JComboBox<>(); // Inicializamos vacío
        panelFormulario.add(cbModelo);

        panelFormulario.add(new JLabel("Año:"));
        cbAnio = new JComboBox<>(); // Inicializamos vacío
        panelFormulario.add(cbAnio);

        panelFormulario.add(new JLabel("ID del Cliente (Dueño):"));
        txtIdCliente = new JTextField();
        panelFormulario.add(txtIdCliente);

        panelFormulario.add(new JLabel("")); 
        btnGuardar = new JButton("Registrar Vehículo");
        btnGuardar.setBackground(new Color(52, 152, 219)); 
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnGuardar.setFocusPainted(false);
        btnGuardar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panelFormulario.add(btnGuardar);

        panelCentro.add(panelFormulario, BorderLayout.NORTH);

        // 3. Tabla de Vehículos
        String[] columnas = {"Placa", "Marca", "Modelo", "Año", "ID Cliente"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaVehiculos = new JTable(modeloTabla);
        tablaVehiculos.setRowHeight(25);
        tablaVehiculos.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        
        JScrollPane scrollTabla = new JScrollPane(tablaVehiculos);
        panelCentro.add(scrollTabla, BorderLayout.CENTER);

        add(panelCentro, BorderLayout.CENTER);
    }

    // --- NUEVOS GETTERS PARA EL CONTROLADOR ---
    public JTextField getTxtPlaca() { return txtPlaca; }
    public JComboBox<String> getCbMarca() { return cbMarca; }
    public JComboBox<String> getCbModelo() { return cbModelo; }
    public JComboBox<String> getCbAnio() { return cbAnio; }
    public JTextField getTxtIdCliente() { return txtIdCliente; }
    public JButton getBtnGuardar() { return btnGuardar; }
    public DefaultTableModel getModeloTabla() { return modeloTabla; }
}