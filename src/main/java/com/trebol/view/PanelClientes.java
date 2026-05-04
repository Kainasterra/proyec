package com.trebol.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PanelClientes extends JPanel {

    // Componentes del formulario
    private JTextField txtNombre;
    private JTextField txtTelefono;
    private JTextField txtCorreo;
    private JTextField txtDireccion;
    private JButton btnGuardar;
    
    // Componentes de la tabla
    private JTable tablaClientes;
    private DefaultTableModel modeloTabla;

    public PanelClientes() {
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        setLayout(new BorderLayout(20, 20));
        setBackground(new Color(245, 245, 245));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // 1. Encabezado
        JLabel lblTitulo = new JLabel("Gestión de Clientes");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitulo.setForeground(new Color(46, 64, 83));
        add(lblTitulo, BorderLayout.NORTH);

        // --- CONTENEDOR CENTRAL ---
        JPanel panelCentro = new JPanel(new BorderLayout(10, 20));
        panelCentro.setBackground(new Color(245, 245, 245));

        // 2. Formulario
        JPanel panelFormulario = new JPanel(new GridLayout(5, 2, 10, 15));
        panelFormulario.setBackground(Color.WHITE);
        panelFormulario.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));

        panelFormulario.add(new JLabel("Nombre Completo:"));
        txtNombre = new JTextField();
        panelFormulario.add(txtNombre);

        panelFormulario.add(new JLabel("Teléfono:"));
        txtTelefono = new JTextField();
        panelFormulario.add(txtTelefono);

        panelFormulario.add(new JLabel("Correo Electrónico (Opcional):"));
        txtCorreo = new JTextField();
        panelFormulario.add(txtCorreo);

        panelFormulario.add(new JLabel("Dirección:"));
        txtDireccion = new JTextField();
        panelFormulario.add(txtDireccion);

        panelFormulario.add(new JLabel("")); 
        btnGuardar = new JButton("Registrar Cliente");
        btnGuardar.setBackground(new Color(52, 152, 219)); // Azul
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnGuardar.setFocusPainted(false);
        btnGuardar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panelFormulario.add(btnGuardar);

        panelCentro.add(panelFormulario, BorderLayout.NORTH);

        // 3. Tabla de Clientes
        String[] columnas = {"ID", "Nombre", "Teléfono", "Correo", "Dirección"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaClientes = new JTable(modeloTabla);
        tablaClientes.setRowHeight(25);
        tablaClientes.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        
        JScrollPane scrollTabla = new JScrollPane(tablaClientes);
        panelCentro.add(scrollTabla, BorderLayout.CENTER);

        add(panelCentro, BorderLayout.CENTER);
    }

    // --- GETTERS PARA EL CONTROLADOR ---
    public JTextField getTxtNombre() { return txtNombre; }
    public JTextField getTxtTelefono() { return txtTelefono; }
    public JTextField getTxtCorreo() { return txtCorreo; }
    public JTextField getTxtDireccion() { return txtDireccion; }
    public JButton getBtnGuardar() { return btnGuardar; }
    public DefaultTableModel getModeloTabla() { return modeloTabla; }
}